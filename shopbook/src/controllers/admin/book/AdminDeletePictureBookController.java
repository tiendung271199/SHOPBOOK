package controllers.admin.book;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
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
import utils.StringUtil;

public class AdminDeletePictureBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeletePictureBookController() {
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
		String fileName = request.getParameter("filename");
		ArrayList<String> listFileName = StringUtil.getFileName(objBook.getPicture());
		if (listFileName.size() == 1) {
			response.sendRedirect(request.getContextPath() + "/admin/book/picture?id=" + id + "&err=1");
			return;
		}
		if (listFileName.size() > 1) {
			for (int i = 0; i < listFileName.size(); i++) {
				if (listFileName.get(i).equals(fileName)) {
					listFileName.remove(i);
					break;
				}
			}
		}
		String picture = "";
		if (listFileName.size() > 0) {
			for (int i = 0; i < listFileName.size(); i++) {
				if (i == 0) {
					picture += listFileName.get(i);
				} else {
					picture += "/" + listFileName.get(i);
				}
			}
		}
		if (objBookDao.editPicture(picture, id) > 0) {
			FileUtil.delFile(fileName, request);
			response.sendRedirect(request.getContextPath() + "/admin/book/picture?id=" + id + "&msg=2");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/book/picture?id=" + id + "&err=2");
		return;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
