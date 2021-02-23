package utils;

import daos.FavouriteDao;
import daos.OrderDao;
import daos.RoleDao;

public class CheckUtil {
	public static boolean checkUserOrder(int idUser) {
		OrderDao objOrderDao = new OrderDao();
		if (objOrderDao.checkUserOrder(idUser)) {
			return true;
		}
		return false;
	}
	
	public static boolean checkFavouriteStatus(int idBook, int idUser) {
		FavouriteDao objFavouriteDao = new FavouriteDao();
		if (objFavouriteDao.checkFavouriteStatus(idBook, idUser)) {
			return true;
		}
		return false;
	}
	
	public static boolean checkRole(int idUser, int idModule) {
		RoleDao objRoleDao = new RoleDao();
		if (objRoleDao.checkRole2(idUser, idModule)) {
			return true;
		}
		return false;
	}
}
