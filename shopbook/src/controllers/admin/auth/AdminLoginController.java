package controllers.admin.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDao;
import models.User;
import utils.StringUtil;

public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminLoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/auth/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserDao objUserDao = new UserDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		request.setAttribute("username", username);
		password = StringUtil.md5(password);
		User adminLogin = objUserDao.getUserLogin(username, password);
		if (adminLogin != null) {
			session.setAttribute("adminLogin", adminLogin);
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}
		request.setAttribute("err", "Username hoặc password không chính xác!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/auth/login.jsp");
		rd.forward(request, response);
	}

}
