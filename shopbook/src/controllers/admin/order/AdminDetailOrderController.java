package controllers.admin.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
import utils.CheckUtil;
import utils.EmailUtil;

public class AdminDetailOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDetailOrderController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.ORDER_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
		OrderDetailDao objOrderDetailDao = new OrderDetailDao();
		OrderDao objOrderDao = new OrderDao();
		int idOrder = 0;
		try {
			idOrder = Integer.parseInt(request.getParameter("id"));
			if (!objOrderDetailDao.checkId(idOrder)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/order?err=1");
			return;
		}
		Order objOrder = objOrderDao.getOrder(idOrder);
		session.setAttribute("trangThaiOrder", objOrder.getStatus());
		ArrayList<OrderDetail> listOrderDetail = objOrderDetailDao.getOrderDetail(idOrder);
		request.setAttribute("objOrder", objOrder);
		request.setAttribute("listOrderDetail", listOrderDetail);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/order/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		OrderDao objOrderDao = new OrderDao();
		OrderDetailDao objOrderDetailDao = new OrderDetailDao();
		int trangThai = Integer.parseInt(request.getParameter("trangthai"));
		int idOrder = Integer.parseInt(request.getParameter("idOrder"));
		int oldTrangThai = 0;
		if (session.getAttribute("trangThaiOrder") != null) {
			oldTrangThai = (int) session.getAttribute("trangThaiOrder");
		}
		if (trangThai == 4) {
			response.sendRedirect(request.getContextPath() + "/admin/order?err=4");
			return;
		}
		if (oldTrangThai == 3) {
			response.sendRedirect(request.getContextPath() + "/admin/order?err=5");
			return;
		}
		if (oldTrangThai == 4) {
			response.sendRedirect(request.getContextPath() + "/admin/order?err=6");
			return;
		}
		if (objOrderDao.editStatus(trangThai, idOrder) > 0) {
			Order objOrder = objOrderDao.getOrder(idOrder);
			String kOrder = "order" + objOrder.getIdUser();
			if (session.getAttribute(kOrder) != null) {
				ArrayList<OrderManage> list = (ArrayList<OrderManage>) session.getAttribute(kOrder);
				if (list.size() > 0) {
					for (OrderManage orderManage : list) {
						if (orderManage.getId() == idOrder) {
							orderManage.setStatus(trangThai);
						}
					}
				}
			}
			if (trangThai == 3) {
				String toEmail = objOrder.getEmail();
				String userEmail = GlobalConstant.EMAIL_USER;
				String passEmail = GlobalConstant.EMAIL_PASS;
				String subject = GlobalConstant.DELIVERY_SUCCESS_SUBJECT;
				ArrayList<OrderDetail> listOrderDetail = objOrderDetailDao.getOrderDetail(objOrder.getId());
				String content = EmailUtil.emailContentDeliverySuccess(listOrderDetail, objOrder.getName());
				EmailUtil.sendMail(toEmail, subject, content, userEmail, passEmail);
			}
		}
		response.sendRedirect(request.getContextPath() + "/admin/order?msg=4");
		return;
	}

}
