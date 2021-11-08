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

public class AdminIndexOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexOrderController() {
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
		if (request.getParameter("msg") != null) {
			int msg = Integer.parseInt(request.getParameter("msg"));
			if (msg == 1) {
				request.setAttribute("success", "Xoá thành công!");
			} else if (msg == 2) {
				request.setAttribute("success", "Xuất đơn hàng thành công!");
			} else if (msg == 3) {
				request.setAttribute("success", "Không có đơn hàng trong tháng!");
			} else if (msg == 4) {
				request.setAttribute("success", "Cập nhật trạng thái đơn hàng thành công!");
			}
		}
		if (request.getParameter("err") != null) {
			int err = Integer.parseInt(request.getParameter("err"));
			if (err == 1) {
				request.setAttribute("err", "ID không tồn tại!");
			} else if (err == 4) {
				request.setAttribute("err", "Không thể huỷ đơn hàng của user!");
			} else if (err == 5) {
				request.setAttribute("err", "Đơn hàng đã được giao thành công!");
			} else if (err == 6) {
				request.setAttribute("err", "Đơn hàng đã bị huỷ!");
			} else {
				request.setAttribute("err", "Có lỗi xảy ra!");
			}
		}
		OrderDao objOrderDao = new OrderDao();
		int numOrder = objOrderDao.getCount();
		session.setAttribute("numOrder", numOrder);
		int numberPage = GlobalConstant.NUMBER_PAGE2;
		int first = 0, last = 0, pages = 1, totalPages = 1;
		int total = objOrderDao.getCount();
		if (total % numberPage == 0) {
			totalPages = total / numberPage;
		} else {
			totalPages = total / numberPage + 1;
		}
		if (request.getParameter("pages") != null) {
			try {
				pages = Integer.parseInt(request.getParameter("pages"));
				if (pages < 1 || pages > totalPages) {
					response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=2");
					return;
				}
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/views/admin/error/error.jsp?err=1");
				return;
			}
		}
		if (total <= numberPage) {
			first = 0;
			last = total;
		} else {
			first = (pages - 1) * numberPage;
			last = numberPage;
		}
		ArrayList<Order> listOrder = objOrderDao.getOrder(first, last);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("total", total);
		request.setAttribute("pages", pages);
		request.setAttribute("first", first);
		request.setAttribute("last", last);
		if (request.getParameter("sname") != null) {
			String sName = request.getParameter("sname");
			request.setAttribute("sname", sName);
			listOrder = objOrderDao.getSearchOrder(sName);
		}
		request.setAttribute("listOrder", listOrder);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/order/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
