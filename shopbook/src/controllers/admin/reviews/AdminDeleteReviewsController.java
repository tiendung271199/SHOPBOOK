package controllers.admin.reviews;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.ReviewsDao;
import models.User;
import utils.CheckUtil;

public class AdminDeleteReviewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteReviewsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.REVIEWS_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
		ReviewsDao objReviewsDao = new ReviewsDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objReviewsDao.checkId(id)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/reviews?err=1");
			return;
		}
		if (objReviewsDao.delete(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/reviews?msg=1");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/reviews?err=2");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
