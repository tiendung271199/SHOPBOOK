package controllers.admin.cat;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.CategoryDao;
import models.Category;
import models.User;
import utils.CategoryUtil;
import utils.CheckUtil;
import utils.StringUtil;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.CAT_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
		CategoryUtil.getCat(request, response);
		CategoryDao objCatDao = new CategoryDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objCatDao.checkId(id)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/cat?err=1");
			return;
		}
		Category objCat = objCatDao.getCat(id);
		request.setAttribute("cat", objCat);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryUtil.getCat(request, response);
		CategoryDao objCatDao = new CategoryDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("catid"));
		} catch (Exception e) {
			request.setAttribute("err", "ID không tồn tại!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
			rd.forward(request, response);
			return;
		}
		Category oldCat = objCatDao.getCat(id);
		String name = request.getParameter("name");
		int parentId = Integer.parseInt(request.getParameter("catParent"));
		Category objCat = new Category(id, name, parentId);
		request.setAttribute("cat", objCat);
		if (name.equals("")) {
			request.setAttribute("err", "Tên danh mục không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (StringUtil.checkWord(name)) {
			request.setAttribute("err", "Tên danh mục không hợp lệ!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (objCatDao.checkName(name, oldCat.getName())) {
			request.setAttribute("err", "Tên danh mục đã tồn tại!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (objCatDao.edit(objCat) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=2");
			return;
		}
		request.setAttribute("err", "Có lỗi xảy ra khi sửa!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
		rd.forward(request, response);
		
	}

}
