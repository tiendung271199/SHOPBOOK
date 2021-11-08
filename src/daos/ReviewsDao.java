package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Book;
import models.Reviews;
import utils.JDBCConnectionUtil;

public class ReviewsDao extends AbstractDao {
	public ReviewsDao() {
		connectDB = new JDBCConnectionUtil();
	}

	public ArrayList<Reviews> getAll() {
		ArrayList<Reviews> listReviews = new ArrayList<Reviews>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM reviews ORDER BY id DESC";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				Reviews objReviews = new Reviews(result.getInt("id"), result.getInt("id_user"),
						result.getInt("id_book"), result.getDouble("star"), result.getString("comment"),
						result.getTimestamp("date_create"), result.getInt("status"));
				listReviews.add(objReviews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listReviews;
	}
	
	public Reviews getReviewsId(int id) {
		Reviews objReviews = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM reviews WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			result = pstate.executeQuery();
			if (result.next()) {
				objReviews = new Reviews(result.getInt("id"), result.getInt("id_user"),
						result.getInt("id_book"), result.getDouble("star"), result.getString("comment"),
						result.getTimestamp("date_create"), result.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objReviews;
	}
	
	// Phân trang
	public ArrayList<Reviews> getReviews(int first, int last) {
		ArrayList<Reviews> listReviews = new ArrayList<Reviews>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM reviews ORDER BY id DESC LIMIT ?,?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, first);
			pstate.setInt(2, last);
			result = pstate.executeQuery();
			while (result.next()) {
				Reviews objReviews = new Reviews(result.getInt("id"), result.getInt("id_user"),
						result.getInt("id_book"), result.getDouble("star"), result.getString("comment"),
						result.getTimestamp("date_create"), result.getInt("status"));
				listReviews.add(objReviews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listReviews;
	}
	
	// Tìm kiếm
	public ArrayList<Reviews> getSearchReviews(ArrayList<Book> listBook) {
		ArrayList<Reviews> listReviews = new ArrayList<Reviews>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM reviews WHERE 0";
		for (int i = 1; i <= listBook.size(); i++) {
			sql += " OR id_book = ?";
		}
		sql += " ORDER BY id DESC";
		try {
			pstate = conn.prepareStatement(sql);
			int i = 1;
			for (Book book : listBook) {
				pstate.setInt(i++, book.getId());
			}
			result = pstate.executeQuery();
			while (result.next()) {
				Reviews objReviews = new Reviews(result.getInt("id"), result.getInt("id_user"),
						result.getInt("id_book"), result.getDouble("star"), result.getString("comment"),
						result.getTimestamp("date_create"), result.getInt("status"));
				listReviews.add(objReviews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listReviews;
	}
	
	public int getCount() {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM reviews";
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
	
	public ArrayList<Reviews> getReviews(int idBook) {
		ArrayList<Reviews> listReviews = new ArrayList<Reviews>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM reviews WHERE id_book = ? AND status = 1 ORDER BY id DESC";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idBook);
			result = pstate.executeQuery();
			while (result.next()) {
				Reviews objReviews = new Reviews(result.getInt("id"), result.getInt("id_user"),
						result.getInt("id_book"), result.getDouble("star"), result.getString("comment"),
						result.getTimestamp("date_create"), result.getInt("status"));
				listReviews.add(objReviews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listReviews;
	}
	
	public int getReviewsCount(int idBook) {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM reviews WHERE id_book = ? AND status = 1";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idBook);
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
	
	public ArrayList<Reviews> getBestReviews(int idBook) {
		ArrayList<Reviews> listReviews = new ArrayList<Reviews>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM reviews WHERE id_book = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idBook);
			result = pstate.executeQuery();
			while (result.next()) {
				Reviews objReviews = new Reviews(result.getInt("id"), result.getInt("id_user"),
						result.getInt("id_book"), result.getDouble("star"), result.getString("comment"),
						result.getTimestamp("date_create"), result.getInt("status"));
				listReviews.add(objReviews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listReviews;
	}
	
	public boolean checkId(int id) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM reviews WHERE id = ?";
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
	
	public boolean checkIdUser(int idUser) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM reviews WHERE id_user = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idUser);
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
	
	public boolean checkIdBook(int idBook) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM reviews WHERE id_book = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idBook);
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
	
	public int add(Reviews objReviews) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "INSERT INTO reviews(id_user,id_book,star,comment) VALUES (?,?,?,?)";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, objReviews.getIdUser());
			pstate.setInt(2, objReviews.getIdBook());
			pstate.setDouble(3, objReviews.getStar());
			pstate.setString(4, objReviews.getComment());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
	public int edit(Reviews objReviews) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "UPDATE reviews SET star = ?, comment = ? WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setDouble(1, objReviews.getStar());
			pstate.setString(2, objReviews.getComment());
			pstate.setInt(3, objReviews.getId());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
	public int editStatus(int status, int id) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "UPDATE reviews SET status = ? WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, status);
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
		String sql = "DELETE FROM reviews WHERE id = ?";
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
	
	public int deleteBook(int idBook) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "DELETE FROM reviews WHERE id_book = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idBook);
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
	public int deleteUser(int idUser) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "DELETE FROM reviews WHERE id_user = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idUser);
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
}
