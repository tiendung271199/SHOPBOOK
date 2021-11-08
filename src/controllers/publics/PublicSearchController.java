package controllers.publics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.BookDao;
import daos.SaleOffDao;
import models.Book;
import models.SaleOff;
import utils.CategoryUtil;
import utils.PublicUtil;
import utils.StringUtil;

public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicSearchController() {
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
		ArrayList<Book> listBook = new ArrayList<Book>();
		int total = 0;
		if (request.getParameter("sname") != null) {
			String sName = request.getParameter("sname");
			total = objBookDao.getCount(sName);
			request.setAttribute("sname", sName);
			listBook = objBookDao.getBookName(sName);
		}
		if (request.getParameter("minprice") != null || request.getParameter("maxprice") != null) {
			String sMin = request.getParameter("minprice");
			String sMax = request.getParameter("maxprice");
			request.setAttribute("sMin", sMin);
			request.setAttribute("sMax", sMax);
			int min = 0, max = 0;
			try {
				min = Integer.parseInt(sMin);
			} catch (Exception e) {
				System.out.print("");
			}
			try {
				max = Integer.parseInt(sMax);
			} catch (Exception e) {
				System.out.print("");
			}
			if (!sMin.equals("") && sMax.equals("")) {
				total = objBookDao.getCount2(min);
				listBook = objBookDao.searchPrice(min);
			} else {
				total = objBookDao.getCount(min, max);
				if (min < max) {
					listBook = objBookDao.searchPrice(min, max);
				} else {
					listBook = objBookDao.searchPrice(max, min);
				}
			}
		}
		request.setAttribute("total", total);
		request.setAttribute("listSale", listSale);
		request.setAttribute("listBookNew", listBookNew);
		request.setAttribute("listBook", listBook);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/search.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		int theLoai = 0, gia = 0;
		if (!request.getParameter("aTheLoai").equals("")) {
			theLoai = Integer.parseInt(request.getParameter("aTheLoai"));
		}
		if (!request.getParameter("aGia").equals("")) {
			gia = Integer.parseInt(request.getParameter("aGia"));
		}
		int min = 0, max = 0;
		if (gia == 2) {
			max = 100000;
		} else if (gia == 3) {
			max = 200000;
			min = 100000;
		} else if (gia == 4) {
			min = 200000;
			max = 300000;
		} else if (gia == 5) {
			min = 300000;
		}
		BookDao objBookDao = new BookDao();
		ArrayList<Book> list = objBookDao.search(min, max, theLoai);
		PrintWriter out = response.getWriter();
		if (list.size() > 0) {
			out.print("<div class=\"row\">");
			for (Book book : list) {
				String picture = StringUtil.fileName(book.getPicture());
				out.print("<div class=\"col-lg-4 col-md-6 col-sm-6\">");
				out.print("<div class=\"product__item\">");
				out.print("<div class=\"product__item__pic set-bg\" data-setbg=\"" + request.getContextPath()
						+ "/uploads/images/" + book.getPicture() + "\"");
				out.print("style=\"background-image: url(&quot;/shopbook/uploads/images/" + picture + "&quot;);\">");
				out.print("<ul class=\"product__item__pic__hover\">");
				out.print("<li><a href=\"#\"><i class=\"fa fa-heart\"></i></a></li>");
				out.print("<li><a href=\"#\"><i class=\"fa fa-retweet\"></i></a></li>");
				out.print("<li><a href=\"#\"><i class=\"fa fa-shopping-cart\"></i></a></li>");
				out.print("</ul></div>");
				out.print("<div class=\"product__item__text\">");
				out.print("<h6><a href=\"#\">" + book.getName() + "</a></h6>");
				out.print("<h5>" + book.getPrice() + " VNĐ</h5>");
				out.print("</div></div></div>");
			}
			out.print("</div>");
		}
	}

}
