package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Book;
import models.SaleOff;
import utils.JDBCConnectionUtil;

public class SaleOffDao extends AbstractDao {
	public SaleOffDao() {
		connectDB = new JDBCConnectionUtil();
	}

	public ArrayList<SaleOff> getAll() {
		ArrayList<SaleOff> listSale = new ArrayList<SaleOff>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM sale_off ORDER BY id DESC";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				SaleOff objSale = new SaleOff(result.getInt("id"), result.getInt("id_book"), result.getInt("sale"));
				listSale.add(objSale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listSale;
	}

	public ArrayList<SaleOff> getSaleDec() {
		ArrayList<SaleOff> listSale = new ArrayList<SaleOff>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM sale_off ORDER BY sale DESC";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				SaleOff objSale = new SaleOff(result.getInt("id"), result.getInt("id_book"), result.getInt("sale"));
				listSale.add(objSale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listSale;
	}

	// Phân trang
	public ArrayList<SaleOff> getSale(int first, int last) {
		ArrayList<SaleOff> listSale = new ArrayList<SaleOff>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM sale_off ORDER BY id DESC LIMIT ?,?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, first);
			pstate.setInt(2, last);
			result = pstate.executeQuery();
			while (result.next()) {
				SaleOff objSale = new SaleOff(result.getInt("id"), result.getInt("id_book"), result.getInt("sale"));
				listSale.add(objSale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listSale;
	}

	public boolean checkId(int id) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM sale_off WHERE id = ?";
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

	public int getCount() {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM sale_off";
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

	// Tìm kiếm
	public ArrayList<SaleOff> getSearchSale(ArrayList<Book> listBook) {
		ArrayList<SaleOff> listSale = new ArrayList<SaleOff>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM sale_off WHERE 0";
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
				SaleOff objSale = new SaleOff(result.getInt("id"), result.getInt("id_book"), result.getInt("sale"));
				listSale.add(objSale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listSale;
	}

	public SaleOff getSaleId(int id) {
		SaleOff objSale = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM sale_off WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			result = pstate.executeQuery();
			while (result.next()) {
				objSale = new SaleOff(result.getInt("id"), result.getInt("id_book"), result.getInt("sale"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objSale;
	}

	public SaleOff getSale(int idBook) {
		SaleOff objSale = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM sale_off WHERE id_book = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idBook);
			result = pstate.executeQuery();
			while (result.next()) {
				objSale = new SaleOff(result.getInt("id"), result.getInt("id_book"), result.getInt("sale"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objSale;
	}

	public boolean checkSale(int idBook) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM sale_off WHERE id_book = ?";
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

	public boolean checkSale(int idBook, int sale) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM sale_off WHERE id_book = ? AND sale = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idBook);
			pstate.setInt(2, sale);
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

	public int add(SaleOff objSale) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "INSERT INTO sale_off(id_book,sale) VALUES (?,?)";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, objSale.getIdBook());
			pstate.setInt(2, objSale.getSale());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}

	public int edit(SaleOff objSale) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "UPDATE sale_off SET sale = ? WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, objSale.getSale());
			pstate.setInt(2, objSale.getId());
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
		String sql = "DELETE FROM sale_off WHERE id = ?";
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
		String sql = "DELETE FROM sale_off WHERE id_book = ?";
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

}
