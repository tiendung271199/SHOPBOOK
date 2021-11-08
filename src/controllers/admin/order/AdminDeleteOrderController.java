package controllers.admin.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.OrderDao;
import daos.OrderDetailDao;
import models.User;
import utils.CheckUtil;

public class AdminDeleteOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteOrderController() {
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
		OrderDao objOrderDao = new OrderDao();
		OrderDetailDao objOrderDetailDao = new OrderDetailDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objOrderDao.checkId(id)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/order?err=1");
			return;
		}
		if (objOrderDetailDao.checkOrder(id)) {
			if (objOrderDetailDao.delete(id) > 0) {
				System.out.print("");
			}
		}
		if (objOrderDao.delete(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/order?msg=1");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/order?err=2");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
