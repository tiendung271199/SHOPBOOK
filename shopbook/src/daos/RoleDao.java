package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Role;
import utils.JDBCConnectionUtil;

public class RoleDao extends AbstractDao {
	public RoleDao() {
		connectDB = new JDBCConnectionUtil();
	}

	public ArrayList<Role> getAll() {
		ArrayList<Role> listRole = new ArrayList<Role>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM roles ORDER BY id DESC";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				Role objRole = new Role(result.getInt("id"), result.getInt("id_user"), result.getInt("id_module"),
						result.getInt("status"));
				listRole.add(objRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listRole;
	}
	
	// Phân trang
	public ArrayList<Role> getRole(int first, int last) {
		ArrayList<Role> listRole = new ArrayList<Role>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM roles ORDER BY id DESC LIMIT ?,?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, first);
			pstate.setInt(2, last);
			result = pstate.executeQuery();
			while (result.next()) {
				Role objRole = new Role(result.getInt("id"), result.getInt("id_user"), result.getInt("id_module"),
						result.getInt("status"));
				listRole.add(objRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listRole;
	}
	
	public int getCount() {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM roles";
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
	
	public boolean checkRole(int idUser, int idModule) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM roles WHERE id_user = ? AND id_module = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idUser);
			pstate.setInt(2, idModule);
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
	
	public boolean checkRole2(int idUser, int idModule) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM roles WHERE id_user = ? AND id_module = ? AND status = 1";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idUser);
			pstate.setInt(2, idModule);
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
		String sql = "SELECT * FROM roles WHERE id_user = ?";
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
	
	public ArrayList<Role> getRoleUser(int idUser) {
		ArrayList<Role> listRole = new ArrayList<Role>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM roles WHERE id_user = ? AND status = 1";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idUser);
			result = pstate.executeQuery();
			while (result.next()) {
				Role objRole = new Role(result.getInt("id"), result.getInt("id_user"), result.getInt("id_module"),
						result.getInt("status"));
				listRole.add(objRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listRole;
	}
	
	public boolean checkIdModule(int idModule) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM roles WHERE id_module = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idModule);
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
	
	public int add(Role objRole) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "INSERT INTO roles(id_user,id_module) VALUES (?,?)";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, objRole.getIdUser());
			pstate.setInt(2, objRole.getIdModule());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
	public int editStatus(int status, int idUser, int idModule) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "UPDATE roles SET status = ? WHERE id_user = ? AND id_module = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, status);
			pstate.setInt(2, idUser);
			pstate.setInt(3, idModule);
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
		String sql = "DELETE FROM roles WHERE id_user = ?";
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
	
	public int deleteModule(int idModule) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "DELETE FROM roles WHERE id_module = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idModule);
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
}
