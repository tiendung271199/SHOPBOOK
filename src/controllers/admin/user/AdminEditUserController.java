package controllers.admin.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.OrderDao;
import daos.RoleDao;
import daos.UserDao;
import models.User;
import utils.CheckUtil;
import utils.StringUtil;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditUserController() {
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
		UserDao objUserDao = new UserDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objUserDao.checkId(id)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/user?err=1");
			return;
		}
		User objUser = objUserDao.getUser(id);
		if (adminLogin.getRole() == 2) {
			if (objUser.getRole() == 2 && objUser.getId() != adminLogin.getId()) {
				response.sendRedirect(request.getContextPath() + "/admin/user?err=5");
				return;
			}
		} else {
			if (objUser.getRole() == 2 || (objUser.getRole() == 1 && objUser.getId() != adminLogin.getId())) {
				response.sendRedirect(request.getContextPath() + "/admin/user?err=5");
				return;
			}
		}
		request.setAttribute("objUser", objUser);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao objUserDao = new UserDao();
		OrderDao objOrderDao = new OrderDao();
		int id = Integer.parseInt(request.getParameter("idUser"));
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
		request.setAttribute("email", email);
		request.setAttribute("phone", phone);
		request.setAttribute("role", role);
		User objUser = new User(id, username, password, fullname, address, email, phone, role);
		request.setAttribute("objUser", objUser);
		User user = objUserDao.getUser(id);
		
		if (password.equals("")) {
			objUser.setPassword(user.getPassword());
		} else {
			if (!password.equals(rePassword)) {
				request.setAttribute("err", "Password không chính xác!");
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
				rd.forward(request, response);
				return;
			}
			objUser.setPassword(StringUtil.md5(password));
		}
		
		if (fullname.equals("")) {
			request.setAttribute("err", "Fullname không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (address.equals("")) {
			request.setAttribute("err", "Address không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (email.equals("")) {
			request.setAttribute("err", "Email không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (phone.equals("")) {
			request.setAttribute("err", "Phone không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (objUserDao.edit(objUser) > 0) {
			if (objUser.getRole() == 0) {
				RoleDao objRoleDao = new RoleDao();
				if (objRoleDao.checkIdUser(objUser.getId())) {
					if (objRoleDao.deleteUser(objUser.getId()) > 0) {
						System.out.print("");
					}
				}
			}
			if (objOrderDao.edit(objUser) > 0) {
				System.out.print("");
			}
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=2");
			return;
		}
		request.setAttribute("err", "Có lỗi xảy ra khi sửa!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
		rd.forward(request, response);
		
	}

}
