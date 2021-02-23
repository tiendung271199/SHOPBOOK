package controllers.publics.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.OrderDao;
import daos.OrderDetailDao;
import models.Order;
import models.OrderDetail;
import models.OrderManage;
import models.User;
import utils.AuthUtil;
import utils.CategoryUtil;
import utils.EmailUtil;
import utils.PublicUtil;

public class PublicCancelOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicCancelOrderController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (!AuthUtil.checkPublicLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		User userLogin = (User) session.getAttribute("userLogin");
		PublicUtil.getCountFavourite(request, response);
		request.setAttribute("activePage", 5);
		CategoryUtil.getCat(request, response);
		OrderDao objOrderDao = new OrderDao();
		OrderDetailDao objOrderDetailDao = new OrderDetailDao();
		int idOrder = 0;
		try {
			idOrder = Integer.parseInt(request.getParameter("id"));
			if (!objOrderDao.checkId(idOrder, userLogin.getId())) {
				response.sendRedirect(request.getContextPath() + "/error-2");
				return;
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/error-1");
			return;
		}
		Order objOrder = objOrderDao.getOrder(idOrder);
		if (objOrder.getIdUser() != userLogin.getId()) {
			response.sendRedirect(request.getContextPath() + "/order/user?err=1");
			return;
		}
		if (objOrder.getStatus() == 4 || objOrder.getStatus() == 3) {
			response.sendRedirect(request.getContextPath() + "/order/user?err=2");
			return;
		}
		if (objOrderDao.editStatus(4, idOrder) > 0) {
			String kOrder = "order" + userLogin.getId();
			if (session.getAttribute(kOrder) != null) {
				ArrayList<OrderManage> list = (ArrayList<OrderManage>) session.getAttribute(kOrder);
				if (list.size() > 0) {
					for (OrderManage orderManage : list) {
						if (orderManage.getId() == idOrder) {
							orderManage.setStatus(4);
						}
					}
				}
			}
		}
		String toEmail = objOrder.getEmail();
		String userEmail = GlobalConstant.EMAIL_USER;
		String passEmail = GlobalConstant.EMAIL_PASS;
		String subject = GlobalConstant.CANCEL_ORDER_SUBJECT;
		ArrayList<OrderDetail> listOrderDetail = objOrderDetailDao.getOrderDetail(objOrder.getId());
		String content = EmailUtil.emailContentCancelOrder(listOrderDetail, objOrder.getName());
		EmailUtil.sendMail(toEmail, subject, content, userEmail, passEmail);
		response.sendRedirect(request.getContextPath() + "/order/user?cancel=success");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
