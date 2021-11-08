package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import models.User;
import utils.JDBCConnectionUtil;

public class UserDao extends AbstractDao {
	public UserDao() {
		connectDB = new JDBCConnectionUtil();
	}

	public ArrayList<User> getAll() {
		ArrayList<User> listUser = new ArrayList<User>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM users ORDER BY id DESC";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				User objUser = new User(result.getInt("id"), result.getString("username"), result.getString("password"),
						result.getString("fullname"), result.getString("address"), result.getString("email"), result.getString("phone"),
						result.getInt("role"));
				listUser.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listUser;
	}
	
	public User getUserNew() {
		User objUser = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM users ORDER BY id DESC LIMIT 1";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			if (result.next()) {
				objUser = new User(result.getInt("id"), result.getString("username"), result.getString("password"),
						result.getString("fullname"), result.getString("address"), result.getString("email"), result.getString("phone"),
						result.getInt("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return objUser;
	}
	
	// Phân trang
	public ArrayList<User> getUser(int first, int last) {
		ArrayList<User> listUser = new ArrayList<User>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM users ORDER BY id DESC LIMIT ?,?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, first);
			pstate.setInt(2, last);
			result = pstate.executeQuery();
			while (result.next()) {
				User objUser = new User(result.getInt("id"), result.getString("username"), result.getString("password"),
						result.getString("fullname"), result.getString("address"), result.getString("email"), result.getString("phone"),
						result.getInt("role"));
				listUser.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listUser;
	}
	
	// Tìm kiếm
	public ArrayList<User> getSearchUser(String sName) {
		ArrayList<User> listUser = new ArrayList<User>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM users WHERE username LIKE ? ORDER BY id DESC";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, "%" + sName + "%");
			result = pstate.executeQuery();
			while (result.next()) {
				User objUser = new User(result.getInt("id"), result.getString("username"), result.getString("password"),
						result.getString("fullname"), result.getString("address"), result.getString("email"), result.getString("phone"),
						result.getInt("role"));
				listUser.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listUser;
	}
	
	public int getCount() {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM users";
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
	
	public User getUser(int id) {
		User objUser = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			result = pstate.executeQuery();
			if (result.next()) {
				objUser = new User(result.getInt("id"), result.getString("username"), result.getString("password"),
						result.getString("fullname"), result.getString("address"), result.getString("email"), result.getString("phone"),
						result.getInt("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objUser;
	}
	
	public User getUser2(String username) {
		User objUser = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, username);
			result = pstate.executeQuery();
			if (result.next()) {
				objUser = new User(result.getInt("id"), result.getString("username"), result.getString("password"),
						result.getString("fullname"), result.getString("address"), result.getString("email"), result.getString("phone"),
						result.getInt("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objUser;
	}
	
	public User getUserLogin(String username, String password) {
		User objUser = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, username);
			pstate.setString(2, password);
			result = pstate.executeQuery();
			if (result.next()) {
				objUser = new User(result.getInt("id"), result.getString("username"), result.getString("password"),
						result.getString("fullname"), result.getString("address"), result.getString("email"), result.getString("phone"),
						result.getInt("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objUser;
	}
	
	public boolean checkUsername(String username) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, username);
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
	
	public ArrayList<User> getUser(String fullname) {
		ArrayList<User> listUser = new ArrayList<User>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM users WHERE fullname LIKE ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, "%" + fullname + "%");
			result = pstate.executeQuery();
			if (result.next()) {
				User objUser = new User(result.getInt("id"), result.getString("username"), result.getString("password"),
						result.getString("fullname"), result.getString("address"), result.getString("email"), result.getString("phone"),
						result.getInt("role"));
				listUser.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listUser;
	}
	
	public ArrayList<User> getMod() {
		ArrayList<User> listUser = new ArrayList<User>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM users WHERE role = 1 ORDER BY id DESC";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				User objUser = new User(result.getInt("id"), result.getString("username"), result.getString("password"),
						result.getString("fullname"), result.getString("address"), result.getString("email"), result.getString("phone"),
						result.getInt("role"));
				listUser.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listUser;
	}
	
	public boolean checkId(int id) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
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
	
	public int add(User objUser) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "INSERT INTO users(username,password,fullname,address,email,phone,role) VALUES (?,?,?,?,?,?,?)";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, objUser.getUsername());
			pstate.setString(2, objUser.getPassword());
			pstate.setString(3, objUser.getFullname());
			pstate.setString(4, objUser.getAddress());
			pstate.setString(5, objUser.getEmail());
			pstate.setString(6, objUser.getPhone());
			pstate.setInt(7, objUser.getRole());
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
		String sql = "UPDATE users SET password = ?, fullname = ?, address = ?, email = ?, phone = ?, role = ? WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, objUser.getPassword());
			pstate.setString(2, objUser.getFullname());
			pstate.setString(3, objUser.getAddress());
			pstate.setString(4, objUser.getEmail());
			pstate.setString(5, objUser.getPhone());
			pstate.setInt(6, objUser.getRole());
			pstate.setInt(7, objUser.getId());
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
		String sql = "DELETE FROM users WHERE id = ?";
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
