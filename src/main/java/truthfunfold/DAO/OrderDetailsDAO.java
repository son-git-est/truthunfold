package truthfunfold.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import truthunfold.DB.DBUtil;
import truthunfold.Entity.Article;
import truthunfold.Entity.Order;
import truthunfold.Entity.OrderDetails;

public class OrderDetailsDAO {

	public List<OrderDetails> viewOrderDetails(int orderId) throws SQLException {

		List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
		OrderDetails orderDetail;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.makeConnection();

			String sql = "SELECT * FROM `order_detail`JOIN article on order_detail.article_id = article.id WHERE order_id = ?";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, orderId);

			rs = ps.executeQuery();

			while (rs.next()) { // rs.next is boolean 1/0

				orderDetail = new OrderDetails();

				orderDetail.setOrderId(orderId);
				orderDetail.setArticleId(rs.getInt("article_id"));
				orderDetail.setArticleTitle(rs.getString("title"));

				orderDetails.add(orderDetail);
			}

			return orderDetails;

		} catch (Exception e) {

		} finally {

			close(conn, ps, rs);

		}

		return null;
	}

	public void insertOrder(int readerId, List<Article> articles) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int orderId = 0;

		try {
			conn = DBUtil.makeConnection();
			conn.setAutoCommit(false);

			String sql = "INSERT INTO `order` (reader_id, date) VALUES(?,?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, readerId);
			ps.setDate(2, new Date(System.currentTimeMillis()));
			ps.execute();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				orderId = rs.getInt(1);
			}

			sql = "INSERT INTO `order_detail` (order_id, article_id) VALUES (?,?)";

			pstm = conn.prepareStatement(sql);

			for (Article article : articles) {

				pstm.setInt(1, orderId);
				pstm.setInt(2, article.getId());
				pstm.addBatch();

			}
			pstm.executeBatch();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();

		} finally {

			close(conn, ps, rs);

			if (pstm != null) {
				pstm.close();
			}
		}
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
