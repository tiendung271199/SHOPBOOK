package controllers.admin.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.UserDao;
import models.User;
import utils.CheckUtil;
import utils.StringUtil;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddUserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.USER_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao objUserDao = new UserDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("repassword");
		String fullname = request.getParameter("fullname");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int role = Integer.parseInt(request.getParameter("role"));
		request.setAttribute("username", username);
		request.setAttribute("fullname", fullname);
		request.setAttribute("address", address);
		request.setAttribute("email", email);
		request.setAttribute("phone", phone);
		request.setAttribute("role", role);
		if (username.equals("")) {
			request.setAttribute("err", "Username không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (objUserDao.checkUsername(username)) {
			request.setAttribute("err", "Username đã tồn tại!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (password.equals("")) {
			request.setAttribute("err", "Password không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (rePassword.equals("")) {
			request.setAttribute("err", "RePassword không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (!rePassword.equals(password)) {
			request.setAttribute("err", "Password không chính xác!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (fullname.equals("")) {
			request.setAttribute("err", "Fullname không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (address.equals("")) {
			request.setAttribute("err", "Address không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (email.equals("")) {
			request.setAttribute("err", "Email không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (phone.equals("")) {
			request.setAttribute("err", "Phone không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		password = StringUtil.md5(password);
		User objUser = new User(0, username, password, fullname, address, email, phone, role);
		if (objUserDao.add(objUser) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=1");
			return;
		}
		request.setAttribute("err", "Có lỗi xảy ra khi thêm!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
		rd.forward(request, response);
		
	}

}
