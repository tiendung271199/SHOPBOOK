package controllers.publics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Cart;
import models.CartDetail;
import models.User;

public class PublicAddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicAddToCartController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String kCart = "cart"; // key của session lưu giỏ hàng
		int idUser = 0;
		if (session.getAttribute("userLogin") != null) {
			User userLogin = (User) session.getAttribute("userLogin");
			idUser = userLogin.getId();
			kCart += idUser;
		}
		
		// Add to cart
		if (request.getParameter("aBId") != null) {
			int number = 1;
			int bId = Integer.parseInt(request.getParameter("aBId")); // bId (idBook)
			if (request.getParameter("aNum") != null) {
				number = Integer.parseInt(request.getParameter("aNum"));
			}
			if (session.getAttribute(kCart) == null) {
				ArrayList<CartDetail> list = new ArrayList<CartDetail>();
				CartDetail objCartDetail = new CartDetail(bId, number);
				list.add(objCartDetail);
				Cart objCart = new Cart(idUser, list, number);
				session.setAttribute(kCart, objCart);
				out.print(number);
			} else {
				Cart objCart = (Cart) session.getAttribute(kCart);
				ArrayList<CartDetail> list = objCart.getList();
				int check = 0;
				if (list.size() > 0) {
					for (CartDetail cartDetail : list) {
						if (bId == cartDetail.getIdBook()) {
							int nNum = cartDetail.getNumber() + number;
							cartDetail.setNumber(nNum);
							check = 1;
							break;
						}
					}
				}
				if (check == 0) {
					CartDetail objCartDetail = new CartDetail(bId, number);
					list.add(objCartDetail);
				}
				objCart.setNumber(objCart.getNumber() + number);
				session.setAttribute(kCart, objCart);
				out.print(objCart.getNumber());
			}
		}

	}

}
