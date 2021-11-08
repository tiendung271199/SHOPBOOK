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
import daos.SaleOffDao;
import models.Book;
import models.SaleOff;
import utils.CategoryUtil;
import utils.PublicUtil;

public class PublicShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicShopController() {
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
		BookDao objBookDao = new BookDao();
		SaleOffDao objSaleOffDao = new SaleOffDao();
		ArrayList<Book> listBookNew = objBookDao.getBookNew();
		ArrayList<SaleOff> listSale = objSaleOffDao.getSaleDec();
		int numberPage = GlobalConstant.NUMBER_PAGE3;
		int first = 0, last = 0, pages = 1, totalPages = 1;
		int total = objBookDao.getCount();
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
		ArrayList<Book> listBook = objBookDao.getBook(first, last);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("total", total);
		request.setAttribute("pages", pages);
		request.setAttribute("listSale", listSale);
		request.setAttribute("listBookNew", listBookNew);
		request.setAttribute("listBook", listBook);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/shop.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
