package controllers.publics.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.AuthUtil;
import utils.CategoryUtil;
import utils.PublicUtil;

public class PublicOrderManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicOrderManageController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		if (!AuthUtil.checkPublicLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		PublicUtil.getCountFavourite(request, response);
		request.setAttribute("activePage", 5);
		CategoryUtil.getCat(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/ordermanage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
