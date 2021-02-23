package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import models.OrderDetail;
import utils.JDBCConnectionUtil;

public class OrderDetailDao extends AbstractDao {
	public OrderDetailDao() {
		connectDB = new JDBCConnectionUtil();
	}

	public ArrayList<OrderDetail> getOrderDetail(int idOrder) {
		ArrayList<OrderDetail> listOrderDetail = new ArrayList<OrderDetail>();
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM order_details WHERE id_order = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idOrder);
			result = pstate.executeQuery();
			while (result.next()) {
				OrderDetail objOrderDetail = new OrderDetail(result.getInt("id"), result.getInt("id_order"),
						result.getInt("number"), result.getInt("id_book"), result.getInt("sale"),
						result.getInt("price"));
				listOrderDetail.add(objOrderDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(result, pstate, conn);
		}
		return listOrderDetail;
	}

	public boolean checkOrder(int idOrder) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM order_details WHERE id_order = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idOrder);
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

	public boolean checkId(int idOrder) {
		conn = connectDB.getConnection();
		String sql = "SELECT * FROM order_details WHERE id_order = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idOrder);
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
		String sql = "SELECT * FROM order_details WHERE id_book = ?";
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

	public int add(OrderDetail objOrderDetail) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "INSERT INTO order_details(id_order,number,id_book,sale,price) VALUES (?,?,?,?,?)";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, objOrderDetail.getIdOrder());
			pstate.setInt(2, objOrderDetail.getNumber());
			pstate.setInt(3, objOrderDetail.getIdBook());
			pstate.setInt(4, objOrderDetail.getSale());
			pstate.setInt(5, objOrderDetail.getPrice());
			rs = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(pstate, conn);
		}
		return rs;
	}

	public int delete(int idOrder) {
		int rs = 0;
		conn = connectDB.getConnection();
		String sql = "DELETE FROM order_details WHERE id_order = ?";
		try {
			pstate = conn.prepareStatement(sql);
			pstate.setInt(1, idOrder);
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
		String sql = "DELETE FROM order_details WHERE id_book = ?";
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
