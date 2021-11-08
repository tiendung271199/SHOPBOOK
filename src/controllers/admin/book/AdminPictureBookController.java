package controllers.admin.book;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.BookDao;
import models.Book;
import models.User;
import utils.CheckUtil;
import utils.FileUtil;

@MultipartConfig
public class AdminPictureBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AdminPictureBookController() {
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
		if (request.getParameter("msg") != null) {
			int msg = Integer.parseInt(request.getParameter("msg"));
			if (msg == 1) {
				request.setAttribute("success", "Thêm thành công!");
			} else {
				request.setAttribute("success", "Xoá thành công!");
			}
		}
		if (request.getParameter("err") != null) {
			int err = Integer.parseInt(request.getParameter("err"));
			if (err == 1) {
				request.setAttribute("err", "Không thể xoá!");
			} else {
				request.setAttribute("err", "Có lỗi xảy ra!");
			}
		}
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
		session.setAttribute("idPictureBook", id);
		Book objBook = objBookDao.getBook(id);
		request.setAttribute("objBook", objBook);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/picture.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		BookDao objBookDao = new BookDao();
		int id = 0;
		if (session.getAttribute("idPictureBook") != null) {
			id = (int) session.getAttribute("idPictureBook");
		}
		Book objBook = objBookDao.getBook(id);
		request.setAttribute("objBook", objBook);
		session.removeAttribute("idPictureBook");
		ArrayList<String> listPicture = FileUtil.uploadMultipleFile(request);
		String newFileName = "";
		if (listPicture.size() > 0) {
			for (String picture : listPicture) {
				newFileName += "/" + picture;
			}
		} else {
			request.setAttribute("err", "Vui lòng chọn file hình ảnh!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/picture.jsp");
			rd.forward(request, response);
			return;
		}
		String picture = objBook.getPicture() + newFileName;
		if (objBookDao.editPicture(picture, id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/book/picture?id=" + id + "&msg=1");
			return;
		}
		request.setAttribute("err", "Có lỗi xảy ra!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/book/picture.jsp");
		rd.forward(request, response);

	}

}
