package controllers.publics.error;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.CategoryUtil;
import utils.PublicUtil;

public class PublicErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicErrorController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		CategoryUtil.getCat(request, response);
		request.setAttribute("activePage", 10);
		HttpSession session = request.getSession();
		if (session.getAttribute("userLogin") != null) {
			PublicUtil.getCountFavourite(request, response);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/error/error.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
