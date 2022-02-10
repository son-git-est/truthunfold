package truthfunfold.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import truthunfold.Constant.SecureHash;
import truthunfold.DB.DBUtil;
import truthunfold.Entity.User;

public class UserDAO {

	public User getUserByUserNameAndPassword(String username, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {

			conn = DBUtil.makeConnection();

			String sql = "SELECT * FROM user WHERE user_name = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, username);

			rs = ps.executeQuery();
			if (rs.next()) {
				String storedPassord = rs.getString("password");
				String salt = rs.getString("salt");
				boolean validation = SecureHash.validatePBKDF2Password(password, storedPassord, salt);

				if (validation) {
					int id = rs.getInt("id");

					user = new User(id, username, storedPassord);

				}
			}

			return user;

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

		return null;
	}

}
