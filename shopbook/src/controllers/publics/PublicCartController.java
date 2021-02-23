package controllers.publics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Cart;
import models.CartDetail;
import models.User;
import utils.AuthUtil;
import utils.CategoryUtil;
import utils.PublicUtil;

public class PublicCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicCartController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		if (AuthUtil.checkPublicLogin(request, response)) {
			PublicUtil.getCountFavourite(request, response);
		}
		request.setAttribute("activePage", 4);
		CategoryUtil.getCat(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/giohang.jsp");
		rd.forward(request, response);
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
		
		// Tăng số lượng cart detail
		if (request.getParameter("aBIdInc") != null) {
			int bId = Integer.parseInt(request.getParameter("aBIdInc"));
			Cart objCart = (Cart) session.getAttribute(kCart);
			ArrayList<CartDetail> list = objCart.getList();
			if (list.size() > 0) {
				for (CartDetail cartDetail : list) {
					if (bId == cartDetail.getIdBook()) {
						int nNum = cartDetail.getNumber() + 1;
						cartDetail.setNumber(nNum);
						out.print(nNum);
						break;
					}
				}
			}
			objCart.setNumber(objCart.getNumber() + 1);
			session.setAttribute(kCart, objCart);
		}

		// Giảm số lượng cart detail
		if (request.getParameter("aBIdDec") != null) {
			int bId = Integer.parseInt(request.getParameter("aBIdDec"));
			Cart objCart = (Cart) session.getAttribute(kCart);
			ArrayList<CartDetail> list = objCart.getList();
			if (list.size() > 0) {
				for (CartDetail cartDetail : list) {
					if (bId == cartDetail.getIdBook()) {
						int nNum = cartDetail.getNumber() - 1;
						cartDetail.setNumber(nNum);
						out.print(nNum);
						break;
					}
				}
			}
			objCart.setNumber(objCart.getNumber() - 1);
			session.setAttribute(kCart, objCart);
		}

		// Xoá cart detail
		if (request.getParameter("aBIdDel") != null) {
			int bId = Integer.parseInt(request.getParameter("aBIdDel"));
			Cart objCart = (Cart) session.getAttribute(kCart);
			ArrayList<CartDetail> list = objCart.getList();
			int numDel = 0;
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getIdBook() == bId) {
						numDel = list.get(i).getNumber();
						list.remove(i);
						break;
					}
				}
			}
			objCart.setNumber(objCart.getNumber() - numDel);
			out.print(objCart.getNumber());
			session.setAttribute(kCart, objCart);
		}

	}

}
