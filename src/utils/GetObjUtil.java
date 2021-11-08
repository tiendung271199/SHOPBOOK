package utils;

import java.util.ArrayList;

import daos.BookDao;
import daos.CategoryDao;
import daos.ModuleDao;
import daos.ReviewsDao;
import daos.SaleOffDao;
import daos.UserDao;
import models.Book;
import models.Category;
import models.Modules;
import models.Reviews;
import models.SaleOff;
import models.User;

public class GetObjUtil {
	public static User getUser(int idUser) {
		UserDao objUserDao = new UserDao();
		User objUser = objUserDao.getUser(idUser);
		return objUser;
	}
	
	public static Book getBook(int idBook) {
		BookDao objBookDao = new BookDao();
		Book objBook = objBookDao.getBook(idBook);
		return objBook;
	}
	
	public static Category getCat(int idCat) {
		CategoryDao objCatDao = new CategoryDao();
		Category objCat = objCatDao.getCat(idCat);
		return objCat;
	}
	
	public static ArrayList<Category> getCatParent(int idCat) {
		CategoryDao objCatDao = new CategoryDao();
		ArrayList<Category> listCat = objCatDao.getCatParent(idCat);
		return listCat;
	}
	
	public static ArrayList<Reviews> getReviews(int idBook) {
		ReviewsDao objReviewsDao = new ReviewsDao();
		ArrayList<Reviews> listReviews = objReviewsDao.getBestReviews(idBook);
		return listReviews;
	}
	
	public static SaleOff getSale(int idBook) {
		SaleOffDao objSaleDao = new SaleOffDao();
		SaleOff objSale = objSaleDao.getSale(idBook);
		return objSale;
	}
	
	public static ArrayList<Modules> getListModule() {
		ModuleDao objModuleDao = new ModuleDao();
		ArrayList<Modules> listModule = objModuleDao.getAll();
		return listModule;
	}
	
}
