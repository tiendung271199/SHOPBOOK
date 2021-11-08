package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import daos.BookDao;
import daos.CategoryDao;
import models.Book;
import models.Category;

public class CategoryUtil {
	public static void getCat(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CategoryDao objCategoryDao = new CategoryDao();
		ArrayList<Category> listCat = objCategoryDao.getAll();
		ArrayList<TheLoaiNoiBatUtil> listNoiBat = CategoryUtil.getListTheLoaiNoiBat();
		ArrayList<Category> listCatNoiBat = CategoryUtil.getListCat(listNoiBat);
		request.setAttribute("listCat", listCat);
		request.setAttribute("listCatNoiBat", listCatNoiBat);
	}

	// Hiển thị danh mục đa cấp - Sử dụng giải thuật đệ quy
	public static void showCat(HttpServletRequest request, JspWriter out, int parentId, String menu)
			throws IOException {
		CategoryDao objCatDao = new CategoryDao();
		ArrayList<Category> listCatCon = objCatDao.getCatParent(parentId);
		if (listCatCon.size() > 0) {
			out.println("<ul>");
			for (Category cat : listCatCon) {
				String urlCat = request.getContextPath() + "/danh-muc-san-pham/" + StringUtil.makeSlug(cat.getName()) + "-" + cat.getId();
				out.println("<li><a href='" + urlCat + "'>" + menu + cat.getName() + "</a>");
				showCat(request, out, cat.getId(), menu + "|---");
			}
			out.println("</ul>");
		} else {
			out.println("</li>");
		}
	}

	// Lấy danh sách id danh mục con
	public static ArrayList<Integer> getIdCat(ArrayList<Integer> list, int catId) {
		CategoryDao objCatDao = new CategoryDao();
		ArrayList<Category> listCatCon = objCatDao.getCatParent(catId);
		if (listCatCon.size() > 0) {
			for (Category cat : listCatCon) {
				list.add(cat.getId());
				getIdCat(list, cat.getId());
			}
		}
		return list;
	}

	public static ArrayList<TheLoaiNoiBatUtil> getListTheLoaiNoiBat() {
		ArrayList<TheLoaiNoiBatUtil> list = new ArrayList<TheLoaiNoiBatUtil>();
		CategoryDao objCatDao = new CategoryDao();
		BookDao objBookDao = new BookDao();
		ArrayList<Category> listCat = objCatDao.getAll();
		if (listCat.size() > 0) {
			for (Category category : listCat) {
				int purchases = 0;
				ArrayList<Book> listBook = objBookDao.getBookByCat(category.getId());
				if (listBook.size() > 0) {
					for (Book book : listBook) {
						purchases += book.getPurchases();
					}
				}
				TheLoaiNoiBatUtil obj = new TheLoaiNoiBatUtil(category.getId(), purchases);
				list.add(obj);
			}
		}
		return list;
	}

	public static ArrayList<Category> getListCat(ArrayList<TheLoaiNoiBatUtil> listNoiBat) {
		ArrayList<Category> list = new ArrayList<Category>();
		CategoryDao objCatDao = new CategoryDao();
		if (listNoiBat.size() > 0) {
			Collections.sort(listNoiBat, (o1, o2) -> Integer.compare(o2.getPurchases(), o1.getPurchases()));
			for (int i = 0; i < listNoiBat.size(); i++) {
				if (i > 4) {
					break;
				}
				TheLoaiNoiBatUtil theLoai = listNoiBat.get(i);
				Category objCat = objCatDao.getCat(theLoai.getCatId());
				list.add(objCat);
			}
		}
		return list;
	}

}
