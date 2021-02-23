package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Favourite;
import utils.JDBCConnectionUtil;

public class FavouriteDao extends AbstractDao {
	public FavouriteDao() {
		connectDB = new JDBCConnectionUtil();
	}

	public ArrayList<Favourite> getAll() {
		ArrayList<Favourite> listFavourite = new ArrayList<Favourite>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM favourites";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				Favourite objFavourite = new Favourite(result.getInt("id"), result.getInt("id_user"),
						result.getInt("id_book"), result.getInt("status"));
				listFavourite.add(objFavourite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listFavourite;
	}
	
	public int getFavouriteCount(int idBook) {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM favourites WHERE id_book = ? AND status = 1";
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
	
	public ArrayList<Favourite> getFavouriteUser(int idUser) {
		ArrayList<Favourite> listFavourite = new ArrayList<Favourite>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM favourites WHERE id_user = ? AND status = 1";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idUser);
			result = pstate.executeQuery();
			while (result.next()) {
				Favourite objFavourite = new Favourite(result.getInt("id"), result.getInt("id_user"),
						result.getInt("id_book"), result.getInt("status"));
				listFavourite.add(objFavourite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listFavourite;
	}
	
	public int getCountFavouriteUser(int idUser) {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM favourites WHERE id_user = ? AND status = 1";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idUser);
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
	
	public boolean checkIdUser(int idUser) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM favourites WHERE id_user = ?";
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
			connectDB.close(pstate, conn);
		}
		return false;
	}
	
	public boolean checkIdBook(int idBook) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM favourites WHERE id_book = ?";
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
			connectDB.close(pstate, conn);
		}
		return false;
	}
	
	public boolean checkFavouriteStatus(int idBook, int idUser) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM favourites WHERE id_book = ? AND id_user = ? AND status = 1";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idBook);
			pstate.setInt(2, idUser);
			result = pstate.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return false;
	}
	
	public boolean checkFavourite(int idBook, int idUser) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM favourites WHERE id_book = ? AND id_user = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idBook);
			pstate.setInt(2, idUser);
			result = pstate.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return false;
	}
	
	public Favourite getFavourite(int idUser, int idBook) {
		Favourite objFavourite = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM favourites WHERE id_user = ? AND id_book = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idUser);
			pstate.setInt(2, idBook);
			result = pstate.executeQuery();
			if (result.next()) {
				objFavourite = new Favourite(result.getInt("id"), result.getInt("id_user"),
						result.getInt("id_book"), result.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objFavourite;
	}
	
	public int add(Favourite objFavourite) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "INSERT INTO favourites(id_user,id_book) VALUES (?,?)";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, objFavourite.getIdUser());
			pstate.setInt(2, objFavourite.getIdBook());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
	public int editStatus(int idBook, int idUser, int status) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "UPDATE favourites SET status = ? WHERE id_book = ? AND id_user = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, status);
			pstate.setInt(2, idBook);
			pstate.setInt(3, idUser);
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
	public int delete(int idBook) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "DELETE FROM favourites WHERE id_book = ?";
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
		String sql = "DELETE FROM favourites WHERE id_user = ?";
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
