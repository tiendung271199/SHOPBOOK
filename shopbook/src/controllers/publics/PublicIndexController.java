package controllers.publics;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.BookDao;
import models.Book;
import utils.BookUtil;
import utils.CategoryUtil;
import utils.HighlightsUtil;
import utils.PublicUtil;

public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicIndexController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		CategoryUtil.getCat(request, response);
		request.setAttribute("activePage", 1);
		HttpSession session = request.getSession();
		BookDao objBookDao = new BookDao();
		ArrayList<Book> listBookNew = objBookDao.getBookNew(); // sách mới nhất
		request.setAttribute("listBookNew", listBookNew);
		ArrayList<Book> listBookPurchases = objBookDao.getBookPurchases(); // sách được mua nhiều nhất
		request.setAttribute("listBookPurchases", listBookPurchases);
		ArrayList<Book> listBookFavourite = PublicUtil.getBestFavourite(request, response); // sách được yêu thích nhiều nhất
		request.setAttribute("listBookFavourite", listBookFavourite);
		ArrayList<Book> listBookReviews = PublicUtil.getBestReviews(request, response); // sách được đánh giá cao nhất
		request.setAttribute("listBookReviews", listBookReviews);
		ArrayList<HighlightsUtil> listHighlights = new ArrayList<HighlightsUtil>(); // lọc sản phẩm nổi bật
		HighlightsUtil objMoi = new HighlightsUtil(0, 0, 1, 0, 0);
		listHighlights = BookUtil.listHighlights(listHighlights, listBookNew, objMoi);
		HighlightsUtil objMua = new HighlightsUtil(0, 1, 0, 0, 0);
		listHighlights = BookUtil.listHighlights(listHighlights, listBookPurchases, objMua);
		HighlightsUtil objDanhGia = new HighlightsUtil(0, 0, 0, 1, 0);
		listHighlights = BookUtil.listHighlights(listHighlights, listBookReviews, objDanhGia);
		HighlightsUtil objYeuThich = new HighlightsUtil(0, 0, 0, 0, 1);
		listHighlights = BookUtil.listHighlights(listHighlights, listBookFavourite, objYeuThich);
		request.setAttribute("listHighlights", listHighlights);
		if (session.getAttribute("userLogin") != null) {
			PublicUtil.getCountFavourite(request, response);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
