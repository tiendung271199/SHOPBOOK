package controllers.publics.status;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.ReviewsDao;
import models.Reviews;
import models.User;
import utils.GetObjUtil;
import utils.PublicUtil;
import utils.StringUtil;

public class PublicReviewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicReviewsController() {
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
		PrintWriter out = response.getWriter();
		ReviewsDao objReviewsDao = new ReviewsDao();
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		int idUser = userLogin.getId();
		int idBook = 0;
		int check = 0;
		double star = 0.0d;
		String cmt = "";
		if (request.getParameter("aBIdReviews") != null || request.getParameter("aIdEdit") != null) {
			String sStar = request.getParameter("aStar");
			cmt = request.getParameter("aCmt");
			try {
				star = Double.parseDouble(sStar);
				if (star > 5.0d || star < 0.5d) {
					throw new Exception();
				}
			} catch (Exception e) {
				check = 1;
				out.print(
						"<p style='color: red; font-style: italic; border-bottom: 1px solid #CCC; padding-bottom: 12px'>Số sao là số thực > 0.5 và < 5!</p>");
			}
			star = PublicUtil.getStar(star);

			if (check == 0) {
				if (cmt.equals("")) {
					check = 1;
					out.print(
							"<p style='color: red; font-style: italic; border-bottom: 1px solid #CCC; padding-bottom: 12px'>Nội dung đánh giá không được rỗng!</p>");
				}
			}

			if (check == 0) {
				if (StringUtil.checkWord(cmt)) {
					check = 1;
					out.print(
							"<p style='color: red; font-style: italic; border-bottom: 1px solid #CCC; padding-bottom: 12px'>Nội dung đánh giá không hợp lệ!</p>");
				}
			}
		}
		if (request.getParameter("aBIdReviews") != null) {
			idBook = Integer.parseInt(request.getParameter("aBIdReviews"));
			if (check == 0) {
				Reviews reviews = new Reviews(idUser, idBook, star, cmt);
				if (objReviewsDao.add(reviews) > 0) {
					out.print(
							"<p style='color: blue; font-style: italic; border-bottom: 1px solid #CCC; padding-bottom: 12px'>Cảm ơn bạn đã đánh giá!</p>");
				}
			}
		}
		if (request.getParameter("aIdEdit") != null) {
			int id = Integer.parseInt(request.getParameter("aIdEdit"));
			Reviews objReviews = objReviewsDao.getReviewsId(id);
			idBook = objReviews.getIdBook();
			if (check == 0) {
				objReviews.setStar(star);
				objReviews.setComment(cmt);
				if (objReviewsDao.edit(objReviews) > 0) {
					out.print(
							"<p style='color: blue; font-style: italic; border-bottom: 1px solid #CCC; padding-bottom: 12px'>Sửa đánh giá thành công!</p>");
				}
			}
		}
		if (request.getParameter("aIdDelete") != null) {
			int id = Integer.parseInt(request.getParameter("aIdDelete"));
			Reviews objReviews = objReviewsDao.getReviewsId(id);
			idBook = objReviews.getIdBook();
			if (objReviewsDao.delete(id) > 0) {
				out.print(
						"<p style='color: blue; font-style: italic; border-bottom: 1px solid #CCC; padding-bottom: 12px'>Xoá đánh giá thành công!</p>");
			}
		}
		ArrayList<Reviews> listReviews = objReviewsDao.getReviews(idBook);
		if (listReviews.size() > 0) {
			out.print("<h6>Một số đánh giá</h6>");
			for (Reviews objReviews : listReviews) {
				User objUser = GetObjUtil.getUser(objReviews.getIdUser());
				double star2 = objReviews.getStar();
				out.print("<div style=\"border-bottom: 1px solid #CCC; margin-bottom: 10px\">");
				out.print("<p>Tên: " + objUser.getFullname() + "</p>");
				out.print("<p>Nhận xét vào " + StringUtil.tachChuoi(objReviews.getDateCreate()) + "</p>");
				out.print("<p>" + star2 + "<i class=\"fa fa-star\"></i> - ");
				if (star2 <= 1.0d) {
					out.print("Rất không hài lòng");
				} else if (star2 > 1.0d && star2 <= 2.0d) {
					out.print("Không hài lòng");
				} else if (star2 > 2.0d && star2 <= 3.5d) {
					out.print("Bình thường");
				} else if (star2 > 3.5d && star2 < 4.5d) {
					out.print("Hài lòng");
				} else {
					out.print("Cực kỳ hài lòng");
				}
				out.print("</p>");
				out.print("<p>Nội dung đánh giá: " + objReviews.getComment() + "</p>");
				if (idUser == objReviews.getIdUser()) {
					out.print("<p><a onclick=\"editReviews(" + objReviews.getId()
							+ ")\" style=\"padding: 5px 10px; background-color: #CCC\" href=\"javascript:void(0)\">Sửa</a>");
					out.print("<a onclick=\"deleteReviews(" + objReviews.getId()
							+ ")\" style=\"padding: 5px 10px; background-color: #CCC; margin-left: 5px\" href=\"javascript:void(0)\">Xoá</a></p>");
				}
				out.print("</div>");
			}
		}

	}

}
