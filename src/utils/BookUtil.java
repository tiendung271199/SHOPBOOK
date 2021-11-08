package utils;

import java.util.ArrayList;

import daos.BookDao;
import daos.SaleOffDao;
import models.Book;
import models.SaleOff;

public class BookUtil {
	public static ArrayList<Book> listBookSale() {
		ArrayList<Book> listBookSale = new ArrayList<Book>();
		SaleOffDao objSaleOffDao = new SaleOffDao();
		BookDao objBookDao = new BookDao();
		ArrayList<SaleOff> listSale = objSaleOffDao.getAll();
		if (listSale.size() > 0) {
			for (SaleOff sale : listSale) {
				Book objBook = objBookDao.getBook(sale.getIdBook());
				listBookSale.add(objBook);
			}
		}
		return listBookSale;
	}
	
	// lọc sản phẩm nổi bật
	public static ArrayList<HighlightsUtil> listHighlights(ArrayList<HighlightsUtil> listHighlights, ArrayList<Book> listBook, HighlightsUtil obj) {
		if (listBook.size() > 0) {
			int check = 1;
			for (Book book : listBook) {
				if (check > 4) {
					break;
				}
				int k = 0;
				if (listHighlights.size() > 0) {
					for (HighlightsUtil highLights : listHighlights) {
						if (highLights.getIdBook() == book.getId()) {
							k = 1;
							if (obj.getMoi() == 1) {
								highLights.setMoi(1);
							}
							if (obj.getMua() == 1) {
								highLights.setMua(1);
							}
							if (obj.getDanhGia() == 1) {
								highLights.setDanhGia(1);
							}
							if (obj.getYeuThich() == 1) {
								highLights.setYeuThich(1);
							}
						}
					}
				}
				if (k == 0) {
					HighlightsUtil obj2 = new HighlightsUtil(book.getId(), obj.getMua(), obj.getMoi(), obj.getDanhGia(), obj.getYeuThich());
					listHighlights.add(obj2);
				}
				check++;
			}
		}
		return listHighlights;
	}
	
}
