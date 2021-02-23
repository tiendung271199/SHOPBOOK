package controllers.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.BookDao;
import daos.CategoryDao;
import daos.ContactDao;
import daos.ForbiddenWordDao;
import daos.OrderDao;
import daos.ReviewsDao;
import daos.RoleDao;
import daos.SaleOffDao;
import daos.UserDao;
import models.Role;
import models.User;

public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDao objCatDao = new CategoryDao();
		BookDao objBookDao = new BookDao();
		OrderDao objOrderDao = new OrderDao();
		UserDao objUserDao = new UserDao();
		ReviewsDao objReviewsDao = new ReviewsDao();
		SaleOffDao objSaleDao = new SaleOffDao();
		ContactDao objContactDao = new ContactDao();
		ForbiddenWordDao objWordDao = new ForbiddenWordDao();
		RoleDao objRoleDao = new RoleDao();
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		if (adminLogin.getRole() == 2) {
			int oldOrder = 0;
			if (session.getAttribute("numOrder") != null) {
				oldOrder = (int) session.getAttribute("numOrder");
			}
			int newOrder = objOrderDao.getCount() - oldOrder;
			int numBook = objBookDao.getCount();
			int numUser = objUserDao.getCount();
			request.setAttribute("newOrder", newOrder);
			request.setAttribute("numBook", numBook);
			request.setAttribute("numUser", numUser);
		} else {
			if (objRoleDao.checkIdUser(adminLogin.getId())) {
				ArrayList<Role> listRole = objRoleDao.getRoleUser(adminLogin.getId());
				if (listRole.size() > 0) {
					for (Role role : listRole) {
						int moduleId = role.getIdModule();
						if (role.getIdModule() == GlobalConstant.CAT_MODULE) {
							int numCat = objCatDao.getCount();
							request.setAttribute("numCat", numCat);
						}
						if (moduleId == GlobalConstant.BOOK_MODULE) {
							int numBook = objBookDao.getCount();
							request.setAttribute("numBook", numBook);
						}
						if (moduleId == GlobalConstant.USER_MODULE) {
							int numUser = objUserDao.getCount();
							request.setAttribute("numUser", numUser);
						}
						if (moduleId == GlobalConstant.ORDER_MODULE) {
							int oldOrder = 0;
							if (session.getAttribute("numOrder") != null) {
								oldOrder = (int) session.getAttribute("numOrder");
							}
							int newOrder = objOrderDao.getCount() - oldOrder;
							request.setAttribute("newOrder", newOrder);
						}
						if (moduleId == GlobalConstant.REVIEWS_MODULE) {
							int oldReviews = 0;
							if (session.getAttribute("numReviews") != null) {
								oldReviews = (int) session.getAttribute("numReviews");
							}
							int newReviews = objReviewsDao.getCount() - oldReviews;
							request.setAttribute("newReviews", newReviews);
						}
						if (moduleId == GlobalConstant.SALE_MODULE) {
							int numSale = objSaleDao.getCount();
							request.setAttribute("numSale", numSale);
						}
						if (moduleId == GlobalConstant.CONTACT_MODULE) {
							int oldContact = 0;
							if (session.getAttribute("numContact") != null) {
								oldContact = (int) session.getAttribute("numContact");
							}
							int newContact = objContactDao.getCount() - oldContact;
							request.setAttribute("newContact", newContact);
						}
						if (moduleId == GlobalConstant.WORD_MODULE) {
							int numWord = objWordDao.getCount();
							request.setAttribute("numWord", numWord);
						}
						if (moduleId == GlobalConstant.ROLE_MODULE) {
							int numRole = objRoleDao.getCount();
							request.setAttribute("numRole", numRole);
						}
					}
				}
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
