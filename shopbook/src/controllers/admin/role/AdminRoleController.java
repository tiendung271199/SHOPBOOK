package controllers.admin.role;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.ModuleDao;
import daos.UserDao;
import models.Modules;
import models.User;
import utils.CheckUtil;

public class AdminRoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminRoleController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.ROLE_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
		UserDao objUserDao = new UserDao();
		ModuleDao objModuleDao = new ModuleDao();
		ArrayList<User> listUser = objUserDao.getMod();
		ArrayList<Modules> listModules = objModuleDao.getAll();
		request.setAttribute("listUser", listUser);
		request.setAttribute("listModules", listModules);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/role/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao objUserDao = new UserDao();
		ModuleDao objModuleDao = new ModuleDao();
		ArrayList<User> listUser = objUserDao.getMod();
		ArrayList<Modules> listModules = objModuleDao.getAll();
		request.setAttribute("listUser", listUser);
		request.setAttribute("listModules", listModules);
		if (request.getParameter("aId") != null) {
			int idUser = Integer.parseInt(request.getParameter("aId"));
			request.setAttribute("idUser", idUser);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/role/index.jsp");
		rd.forward(request, response);
	}

}
