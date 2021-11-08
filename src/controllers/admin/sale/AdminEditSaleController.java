package controllers.admin.sale;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.SaleOffDao;
import models.SaleOff;
import models.User;
import utils.CheckUtil;

public class AdminEditSaleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditSaleController() {
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
		SaleOffDao objSaleDao = new SaleOffDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			if (!objSaleDao.checkId(id)) {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/sale?err=1");
			return;
		}
		SaleOff objSale = objSaleDao.getSaleId(id);
		request.setAttribute("objSale", objSale);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/sale/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SaleOffDao objSaleDao = new SaleOffDao();
		int id = Integer.parseInt(request.getParameter("idSale"));
		String sSale = request.getParameter("sale");
		SaleOff objSale = objSaleDao.getSaleId(id);
		request.setAttribute("objSale", objSale);
		
		if (sSale.equals("")) {
			request.setAttribute("err", "Phần trăm sale không được rỗng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/sale/edit.jsp");
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
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/sale/edit.jsp");
			rd.forward(request, response);
			return;
		}
		objSale.setSale(sale);
		request.setAttribute("objSale", objSale);
		
		if (objSaleDao.edit(objSale) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/sale?msg=2");
			return;
		}
		request.setAttribute("err", "Có lỗi xảy ra!");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/sale/edit.jsp");
		rd.forward(request, response);
		
	}

}
