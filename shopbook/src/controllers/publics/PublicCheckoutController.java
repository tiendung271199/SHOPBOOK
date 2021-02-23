package controllers.publics;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstant;
import daos.BookDao;
import daos.OrderDao;
import daos.OrderDetailDao;
import daos.SaleOffDao;
import models.Book;
import models.Cart;
import models.CartDetail;
import models.Order;
import models.OrderDetail;
import models.OrderManage;
import models.OrderManageDetail;
import models.SaleOff;
import models.User;
import utils.AuthUtil;
import utils.CategoryUtil;
import utils.EmailUtil;
import utils.PublicUtil;
import utils.StringUtil;

public class PublicCheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicCheckoutController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (AuthUtil.checkPublicLogin(request, response)) {
			PublicUtil.getCountFavourite(request, response);
		}
		request.setAttribute("activePage", 4);
		CategoryUtil.getCat(request, response);
		User userLogin = (User) session.getAttribute("userLogin");
		Cart objCart = null;
		if (userLogin != null) {
			objCart = (Cart) session.getAttribute("cart" + userLogin.getId());
		} else {
			objCart = (Cart) session.getAttribute("cart");
		}
		if (objCart == null) {
			response.sendRedirect(request.getContextPath() + "/cart");
			return;
		} else {
			ArrayList<CartDetail> list = objCart.getList();
			if (list.size() < 1) {
				response.sendRedirect(request.getContextPath() + "/cart");
				return;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/checkout.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		if (AuthUtil.checkPublicLogin(request, response)) {
			PublicUtil.getCountFavourite(request, response);
		}
		request.setAttribute("activePage", 4);
		CategoryUtil.getCat(request, response);
		HttpSession session = request.getSession();
		SaleOffDao objSaleDao = new SaleOffDao();
		BookDao objBookDao = new BookDao();
		OrderDao objOrderDao = new OrderDao();
		OrderDetailDao objOrderDetailDao = new OrderDetailDao();
		User userLogin = (User) session.getAttribute("userLogin");
		int idUser = 0;
		String name = "", phone = "", email = "", address = "", orderNote = "", kCart = "cart";
		if (userLogin != null) {
			kCart += userLogin.getId();
			idUser = userLogin.getId();
			name = userLogin.getFullname();
			phone = userLogin.getPhone();
			email = userLogin.getEmail();
			address = userLogin.getAddress();
		} else {
			name = request.getParameter("name");
			phone = request.getParameter("phone");
			email = request.getParameter("email");
			address = request.getParameter("address");
		}
		orderNote = request.getParameter("ghichu");
		Cart objCart = (Cart) session.getAttribute(kCart);
		ArrayList<CartDetail> list = objCart.getList();
		if (list.size() > 0) {
			// check trường hợp hết hàng
			for (CartDetail cartDetail : list) {
				Book objBook = objBookDao.getBook(cartDetail.getIdBook());
				if (cartDetail.getNumber() > objBook.getNumber()) {
					request.setAttribute("errOrder", "Shop còn " + objBook.getNumber() + " cuốn " + objBook.getName() + ", vui lòng mua với số lượng nhỏ hơn!");
					RequestDispatcher rd = request.getRequestDispatcher("/views/public/checkout.jsp");
					rd.forward(request, response);
					return;
				}
			}
		}
		Order objOrder = new Order(idUser, name, phone, email, address, orderNote);
		if (objOrderDao.add(objOrder) > 0) {
			Order newOrder = objOrderDao.getOrderNew();
			int idOrder = newOrder.getId();
			if (list.size() > 0) {
				for (CartDetail cartDetail : list) {
					Book objBook = objBookDao.getBook(cartDetail.getIdBook());
					int sale = 0;
					SaleOff objSale = objSaleDao.getSale(objBook.getId());
					if (objSale != null) {
						sale = objSale.getSale();
					}
					OrderDetail objOrderDetail = new OrderDetail(0, idOrder, cartDetail.getNumber(), objBook.getId(), sale, objBook.getPrice());
					if (objOrderDetailDao.add(objOrderDetail) > 0) {
						// set lại số lượng và lượt mua của book
						int newNum = objBook.getNumber() - cartDetail.getNumber();
						int newPur = objBook.getPurchases() + cartDetail.getNumber();
						if (objBookDao.edit(newNum, newPur, objBook.getId()) > 0) {
							System.out.print("");
						}
					}
				}
			}
			// thêm đơn hàng vào session để user quản lý đơn hàng
			if (userLogin != null) {
				String kOrder = "order" + newOrder.getIdUser();
				ArrayList<OrderManage> listOrderManage = new ArrayList<OrderManage>();
				if (session.getAttribute(kOrder) != null) {
					listOrderManage = (ArrayList<OrderManage>) session.getAttribute(kOrder);
				}
				ArrayList<OrderManageDetail> listOrderManageDetail = new ArrayList<OrderManageDetail>();
				ArrayList<OrderDetail> listOrderDetail = objOrderDetailDao.getOrderDetail(idOrder);
				if (listOrderDetail.size() > 0) {
					for (OrderDetail orderDetail : listOrderDetail) {
						OrderManageDetail orderManageDetail = new OrderManageDetail(orderDetail.getNumber(), orderDetail.getIdBook(), orderDetail.getSale(), orderDetail.getPrice());
						listOrderManageDetail.add(orderManageDetail);
					}
				}
				String ngayMua = StringUtil.tachChuoi(newOrder.getDateCreate());
				OrderManage orderManage = new OrderManage(idOrder, newOrder.getIdUser(), newOrder.getName(), newOrder.getPhone(), newOrder.getEmail(), newOrder.getAddress(), newOrder.getOrderNote(), ngayMua, newOrder.getStatus(), listOrderManageDetail);
				listOrderManage.add(orderManage);
				session.setAttribute(kOrder, listOrderManage);
			}
			// Gửi email xác nhận mua hàng cho khách hàng
			ArrayList<OrderDetail> listOrderDetail = objOrderDetailDao.getOrderDetail(idOrder);
			String subject = GlobalConstant.ORDER_SUBJECT;
			String emailUser = GlobalConstant.EMAIL_USER;
			String emailPass = GlobalConstant.EMAIL_PASS;
			String content = EmailUtil.emailContentOrder(listOrderDetail, name);
			EmailUtil.sendMail(email, subject, content, emailUser, emailPass);
		}
		
		if (userLogin == null) {
			response.sendRedirect(request.getContextPath() + "/checkout?msg=ordersuccess");
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/order/user?msg=ordersuccess");
		return;
	}

}
