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

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddCatController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryUtil.getCat(request, response);
		CategoryDao objCatDao = new CategoryDao();
		String name = request.getParameter("name");
		int parentId = Integer.parseInt(request.getParameter("catParent"));
		request.setAttribute("name", name);
		request.setAttribute("parentId", parentId);
		if (name.equals("")) {
			request.setAttribute("err", "Tên danh mục không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (StringUtil.checkWord(name)) {
			request.setAttribute("err", "Tên danh mục không hợp lệ!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (objCatDao.checkName(name)) {
			request.setAttribute("err", "Tên danh mục đã tồn tại!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp");
			rd.forward(request, response);
			return;
		}
		Category objCat = new Category(0, name, parentId);
		if (objCatDao.add(objCat) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=1");
			return;
		}
		request.setAttribute("err", "Có lỗi xảy ra khi thêm!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp");
		rd.forward(request, response);
		
	}

}
