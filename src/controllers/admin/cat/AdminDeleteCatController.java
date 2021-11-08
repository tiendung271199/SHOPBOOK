package controllers.admin.cat;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.BookDao;
import daos.CategoryDao;
import daos.FavouriteDao;
import daos.ReviewsDao;
import daos.SaleOffDao;
import models.Book;
import models.User;
import utils.CategoryUtil;
import utils.CheckUtil;
import utils.FileUtil;
import utils.StringUtil;

public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.CAT_MODULE;
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
		CategoryDao objCatDao = new CategoryDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objCatDao.checkId(id)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/cat?err=1");
			return;
		}
		ArrayList<Integer> listCatId = new ArrayList<Integer>();
		listCatId.add(id);
		listCatId = CategoryUtil.getIdCat(listCatId, id);
		if (listCatId.size() > 0) {
			for (int i = listCatId.size() - 1; i >= 0; i--) {
				int catId = listCatId.get(i);
				if (objBookDao.checkIdCat(catId)) {
					ArrayList<Book> listBook = objBookDao.getBookByCat(catId);
					if (listBook.size() > 0) {
						for (Book book : listBook) {
							ArrayList<String> listPicture = StringUtil.getFileName(book.getPicture());
							if (objSaleDao.checkSale(book.getId())) {
								if (objSaleDao.deleteBook(book.getId()) > 0) {
									System.out.print("");
								}
							}
							if (objFavDao.checkIdBook(book.getId())) {
								if (objFavDao.delete(book.getId()) > 0) {
									System.out.print("");
								}
							}
							if (objReviewsDao.checkIdBook(book.getId())) {
								if (objReviewsDao.deleteBook(book.getId()) > 0) {
									System.out.print("");
								}
							}
							if (objBookDao.delete(book.getId()) > 0) {
								if (listPicture.size() > 0) {
									for (String str : listPicture) {
										FileUtil.delFile(str, request);
									}
								}
							}
						}
					}
				}
			}
		}

		if (objCatDao.delete(listCatId) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=3");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/cat?err=2");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
