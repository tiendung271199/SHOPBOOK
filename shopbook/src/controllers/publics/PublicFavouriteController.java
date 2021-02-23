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
import daos.FavouriteDao;
import models.Book;
import models.Favourite;
import models.User;
import utils.AuthUtil;
import utils.CategoryUtil;
import utils.PublicUtil;

public class PublicFavouriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicFavouriteController() {
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
		if (!AuthUtil.checkPublicLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		User userLogin = (User) session.getAttribute("userLogin");
		PublicUtil.getCountFavourite(request, response);
		FavouriteDao objFavouriteDao = new FavouriteDao();
		BookDao objBookDao = new BookDao();
		ArrayList<Favourite> listFavourite = objFavouriteDao.getFavouriteUser(userLogin.getId());
		ArrayList<Book> listBook = new ArrayList<Book>();
		if (listFavourite.size() > 0) {
			for (Favourite fav : listFavourite) {
				Book book = objBookDao.getBook(fav.getIdBook());
				listBook.add(book);
			}
		}
		ArrayList<Book> listBookNew = objBookDao.getBookNew();
		request.setAttribute("listBookNew", listBookNew);
		request.setAttribute("listBook", listBook);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/favourite.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
