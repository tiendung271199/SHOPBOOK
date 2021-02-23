package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Order;
import models.User;
import utils.JDBCConnectionUtil;

public class OrderDao extends AbstractDao {
	public OrderDao() {
		connectDB = new JDBCConnectionUtil();
	}

	public ArrayList<Order> getAll() {
		ArrayList<Order> listOrder = new ArrayList<Order>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM borders ORDER BY id DESC";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				Order objOrder = new Order(result.getInt("id"), result.getInt("id_user"), result.getString("name"),
						result.getString("phone"), result.getString("email"), result.getString("address"),
						result.getString("order_note"), result.getTimestamp("date_create"), result.getInt("status"));
				listOrder.add(objOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listOrder;
	}

	// Lấy order mới nhất
	public Order getOrderNew() {
		Order objOrder = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM borders ORDER BY id DESC LIMIT 1";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			if (result.next()) {
				objOrder = new Order(result.getInt("id"), result.getInt("id_user"), result.getString("name"),
						result.getString("phone"), result.getString("email"), result.getString("address"),
						result.getString("order_note"), result.getTimestamp("date_create"), result.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return objOrder;
	}

	// Tìm kiếm
	public ArrayList<Order> getSearchOrder(String sName) {
		ArrayList<Order> listOrder = new ArrayList<Order>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM borders WHERE name LIKE ? ORDER BY id DESC";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, "%" + sName + "%");
			result = pstate.executeQuery();
			while (result.next()) {
				Order objOrder = new Order(result.getInt("id"), result.getInt("id_user"), result.getString("name"),
						result.getString("phone"), result.getString("email"), result.getString("address"),
						result.getString("order_note"), result.getTimestamp("date_create"), result.getInt("status"));
				listOrder.add(objOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listOrder;
	}

	// Lấy order theo idUser
	public ArrayList<Order> getOrderUser(int idUser) {
		ArrayList<Order> listOrder = new ArrayList<Order>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM borders WHERE id_user = ? ORDER BY id DESC";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idUser);
			result = pstate.executeQuery();
			while (result.next()) {
				Order objOrder = new Order(result.getInt("id"), result.getInt("id_user"), result.getString("name"),
						result.getString("phone"), result.getString("email"), result.getString("address"),
						result.getString("order_note"), result.getTimestamp("date_create"), result.getInt("status"));
				listOrder.add(objOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listOrder;
	}

	// Phân trang
	public ArrayList<Order> getOrder(int first, int last) {
		ArrayList<Order> listOrder = new ArrayList<Order>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM borders ORDER BY id DESC LIMIT ?,?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, first);
			pstate.setInt(2, last);
			result = pstate.executeQuery();
			while (result.next()) {
				Order objOrder = new Order(result.getInt("id"), result.getInt("id_user"), result.getString("name"),
						result.getString("phone"), result.getString("email"), result.getString("address"),
						result.getString("order_note"), result.getTimestamp("date_create"), result.getInt("status"));
				listOrder.add(objOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listOrder;
	}

	public int getCount() {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM borders";
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
	public ArrayList<Order> getSearchOrder(ArrayList<User> list) {
		ArrayList<Order> listOrder = new ArrayList<Order>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM borders WHERE 0";
		for (int i = 1; i <= list.size(); i++) {
			sql += " OR id_user = ?";
		}
		sql += " ORDER BY id DESC";
		try {
			pstate = conn.prepareStatement(sql);
			int i = 1;
			for (User user : list) {
				pstate.setInt(i++, user.getId());
			}
			result = pstate.executeQuery();
			while (result.next()) {
				Order objOrder = new Order(result.getInt("id"), result.getInt("id_user"), result.getString("name"),
						result.getString("phone"), result.getString("email"), result.getString("address"),
						result.getString("order_note"), result.getTimestamp("date_create"), result.getInt("status"));
				listOrder.add(objOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listOrder;
	}

	public Order getOrder(int id) {
		Order objOrder = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM borders WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			result = pstate.executeQuery();
			if (result.next()) {
				objOrder = new Order(result.getInt("id"), result.getInt("id_user"), result.getString("name"),
						result.getString("phone"), result.getString("email"), result.getString("address"),
						result.getString("order_note"), result.getTimestamp("date_create"), result.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objOrder;
	}

	public Order getOrderUser(int id, int idUser) {
		Order objOrder = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM borders WHERE id = ? AND id_user = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			pstate.setInt(2, idUser);
			result = pstate.executeQuery();
			if (result.next()) {
				objOrder = new Order(result.getInt("id"), result.getInt("id_user"), result.getString("name"),
						result.getString("phone"), result.getString("email"), result.getString("address"),
						result.getString("order_note"), result.getTimestamp("date_create"), result.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objOrder;
	}

	public boolean checkUserOrder(int idUser) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM borders WHERE id_user = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idUser);
			result = pstate.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkId(int id) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM borders WHERE id = ?";
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

	public boolean checkId(int id, int idUser) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM borders WHERE id = ? AND id_user = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			pstate.setInt(2, idUser);
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

	public int add(Order objOrder) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "INSERT INTO borders(id_user,name,phone,email,address,order_note) VALUES (?,?,?,?,?,?)";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, objOrder.getIdUser());
			pstate.setString(2, objOrder.getName());
			pstate.setString(3, objOrder.getPhone());
			pstate.setString(4, objOrder.getEmail());
			pstate.setString(5, objOrder.getAddress());
			pstate.setString(6, objOrder.getOrderNote());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}

	public int edit(User objUser) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "UPDATE borders SET name = ?, phone = ?, email = ?, address = ? WHERE id_user = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, objUser.getFullname());
			pstate.setString(2, objUser.getPhone());
			pstate.setString(3, objUser.getEmail());
			pstate.setString(4, objUser.getAddress());
			pstate.setInt(5, objUser.getId());
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
		String sql = "UPDATE borders SET status = ? WHERE id = ?";
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
		String sql = "DELETE FROM borders WHERE id = ?";
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
