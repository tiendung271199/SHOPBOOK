package controllers.admin.book;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.BookDao;
import daos.CategoryDao;
import models.Book;
import models.Category;
import models.User;
import utils.CategoryUtil;
import utils.CheckUtil;

public class AdminEditBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditBookController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.BOOK_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
		CategoryUtil.getCat(request, response);
		BookDao objBookDao = new BookDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objBookDao.checkId(id)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/book?err=1");
			return;
		}
		Book objBook = objBookDao.getBook(id);
		request.setAttribute("objBook", objBook);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryUtil.getCat(request, response);
		BookDao objBookDao = new BookDao();
		CategoryDao objCatDao = new CategoryDao();
		int id = Integer.parseInt(request.getParameter("idBook"));
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		int catId = Integer.parseInt(request.getParameter("category"));
		String sNumber = request.getParameter("number");
		String sPrice = request.getParameter("price");
		String description = request.getParameter("description");
		String detail = request.getParameter("detail");
		int number = 0, price = 0;
		Category cat = objCatDao.getCat(catId);
		Book objBook = objBookDao.getBook(id);
		objBook.setCat(cat);
		request.setAttribute("objBook", objBook);
		
		if (name.equals("")) {
			request.setAttribute("err", "Tên sách không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (objBookDao.checkName(name, objBook.getName())) {
			request.setAttribute("err", "Tên sách đã tồn tại!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
			rd.forward(request, response);
			return;
		}
		objBook.setName(name);
		request.setAttribute("objBook", objBook);
		
		if (author.equals("")) {
			request.setAttribute("err", "Tác giả không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
			rd.forward(request, response);
			return;
		}
		objBook.setAuthor(author);
		request.setAttribute("objBook", objBook);
		
		if (sNumber.equals("")) {
			request.setAttribute("err", "Số lượng không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		try {
			number = Integer.parseInt(sNumber);
			if (number <= 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			request.setAttribute("err", "Số lượng là số nguyên > 0!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
			rd.forward(request, response);
			return;
		}
		objBook.setNumber(number);
		request.setAttribute("objBook", objBook);
		
		if (sPrice.equals("")) {
			request.setAttribute("err", "Giá bán không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (!sPrice.endsWith("000")) {
			request.setAttribute("err", "Giá bán không hợp lệ!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		try {
			price = Integer.parseInt(sPrice);
			if (price <= 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			request.setAttribute("err", "Giá bán là số nguyên > 0!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
			rd.forward(request, response);
			return;
		}
		objBook.setPrice(price);
		request.setAttribute("objBook", objBook);
		
		if (description.equals("")) {
			request.setAttribute("err", "Mô tả không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
			rd.forward(request, response);
			return;
		}
		objBook.setDescription(description);
		request.setAttribute("objBook", objBook);
		
		if (detail.equals("")) {
			request.setAttribute("err", "Chi tiết không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
			rd.forward(request, response);
			return;
		}
		objBook.setDetail(detail);
		request.setAttribute("objBook", objBook);
		
		if (objBookDao.edit(objBook) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/book?msg=2");
			return;
		}
		request.setAttribute("err", "Có lỗi xảy ra khi sửa!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/edit.jsp");
		rd.forward(request, response);
		
	}

}
