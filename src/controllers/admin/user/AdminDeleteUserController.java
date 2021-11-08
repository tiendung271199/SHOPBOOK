package controllers.admin.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.FavouriteDao;
import daos.ReviewsDao;
import daos.RoleDao;
import daos.UserDao;
import models.User;
import utils.CheckUtil;

public class AdminDeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteUserController() {
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
		RoleDao objRoleDao = new RoleDao();
		ReviewsDao objReviewsDao = new ReviewsDao();
		FavouriteDao objFavouriteDao = new FavouriteDao();
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
			if (objUser.getRole() == 2) {
				response.sendRedirect(request.getContextPath() + "/admin/user?err=6");
				return;
			}
		} else {
			if (objUser.getRole() != 0) {
				response.sendRedirect(request.getContextPath() + "/admin/user?err=6");
				return;
			}
		}
		
		if (objFavouriteDao.checkIdUser(id)) {
			if (objFavouriteDao.deleteUser(id) > 0) {
				System.out.print("");
			}
		}
		
		if (objReviewsDao.checkIdUser(id)) {
			if (objReviewsDao.deleteUser(id) > 0) {
				System.out.print("");
			}
		}
		
		if (objRoleDao.checkIdUser(id)) {
			if (objRoleDao.deleteUser(id) > 0) {
				System.out.print("");
			}
		}
		
		if (objUserDao.delete(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=3");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/user?err=2");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
