package controllers.publics;

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
import daos.SaleOffDao;
import models.Book;
import models.Category;
import models.SaleOff;
import utils.CategoryUtil;
import utils.PublicUtil;

public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		CategoryUtil.getCat(request, response);
		request.setAttribute("activePage", 2);
		HttpSession session = request.getSession();
		if (session.getAttribute("userLogin") != null) {
			PublicUtil.getCountFavourite(request, response);
		}
		CategoryDao objCatDao = new CategoryDao();
		BookDao objBookDao = new BookDao();
		SaleOffDao objSaleOffDao = new SaleOffDao();
		ArrayList<Book> listBookNew = objBookDao.getBookNew();
		ArrayList<SaleOff> listSale = objSaleOffDao.getSaleDec();
		int idCat = 0;
		if (request.getParameter("idCat") != null) {
			try {
				idCat = Integer.parseInt(request.getParameter("idCat"));
				if (!objCatDao.checkId(idCat)) {
					response.sendRedirect(request.getContextPath() + "/error-2");
					return;
				}
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/error-1");
				return;
			}
		}
		Category cat = objCatDao.getCat(idCat);
		request.setAttribute("category", cat);
		ArrayList<Integer> listCatId = new ArrayList<Integer>();
		listCatId.add(idCat);
		listCatId = CategoryUtil.getIdCat(listCatId, idCat);
		int numberPage = GlobalConstant.NUMBER_PAGE3;
		int first = 0, last = 0, pages = 1, totalPages = 1;
		int total = objBookDao.getCount(listCatId);
		if (total % numberPage == 0) {
			totalPages = total / numberPage;
		} else {
			totalPages = total / numberPage + 1;
		}
		if (request.getParameter("pages") != null) {
			try {
				pages = Integer.parseInt(request.getParameter("pages"));
				if (pages < 1 || pages > totalPages) {
					response.sendRedirect(request.getContextPath() + "/error-3");
					return;
				}
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/error-1");
				return;
			}
		}
		if (total <= numberPage) {
			first = 0;
			last = total;
		} else {
			first = (pages - 1) * numberPage;
			last = numberPage;
		}
		ArrayList<Book> listBook = objBookDao.getBook(first, last, listCatId);
		request.setAttribute("catId", idCat);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("pages", pages);
		request.setAttribute("total", total);
		request.setAttribute("listSale", listSale);
		request.setAttribute("listBookNew", listBookNew);
		request.setAttribute("listBook", listBook);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/cat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
