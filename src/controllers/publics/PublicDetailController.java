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
import daos.FavouriteDao;
import daos.ReviewsDao;
import models.Book;
import models.Reviews;
import utils.CategoryUtil;
import utils.PublicUtil;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicDetailController() {
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
		FavouriteDao objFavDao = new FavouriteDao();
		ReviewsDao objReviewsDao = new ReviewsDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objBookDao.checkId(id)) {
				response.sendRedirect(request.getContextPath() + "/error-2");
				return;
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/error-1");
			return;
		}
		int favouritesCount = objFavDao.getFavouriteCount(id);
		request.setAttribute("favouritesCount", favouritesCount);
		int reviewsCount = objReviewsDao.getReviewsCount(id);
		request.setAttribute("reviewsCount", reviewsCount);
		Book objBook = objBookDao.getBook(id);
		request.setAttribute("objBook", objBook);
		ArrayList<Reviews> listReviews = objReviewsDao.getReviews(id);
		request.setAttribute("listReviews", listReviews);
		ArrayList<Book> listBookRelated = objBookDao.getBookByCat(objBook.getCat().getId());
		request.setAttribute("listBookRelated", listBookRelated);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		ReviewsDao objReviewsDao = new ReviewsDao();
		if (request.getParameter("aImg") != null) {
			String img = request.getParameter("aImg");
			out.print(img);
		}

		if (request.getParameter("aIdEdit") != null) {
			int id = Integer.parseInt(request.getParameter("aIdEdit"));
			Reviews objReviews = objReviewsDao.getReviewsId(id);
			out.print("<form>");
			out.print("<p><label style=\"width: 120px; float: left\">Số sao: </label>");
			out.print("<input type=\"text\" id=\"star-reviews\" value=\"" + objReviews.getStar() + "\" /> <i class=\"fa fa-star\"></i></p>");
			out.print("<p><label style=\"width: 120px; float: left\">Nội dung đánh giá: </label>");
			out.print("<textarea id=\"comment-reviews\" rows=\"5\" cols=\"60\">" + objReviews.getComment() + "</textarea></p>");
			out.print("<p>");
			out.print("<a style=\"padding: 5px 10px; background-color: #CCC\" href=\"javascript:void(0)\" onclick=\"xuLyEditReviews(" + objReviews.getId() + ")\">Sửa dánh giá</a>");
			out.print("<a style='padding: 5px 10px; background-color: #CCC; margin-left: 5px' href='javascript:void(0)' onclick='huyEdit()'>Huỷ</a>");
			out.print("</p></form>");
		}
	}

}
