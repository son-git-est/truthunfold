package truthfunfold.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import truthunfold.DB.DBUtil;
import truthunfold.Entity.Order;

public class OrderDAO {

	public int insertOrder(Order order) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBUtil.makeConnection();
			String sql = "INSERT INTO `order` (reader_id, date) value(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, order.getReaderId());
			ps.setDate(2, order.getDate());
			ps.execute();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {

		} finally {
			close(conn, ps, rs);

		}
		return 0;

	}

	public List<Order> viewOrder(int readerId) throws SQLException {

		List<Order> orders = new ArrayList<Order>();
		Order order;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.makeConnection();

			String sql = "SELECT * FROM `order` WHERE `order`.reader_id = ?";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, readerId);

			rs = ps.executeQuery();

			while (rs.next()) { // rs.next is boolean 1/0

				order = new Order();

				order.setId(rs.getInt("id"));
				order.setReaderId(readerId);
				order.setDate(rs.getDate("date"));

				orders.add(order);
			}

			return orders;

		} catch (Exception e) {

		} finally {

			close(conn, ps, rs);

		}

		return orders;

	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {

		if (conn != null) {
			conn.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}

	}

}
