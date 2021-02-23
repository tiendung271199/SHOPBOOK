package controllers.publics;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.ContactDao;
import models.Contact;
import utils.CategoryUtil;
import utils.PublicUtil;

public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicContactController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		CategoryUtil.getCat(request, response);
		request.setAttribute("activePage", 3);
		HttpSession session = request.getSession();
		if (session.getAttribute("userLogin") != null) {
			PublicUtil.getCountFavourite(request, response);
		}
		if (request.getParameter("msg") != null) {
			request.setAttribute("success", "Gửi liên hệ thành công!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		CategoryUtil.getCat(request, response);
		request.setAttribute("activePage", 3);
		HttpSession session = request.getSession();
		if (session.getAttribute("userLogin") != null) {
			PublicUtil.getCountFavourite(request, response);
		}
		ContactDao objContactDao = new ContactDao();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		request.setAttribute("name", name);
		request.setAttribute("email", email);
		request.setAttribute("message", message);
		if (name.equals("")) {
			request.setAttribute("err", "Tên không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (email.equals("")) {
			request.setAttribute("err", "Email không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (message.equals("")) {
			request.setAttribute("err", "Message không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
			rd.forward(request, response);
			return;
		}
		Contact objContact = new Contact(0, name, email, message);
		if (objContactDao.add(objContact) > 0) {
			response.sendRedirect(request.getContextPath() + "/contact?msg=success");
			return;
		}
		request.setAttribute("err", "Có lỗi khi gửi liên hệ!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
		rd.forward(request, response);
	}

}
