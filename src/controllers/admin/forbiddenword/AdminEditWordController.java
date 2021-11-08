package controllers.admin.forbiddenword;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.ForbiddenWordDao;
import models.ForbiddenWord;
import models.User;
import utils.CheckUtil;

public class AdminEditWordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditWordController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.WORD_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
		ForbiddenWordDao objWordDao = new ForbiddenWordDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objWordDao.checkId(id)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/forbiddenword?err=1");
			return;
		}
		ForbiddenWord objWord = objWordDao.getWord(id);
		request.setAttribute("objWord", objWord);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/forbiddenword/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ForbiddenWordDao objWordDao = new ForbiddenWordDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("idWord"));
		} catch (Exception e) {
			request.setAttribute("err", "ID không tồn tại!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/forbiddenword/edit.jsp");
			rd.forward(request, response);
			return;
		}
		String word = request.getParameter("word");
		ForbiddenWord objWord = new ForbiddenWord(id, word);
		request.setAttribute("objWord", objWord);
		if (word.equals("")) {
			request.setAttribute("err", "Từ cấm không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/forbiddenword/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (objWordDao.checkWord(word)) {
			request.setAttribute("err", "Từ cấm đã tồn tại!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/forbiddenword/edit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (objWordDao.edit(objWord) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/forbiddenword?msg=2");
			return;
		}
		request.setAttribute("err", "Có lỗi xảy ra khi sửa!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/forbiddenword/edit.jsp");
		rd.forward(request, response);
		
	}

}
