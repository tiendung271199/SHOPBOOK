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

public class AdminAddWordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddWordController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/forbiddenword/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ForbiddenWordDao objWordDao = new ForbiddenWordDao();
		String word = request.getParameter("word");
		request.setAttribute("word", word);
		if (word.equals("")) {
			request.setAttribute("err", "Từ cấm không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/forbiddenword/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (objWordDao.checkWord(word)) {
			request.setAttribute("err", "Từ cấm đã tồn tại!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/forbiddenword/add.jsp");
			rd.forward(request, response);
			return;
		}
		ForbiddenWord objWord = new ForbiddenWord(0, word);
		if (objWordDao.add(objWord) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/forbiddenword?msg=1");
			return;
		}
		request.setAttribute("err", "Có lỗi xảy ra khi thêm!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/forbiddenword/add.jsp");
		rd.forward(request, response);
		
	}

}
