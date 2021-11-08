package utils;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.BookDao;
import daos.FavouriteDao;
import daos.ReviewsDao;
import models.Book;
import models.Reviews;
import models.User;

public class PublicUtil {

	public static void getCountFavourite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		FavouriteDao objFavouriteDao = new FavouriteDao();
		int favourite = objFavouriteDao.getCountFavouriteUser(userLogin.getId());
		request.setAttribute("favouriteUser", favourite);
	}

	public static double starTB(ArrayList<Reviews> list) {
		double star = 0;
		if (list.size() > 0) {
			for (Reviews reviews : list) {
				star += reviews.getStar();
			}
			star = star / list.size();
		}
		return star;
	}

	public static void swap(FavoutiteUtil obj1, FavoutiteUtil obj2) {
		FavoutiteUtil obj = new FavoutiteUtil();
		obj.setId(obj1.getId());
		obj.setFavourite(obj1.getFavourite());
		obj1.setId(obj2.getId());
		obj1.setFavourite(obj2.getFavourite());
		obj2.setId(obj.getId());
		obj2.setFavourite(obj.getFavourite());
	}

	public static ArrayList<FavoutiteUtil> sort(ArrayList<FavoutiteUtil> list) {
		if (list.size() > 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = i; j < list.size(); j++) {
					if (list.get(i).getFavourite() < list.get(j).getFavourite()) {
						swap(list.get(i), list.get(j));
					}
				}
			}
		}
		return list;
	}

	public static ArrayList<Book> getBestFavourite(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ArrayList<Book> listBookFavourite = new ArrayList<Book>();
		FavouriteDao objFavouriteDao = new FavouriteDao();
		BookDao objBookDao = new BookDao();
		ArrayList<Book> listBook = objBookDao.getAll();
		ArrayList<FavoutiteUtil> listFavUtil = new ArrayList<FavoutiteUtil>();
		if (listBook.size() > 0) {
			for (Book book : listBook) {
				int favourites = objFavouriteDao.getFavouriteCount(book.getId());
				FavoutiteUtil favUtil = new FavoutiteUtil(book.getId(), favourites);
				listFavUtil.add(favUtil);
			}
		}
		ArrayList<FavoutiteUtil> listSort = sort(listFavUtil);
		if (listSort.size() > 0) {
			int k = 1;
			for (FavoutiteUtil favoutiteUtil : listSort) {
				if (k > 6) {
					break;
				}
				Book bookFav = objBookDao.getBook(favoutiteUtil.getId());
				listBookFavourite.add(bookFav);
				k++;
			}
		}
		return listBookFavourite;
	}

	public static void swap(ReviewsObjUtil obj1, ReviewsObjUtil obj2) {
		ReviewsObjUtil obj = new ReviewsObjUtil();
		obj.setId(obj1.getId());
		obj.setStar(obj1.getStar());
		obj1.setId(obj2.getId());
		obj1.setStar(obj2.getStar());
		obj2.setId(obj.getId());
		obj2.setStar(obj.getStar());
	}

	public static ArrayList<ReviewsObjUtil> sort2(ArrayList<ReviewsObjUtil> list) {
		if (list.size() > 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = i; j < list.size(); j++) {
					if (list.get(i).getStar() < list.get(j).getStar()) {
						swap(list.get(i), list.get(j));
					}
				}
			}
		}
		return list;
	}

	public static ArrayList<Book> getBestReviews(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ArrayList<Book> listBookReviews = new ArrayList<Book>();
		ReviewsDao objReviewsDao = new ReviewsDao();
		BookDao objBookDao = new BookDao();
		ArrayList<Book> listBook = objBookDao.getAll();
		ArrayList<ReviewsObjUtil> listReviewsUtil = new ArrayList<ReviewsObjUtil>();
		if (listBook.size() > 0) {
			for (Book book : listBook) {
				ArrayList<Reviews> listReviews = objReviewsDao.getBestReviews(book.getId());
				double star = starTB(listReviews);
				ReviewsObjUtil rou = new ReviewsObjUtil(book.getId(), star);
				listReviewsUtil.add(rou);
			}
		}
		ArrayList<ReviewsObjUtil> listSort = sort2(listReviewsUtil);
		if (listSort.size() > 0) {
			int k = 1;
			for (ReviewsObjUtil bestReviews : listSort) {
				if (k > 6) {
					break;
				}
				Book bookRev = objBookDao.getBook(bestReviews.getId());
				listBookReviews.add(bookRev);
				k++;
			}
		}
		return listBookReviews;
	}

	public static double getStar(double star) {
		double rs = 0.0d;
		if (star <= 5.0 && star >= 4.75) {
			rs = 5.0d;
		} else if (star < 4.75 && star > 4.25) {
			rs = 4.5d;
		} else if (star <= 4.25 && star >= 3.75) {
			rs = 4.0d;
		} else if (star < 3.75 && star > 3.25) {
			rs = 3.5d;
		} else if (star <= 3.25 && star >= 2.75) {
			rs = 3.0d;
		} else if (star < 2.75 && star > 2.25) {
			rs = 2.5d;
		} else if (star <= 2.25 && star >= 1.75) {
			rs = 2.0d;
		} else if (star < 1.75 && star > 1.25) {
			rs = 1.5d;
		} else if (star <= 1.25 && star >= 0.75) {
			rs = 1.0d;
		} else {
			rs = 0.5d;
		}
		return rs;
	}
	
}
