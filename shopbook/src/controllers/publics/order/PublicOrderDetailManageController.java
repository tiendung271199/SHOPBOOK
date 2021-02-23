package controllers.publics.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.OrderDao;
import models.OrderManage;
import models.User;
import utils.AuthUtil;
import utils.CategoryUtil;
import utils.PublicUtil;

public class PublicOrderDetailManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicOrderDetailManageController() {
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
		String kOrder = "order" + userLogin.getId();
		if (session.getAttribute(kOrder) != null) {
			ArrayList<OrderManage> listOrderManage = (ArrayList<OrderManage>) session.getAttribute(kOrder);
			if (listOrderManage.size() > 0) {
				for (OrderManage orderManage : listOrderManage) {
					if (orderManage.getId() == idOrder) {
						request.setAttribute("orderManage", orderManage);
					}
				}
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/orderdetail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
