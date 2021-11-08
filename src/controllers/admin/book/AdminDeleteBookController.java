package controllers.admin.book;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.BookDao;
import daos.FavouriteDao;
import daos.ReviewsDao;
import daos.SaleOffDao;
import models.Book;
import models.User;
import utils.CheckUtil;
import utils.FileUtil;
import utils.StringUtil;

public class AdminDeleteBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteBookController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.BOOK_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
		ReviewsDao objReviewsDao = new ReviewsDao();
		FavouriteDao objFavDao = new FavouriteDao();
		SaleOffDao objSaleDao = new SaleOffDao();
		BookDao objBookDao = new BookDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objBookDao.checkId(id)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/book?err=1");
			return;
		}
		Book objBook = objBookDao.getBook(id);
		ArrayList<String> listPicture = StringUtil.getFileName(objBook.getPicture());
		if (objSaleDao.checkSale(id)) {
			if (objSaleDao.deleteBook(id) > 0) {
				System.out.print("");
			}
		}
		
		if (objFavDao.checkIdBook(id)) {
			if (objFavDao.delete(id) > 0) {
				System.out.print("");
			}
		}
		
		if (objReviewsDao.checkIdBook(id)) {
			if (objReviewsDao.deleteBook(id) > 0) {
				System.out.print("");
			}
		}
		
		if (objBookDao.delete(id) > 0) {
			if (listPicture.size() > 0) {
				for (String str : listPicture) {
					FileUtil.delFile(str, request);
				}
			}
			response.sendRedirect(request.getContextPath() + "/admin/book?msg=3");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/book?err=2");
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
