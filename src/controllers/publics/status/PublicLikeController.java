package controllers.publics.status;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.FavouriteDao;
import models.Favourite;
import models.User;

public class PublicLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicLikeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		FavouriteDao objFavouriteDao = new FavouriteDao();
		User userLogin = (User) session.getAttribute("userLogin");
		int idBook = Integer.parseInt(request.getParameter("aBIdLike"));
		int idUser = userLogin.getId();
		if (objFavouriteDao.checkFavourite(idBook, idUser)) {
			Favourite objFavourite = objFavouriteDao.getFavourite(idUser, idBook);
			if (objFavourite.getStatus() == 1) {
				if (objFavouriteDao.editStatus(idBook, idUser, 0) > 0) {
					int like = objFavouriteDao.getFavouriteCount(idBook);
					if (request.getParameter("aCheck") != null) {
						out.print("<i class=\"fa fa-heart\"></i>");
					} else {
						out.print("<span class=\"icon_heart_alt\"></span><span style=\"margin-left: 5px\">" + like + "</span>");
					}
				}
			} else {
				if (objFavouriteDao.editStatus(idBook, idUser, 1) > 0) {
					int like = objFavouriteDao.getFavouriteCount(idBook);
					if (request.getParameter("aCheck") != null) {
						out.print("<i style=\"color: red\" class=\"fa fa-heart\"></i>");
					} else {
						out.print("<span style=\"color: red\" class=\"icon_heart_alt\"></span><span style=\"margin-left: 5px\">" + like + "</span>");
					}
				}
			}
		} else {
			Favourite objFavourite = new Favourite(idUser, idBook);
			if (objFavouriteDao.add(objFavourite) > 0) {
				int like = objFavouriteDao.getFavouriteCount(idBook);
				if (request.getParameter("aCheck") != null) {
					out.print("<i style=\"color: red\" class=\"fa fa-heart\"></i>");
				} else {
					out.print("<span style=\"color: red\" class=\"icon_heart_alt\"></span><span style=\"margin-left: 5px\">" + like + "</span>");
				}
			}
		}

	}

}
