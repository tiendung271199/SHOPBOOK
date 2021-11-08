package controllers.publics.log;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDao;
import models.Cart;
import models.User;
import utils.StringUtil;

public class PublicRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicRegisterController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("checkout") != null) {
			HttpSession session = request.getSession();
			int checkout = Integer.parseInt(request.getParameter("checkout"));
			session.setAttribute("checkoutUser", checkout);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/log/register.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserDao objUserDao = new UserDao();
		String fullname = request.getParameter("Fullname");
		String address = request.getParameter("Address");
		String email = request.getParameter("Email");
		String phone = request.getParameter("Phone");
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		String rePassword = request.getParameter("RePassword");
		request.setAttribute("username", username);
		request.setAttribute("fullname", fullname);
		request.setAttribute("address", address);
		request.setAttribute("email", email);
		request.setAttribute("phone", phone);
		if (fullname.equals("")) {
			request.setAttribute("err", "Fullname không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/log/register.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (address.equals("")) {
			request.setAttribute("err", "Address không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/log/register.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (email.equals("")) {
			request.setAttribute("err", "Email không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/log/register.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (phone.equals("")) {
			request.setAttribute("err", "Phone không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/log/register.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (username.equals("")) {
			request.setAttribute("err", "Username không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/log/register.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (objUserDao.checkUsername(username)) {
			request.setAttribute("err", "Username đã tồn tại!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/log/register.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (password.equals("")) {
			request.setAttribute("err", "Password không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/log/register.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (rePassword.equals("")) {
			request.setAttribute("err", "RePassword không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/log/register.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (!rePassword.equals(password)) {
			request.setAttribute("err", "Password không chính xác!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/log/register.jsp");
			rd.forward(request, response);
			return;
		}
		
		password = StringUtil.md5(password);
		User objUser = new User(0, username, password, fullname, address, email, phone, 0);
		if (objUserDao.add(objUser) > 0) {
			if (session.getAttribute("checkoutUser") != null) {
				Cart objCart = (Cart) session.getAttribute("cart");
				User newUser = objUserDao.getUserNew();
				String kCart = "cart" + newUser.getId();
				session.setAttribute(kCart, objCart);
				session.removeAttribute("checkoutUser");
			}
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		request.setAttribute("err", "Có lỗi xảy ra khi đăng ký tài khoản!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/log/register.jsp");
		rd.forward(request, response);
	}

}
