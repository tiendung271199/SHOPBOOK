package controllers.admin.sale;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.BookDao;
import daos.SaleOffDao;
import models.Book;
import models.SaleOff;
import models.User;
import utils.CheckUtil;

public class AdminAddSaleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddSaleController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.SALE_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/sale/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDao objBookDao = new BookDao();
		SaleOffDao objSaleDao = new SaleOffDao();
		String name = request.getParameter("name");
		String sSale = request.getParameter("sale");
		request.setAttribute("name", name);
		request.setAttribute("sale", sSale);
		if (name.equals("")) {
			request.setAttribute("err", "Tên sách không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/sale/add.jsp");
			rd.forward(request, response);
			return;
		}

		Book objBook = objBookDao.getBookName2(name);
		if (objBook == null) {
			request.setAttribute("err", "Tên sách không tồn tại!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/sale/add.jsp");
			rd.forward(request, response);
			return;
		}

		if (sSale.equals("")) {
			request.setAttribute("err", "Phần trăm sale không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/sale/add.jsp");
			rd.forward(request, response);
			return;
		}

		int sale = 0;
		try {
			sale = Integer.parseInt(sSale);
			if (sale > 99 || sale < 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			request.setAttribute("err", "Phần trăm sale là số nguyên từ 1 đến 99!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/sale/add.jsp");
			rd.forward(request, response);
			return;
		}
		
		SaleOff saleAdd = new SaleOff(0, objBook.getId(), sale);
		if (objSaleDao.checkSale(objBook.getId())) {
			SaleOff objSale3 = objSaleDao.getSale(objBook.getId());
			saleAdd.setId(objSale3.getId());
			if (objSaleDao.edit(saleAdd) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/sale?msg=1");
				return;
			}
		}
		if (objSaleDao.add(saleAdd) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/sale?msg=1");
			return;
		}
		request.setAttribute("err", "Có lỗi xảy ra!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/sale/add.jsp");
		rd.forward(request, response);

	}

}
