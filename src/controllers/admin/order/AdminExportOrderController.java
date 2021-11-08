package controllers.admin.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.OrderDao;
import models.Order;
import models.User;
import utils.CheckUtil;
import utils.ExcelUtil;
import utils.StringUtil;

public class AdminExportOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminExportOrderController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		int idModule = GlobalConstant.ORDER_MODULE;
		if (adminLogin.getRole() != 2) {
			if (!CheckUtil.checkRole(adminLogin.getId(), idModule)) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=3");
				return;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderDao objOrderDao = new OrderDao();
		int numberPage = GlobalConstant.NUMBER_PAGE2;
		int first = 0, last = 0, pages = 1, totalPages = 1;
		int total = objOrderDao.getCount();
		if (total % numberPage == 0) {
			totalPages = total / numberPage;
		} else {
			totalPages = total / numberPage + 1;
		}
		if (total <= numberPage) {
			first = 0;
			last = total;
		} else {
			first = (pages - 1) * numberPage;
			last = numberPage;
		}
		ArrayList<Order> listOrder2 = objOrderDao.getOrder(first, last);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("total", total);
		request.setAttribute("pages", pages);
		request.setAttribute("first", first);
		request.setAttribute("last", last);
		request.setAttribute("listOrder", listOrder2);
		
		int thang = Integer.parseInt(request.getParameter("month"));
		String sYear = request.getParameter("year");
		int year = 0;
		if (thang == 0) {
			request.setAttribute("err", "Vui lòng chọn tháng trước khi xuất danh sách đơn hàng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/order/index.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (sYear.equals("")) {
			request.setAttribute("err", "Vui lòng nhập năm trước khi xuất danh sách đơn hàng!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/order/index.jsp");
			rd.forward(request, response);
			return;
		}
		
		try {
			year = Integer.parseInt(sYear);
			if (year < 2020) {
				throw new Exception();
			}
		} catch (Exception e) {
			request.setAttribute("err", "Vui lòng nhập năm là số nguyên, từ năm 2020 trở đi!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/order/index.jsp");
			rd.forward(request, response);
			return;
		}
		ArrayList<Order> listOrderExport = new ArrayList<Order>();
		ArrayList<Order> listOrder = objOrderDao.getAll();
		if (listOrder.size() > 0) {
			for (Order order : listOrder) {
				int m = StringUtil.getMonth(order.getDateCreate());
				int y = StringUtil.getYear(order.getDateCreate());
				if (y == year && m == thang) {
					listOrderExport.add(order);
				}
			}
		}
		if (listOrderExport.size() > 0) {
			ExcelUtil.exportOrder(listOrderExport);
			response.sendRedirect(request.getContextPath() + "/admin/order?msg=2");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/order?msg=3");
			return;
		}
		
	}

}
