package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Modules;
import utils.JDBCConnectionUtil;

public class ModuleDao extends AbstractDao {
	public ModuleDao() {
		connectDB = new JDBCConnectionUtil();
	}
	
	public ArrayList<Modules> getAll() {
		ArrayList<Modules> listModule = new ArrayList<Modules>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM module ORDER BY id DESC";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				Modules objModules = new Modules(result.getInt("id"), result.getString("name"));
				listModule.add(objModules);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listModule;
	}
	
	public Modules getModules(int id) {
		Modules objModules = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM module WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			result = pstate.executeQuery();
			if (result.next()) {
				objModules = new Modules(result.getInt("id"), result.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return objModules;
	}
	
	// Phân trang
	public ArrayList<Modules> getModule(int first, int last) {
		ArrayList<Modules> listModule = new ArrayList<Modules>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM module ORDER BY id DESC LIMIT ?,?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, first);
			pstate.setInt(2, last);
			result = pstate.executeQuery();
			while (result.next()) {
				Modules objModules = new Modules(result.getInt("id"), result.getString("name"));
				listModule.add(objModules);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listModule;
	}
	
	public int getCount() {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM module";
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
	
	public ArrayList<Modules> getSearchModule(String sName) {
		ArrayList<Modules> listModule = new ArrayList<Modules>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM module WHERE name LIKE ? ORDER BY id DESC";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, "%" + sName + "%");
			result = pstate.executeQuery();
			while (result.next()) {
				Modules objModules = new Modules(result.getInt("id"), result.getString("name"));
				listModule.add(objModules);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listModule;
	}
	
	public boolean checkName(String name) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM module WHERE name = ?";
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
	
	public boolean checkId(int id) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM module WHERE id = ?";
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
	
	public int add(Modules objModules) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "INSERT INTO module(name) VALUES (?)";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, objModules.getName());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
	public int edit(Modules objModules) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "UPDATE module SET name = ? WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, objModules.getName());
			pstate.setInt(2, objModules.getId());
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
		String sql = "DELETE FROM module WHERE id = ?";
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
