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
import daos.RoleDao;
import daos.UserDao;
import models.Modules;
import models.Role;
import models.User;
import utils.CheckUtil;

public class AdminRoleManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminRoleManageController() {
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RoleDao objRoleDao = new RoleDao();
		UserDao objUserDao = new UserDao();
		ModuleDao objModuleDao = new ModuleDao();
		ArrayList<User> listUser = objUserDao.getMod();
		ArrayList<Modules> listModules = objModuleDao.getAll();
		request.setAttribute("listUser", listUser);
		request.setAttribute("listModules", listModules);
		int idUser = Integer.parseInt(request.getParameter("mod"));
		request.setAttribute("idUser", idUser);
		if (idUser == 0) {
			request.setAttribute("err", "Vui lòng chọn user để phân quyền!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/role/index.jsp");
			rd.forward(request, response);
			return;
		}
		if (listModules.size() > 0) {
			for (Modules modules : listModules) {
				String role = request.getParameter("role" + modules.getId());
				if (objRoleDao.checkRole(idUser, modules.getId())) {
					if (role != null) {
						if (!objRoleDao.checkRole2(idUser, modules.getId())) {
							if (objRoleDao.editStatus(1, idUser, modules.getId()) > 0) {
								System.out.print("");
							}
						}
					} else {
						if (objRoleDao.checkRole2(idUser, modules.getId())) {
							if (objRoleDao.editStatus(0, idUser, modules.getId()) > 0) {
								System.out.print("");
							}
						}
					}
				} else {
					if (role != null) {
						Role objRole = new Role(0, idUser, modules.getId(), 0);
						if (objRoleDao.add(objRole) > 0) {
							System.out.print("");
						}
					}
				}
			}
		}
		request.setAttribute("success", "Cập nhật quyền thành công!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/role/index.jsp");
		rd.forward(request, response);
	}

}
