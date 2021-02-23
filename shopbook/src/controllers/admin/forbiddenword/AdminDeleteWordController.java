package controllers.admin.forbiddenword;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.ForbiddenWordDao;
import models.User;
import utils.CheckUtil;

public class AdminDeleteWordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteWordController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.WORD_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
		ForbiddenWordDao objWordDao = new ForbiddenWordDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objWordDao.checkId(id)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/forbiddenword?err=1");
			return;
		}
		if (objWordDao.delete(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/forbiddenword?msg=3");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/forbiddenword?err=2");
		return;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
