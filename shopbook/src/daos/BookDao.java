package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Book;
import models.Category;
import utils.JDBCConnectionUtil;

public class BookDao extends AbstractDao {
	public BookDao() {
		connectDB = new JDBCConnectionUtil();
	}

	public ArrayList<Book> getAll() {
		ArrayList<Book> listBook = new ArrayList<Book>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id ORDER BY books.id DESC";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				Book objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
				listBook.add(objBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listBook;
	}

	// Phân trang
	public ArrayList<Book> getBook(int first, int last) {
		ArrayList<Book> listBook = new ArrayList<Book>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id ORDER BY books.id DESC LIMIT ?,?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, first);
			pstate.setInt(2, last);
			result = pstate.executeQuery();
			while (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				Book objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
				listBook.add(objBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listBook;
	}

	// Phân trang danh mục
	public ArrayList<Book> getBook(int first, int last, ArrayList<Integer> listCatId) {
		ArrayList<Book> listBook = new ArrayList<Book>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id WHERE 0";
		if (listCatId.size() > 0) {
			for (int i = 0; i < listCatId.size(); i++) {
				sql += " OR cat_id = ?";
			}
		}
		sql += " ORDER BY books.id DESC LIMIT ?,?";
		try {
			pstate = conn.prepareStatement(sql);
			int k = 1;
			if (listCatId.size() > 0) {
				for (Integer catId : listCatId) {
					pstate.setInt(k++, catId);
				}
			}
			pstate.setInt(k++, first);
			pstate.setInt(k, last);
			result = pstate.executeQuery();
			while (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				Book objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
				listBook.add(objBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listBook;
	}

	public ArrayList<Book> getBookByCat(int idCat) {
		ArrayList<Book> listBook = new ArrayList<Book>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id WHERE cat_id = ? ORDER BY books.id DESC";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idCat);
			result = pstate.executeQuery();
			while (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				Book objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
				listBook.add(objBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listBook;
	}

	public Book getBook(int id) {
		Book objBook = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id WHERE books.id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			result = pstate.executeQuery();
			if (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objBook;
	}

	public ArrayList<Book> getBookNew() {
		ArrayList<Book> listBook = new ArrayList<Book>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id ORDER BY books.id DESC LIMIT 6";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				Book objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
				listBook.add(objBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listBook;
	}

	public ArrayList<Book> getBookPurchases() {
		ArrayList<Book> listBook = new ArrayList<Book>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id ORDER BY purchases DESC LIMIT 4";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				Book objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
				listBook.add(objBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listBook;
	}

	public int getCount() {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM books";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				count = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return count;
	}

	public int getCount(ArrayList<Integer> listCatId) {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM books WHERE 0";
		if (listCatId.size() > 0) {
			for (int i = 0; i < listCatId.size(); i++) {
				sql += " OR cat_id = ?";
			}
		}
		try {
			pstate = conn.prepareStatement(sql);
			int k = 1;
			if (listCatId.size() > 0) {
				for (Integer catId : listCatId) {
					pstate.setInt(k++, catId);
				}
			}
			result = pstate.executeQuery();
			while (result.next()) {
				count = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return count;
	}

	public int getCount(int min, int max) {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM books WHERE price >= ? AND price <= ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, min);
			pstate.setInt(2, max);
			result = pstate.executeQuery();
			while (result.next()) {
				count = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return count;
	}

	public int getCount2(int min) {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM books WHERE price >= ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, min);
			result = pstate.executeQuery();
			while (result.next()) {
				count = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return count;
	}

	public int getCount(String sName) {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM books WHERE name LIKE ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, "%" + sName + "%");
			result = pstate.executeQuery();
			while (result.next()) {
				count = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return count;
	}

	public ArrayList<Book> searchPrice(int min, int max) {
		ArrayList<Book> listBook = new ArrayList<Book>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id WHERE price >= ? AND price <= ? ORDER BY price ASC";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, min);
			pstate.setInt(2, max);
			result = pstate.executeQuery();
			while (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				Book objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
				listBook.add(objBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listBook;
	}

	public ArrayList<Book> searchPrice(int min) {
		ArrayList<Book> listBook = new ArrayList<Book>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id WHERE price >= ? ORDER BY price ASC";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, min);
			result = pstate.executeQuery();
			while (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				Book objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
				listBook.add(objBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listBook;
	}

	public ArrayList<Book> getBookName(String name) {
		ArrayList<Book> listBook = new ArrayList<Book>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id WHERE books.name LIKE ? ORDER BY books.id DESC";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, "%" + name + "%");
			result = pstate.executeQuery();
			while (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				Book objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
				listBook.add(objBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listBook;
	}

	public Book getBookName2(String name) {
		Book objBook = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id WHERE books.name = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, name);
			result = pstate.executeQuery();
			while (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objBook;
	}

	// Tìm kiếm ajax
	public ArrayList<Book> search(int min, int max, int catId) {
		ArrayList<Book> list = new ArrayList<Book>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books INNER JOIN categories ON books.cat_id = categories.id WHERE 1";
		if (catId != 0) {
			sql += " AND cat_id = ?";
		}
		if (min != 0) {
			sql += " AND price >= ?";
		}
		if (max != 0) {
			sql += " AND price <= ?";
		}
		if (min != 0 || max != 0) {
			sql += " ORDER BY price ASC";
		} else {
			sql += " ORDER BY books.id DESC";
		}
		try {
			pstate = conn.prepareStatement(sql);
			int k = 1;
			if (catId != 0) {
				pstate.setInt(k++, catId);
			}
			if (min != 0) {
				pstate.setInt(k++, min);
			}
			if (max != 0) {
				pstate.setInt(k, max);
			}
			result = pstate.executeQuery();
			while (result.next()) {
				Category objCat = new Category(result.getInt("categories.id"), result.getString("categories.name"),
						result.getInt("parent_id"));
				Book objBook = new Book(result.getInt("books.id"), result.getString("books.name"),
						result.getString("author"), result.getString("description"), result.getString("detail"),
						result.getInt("number"), result.getInt("price"), result.getInt("purchases"),
						result.getString("picture"), objCat);
				list.add(objBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return list;
	}
	
	public boolean checkId(int id) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			result = pstate.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return false;
	}

	public boolean checkIdCat(int catId) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books WHERE cat_id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, catId);
			result = pstate.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return false;
	}

	public boolean checkName(String name) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books WHERE name = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, name);
			result = pstate.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return false;
	}

	public boolean checkName(String name, String oldName) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM books WHERE name = ? AND name != ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, name);
			pstate.setString(2, oldName);
			result = pstate.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return false;
	}

	public int add(Book objBook) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "INSERT INTO books(name,author,description,detail,number,price,picture,cat_id) VALUES (?,?,?,?,?,?,?,?)";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, objBook.getName());
			pstate.setString(2, objBook.getAuthor());
			pstate.setString(3, objBook.getDescription());
			pstate.setString(4, objBook.getDetail());
			pstate.setInt(5, objBook.getNumber());
			pstate.setInt(6, objBook.getPrice());
			pstate.setString(7, objBook.getPicture());
			pstate.setInt(8, objBook.getCat().getId());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}

	public int edit(Book objBook) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "UPDATE books SET name = ?, author = ?, description = ?, detail = ?, number = ?, price = ?, cat_id = ? WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, objBook.getName());
			pstate.setString(2, objBook.getAuthor());
			pstate.setString(3, objBook.getDescription());
			pstate.setString(4, objBook.getDetail());
			pstate.setInt(5, objBook.getNumber());
			pstate.setInt(6, objBook.getPrice());
			pstate.setInt(7, objBook.getCat().getId());
			pstate.setInt(8, objBook.getId());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}

	public int edit(int number, int purchases, int id) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "UPDATE books SET number = ?, purchases = ? WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, number);
			pstate.setInt(2, purchases);
			pstate.setInt(3, id);
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}

	public int editPicture(String picture, int id) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "UPDATE books SET picture = ? WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, picture);
			pstate.setInt(2, id);
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}

	public int delete(int id) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "DELETE FROM books WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}

}
