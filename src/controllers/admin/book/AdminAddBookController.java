package controllers.admin.book;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import utils.FileUtil;

@MultipartConfig
public class AdminAddBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddBookController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryUtil.getCat(request, response);
		BookDao objBookDao = new BookDao();
		CategoryDao objCatDao = new CategoryDao();
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		int catId = Integer.parseInt(request.getParameter("category"));
		String sNumber = request.getParameter("number");
		String sPrice = request.getParameter("price");
		String description = request.getParameter("description");
		String detail = request.getParameter("detail");
		request.setAttribute("name", name);
		request.setAttribute("author", author);
		request.setAttribute("catId", catId);
		request.setAttribute("number", sNumber);
		request.setAttribute("price", sPrice);
		request.setAttribute("description", description);
		request.setAttribute("detail", detail);
		if (name.equals("")) {
			request.setAttribute("err", "Tên sách không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (objBookDao.checkName(name)) {
			request.setAttribute("err", "Tên sách đã tồn tại!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (author.equals("")) {
			request.setAttribute("err", "Tác giả không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (sNumber.equals("")) {
			request.setAttribute("err", "Số lượng không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		int number = 0;
		try {
			number = Integer.parseInt(sNumber);
			if (number <= 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			request.setAttribute("err", "Số lượng là số nguyên > 0!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (sPrice.equals("")) {
			request.setAttribute("err", "Giá bán không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (!sPrice.endsWith("000")) {
			request.setAttribute("err", "Giá bán không hợp lệ!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		int price = 0;
		try {
			price = Integer.parseInt(sPrice);
			if (price <= 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			request.setAttribute("err", "Giá bán là số nguyên > 0!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (description.equals("")) {
			request.setAttribute("err", "Mô tả không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (detail.equals("")) {
			request.setAttribute("err", "Chi tiết không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		String fileName = FileUtil.upload("picture", request);
		if (fileName.equals("")) {
			request.setAttribute("err", "Vui lòng chọn file hình ảnh!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
			rd.forward(request, response);
			return;
		}
		Category cat = objCatDao.getCat(catId);
		Book objBook = new Book(0, name, author, description, detail, number, price, 0, fileName, cat);
		if (objBookDao.add(objBook) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/book?msg=1");
			return;
		}
		request.setAttribute("err", "Có lỗi xảy ra khi thêm!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/add.jsp");
		rd.forward(request, response);
		
	}

}
