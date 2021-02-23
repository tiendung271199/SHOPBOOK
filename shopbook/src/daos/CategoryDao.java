package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Category;
import utils.JDBCConnectionUtil;

public class CategoryDao extends AbstractDao {
	public CategoryDao() {
		connectDB = new JDBCConnectionUtil();
	}
	
	public ArrayList<Category> getAll() {
		ArrayList<Category> listCat = new ArrayList<Category>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM categories ORDER BY id DESC";
		try {
			state = conn.createStatement();
			result = state.executeQuery(sql);
			while (result.next()) {
				Category objCat = new Category(result.getInt("id"), result.getString("name"), result.getInt("parent_id"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, state, conn);
		}
		return listCat;
	}
	
	public ArrayList<Category> getCatParent(int id) {
		ArrayList<Category> listCat = new ArrayList<Category>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM categories WHERE parent_id = ? ORDER BY id DESC";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			result = pstate.executeQuery();
			while (result.next()) {
				Category objCat = new Category(result.getInt("id"), result.getString("name"), result.getInt("parent_id"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listCat;
	}
	
	// Phân trang
	public ArrayList<Category> getCat(int first, int last) {
		ArrayList<Category> listCat = new ArrayList<Category>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM categories ORDER BY id DESC LIMIT ?,?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, first);
			pstate.setInt(2, last);
			result = pstate.executeQuery();
			while (result.next()) {
				Category objCat = new Category(result.getInt("id"), result.getString("name"), result.getInt("parent_id"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listCat;
	}
	
	public Category getCat(int id) {
		Category objCat = null;
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM categories WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, id);
			result = pstate.executeQuery();
			if (result.next()) {
				objCat = new Category(result.getInt("id"), result.getString("name"), result.getInt("parent_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return objCat;
	}
	
	// Tìm kiếm
	public ArrayList<Category> getSearchCat(String sName) {
		ArrayList<Category> listCat = new ArrayList<Category>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM categories WHERE name LIKE ? ORDER BY id DESC";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, "%" + sName + "%");
			result = pstate.executeQuery();
			while (result.next()) {
				Category objCat = new Category(result.getInt("id"), result.getString("name"), result.getInt("parent_id"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listCat;
	}
	
	// Kiểm tra id có tồn tại không
	public boolean checkId(int id) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM categories WHERE id = ?";
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
	
	// Kiểm tra name có tồn tại không
	public boolean checkName(String name) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM categories WHERE name = ?";
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
		String sql = "SELECT * FROM categories WHERE name = ? AND name != ?";
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
	
	public int getCount() {
		int count = 0;
		conn = connectDB.getConnection();
		String sql = "SELECT COUNT(id) FROM categories";
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
	
	public int add(Category objCat) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "INSERT INTO categories(name,parent_id) VALUES (?,?)";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, objCat.getName());
			pstate.setInt(2, objCat.getParentId());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
	public int edit(Category objCat) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "UPDATE categories SET name = ?, parent_id = ? WHERE id = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, objCat.getName());
			pstate.setInt(2, objCat.getParentId());
			pstate.setInt(3, objCat.getId());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
	public int delete(ArrayList<Integer> listCatId) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "DELETE FROM categories WHERE 0";
		if (listCatId.size() > 0) {
			for (int i = 0; i < listCatId.size(); i++) {
				sql += " OR id = ?";
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
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}
	
}
