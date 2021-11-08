package controllers.admin.sale;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.SaleOffDao;
import models.User;
import utils.CheckUtil;

public class AdminDeleteSaleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteSaleController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.SALE_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
		SaleOffDao objSaleDao = new SaleOffDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objSaleDao.checkId(id)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/sale?err=1");
			return;
		}
		if (objSaleDao.delete(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/sale?msg=3");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/sale?err=2");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
