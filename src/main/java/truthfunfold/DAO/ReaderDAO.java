package truthfunfold.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import truthunfold.Constant.SecureHash;
import truthunfold.DB.DBUtil;
import truthunfold.Entity.Reader;

public class ReaderDAO {
	public Reader getReaderByUserNameAndPassword(String username, String password) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reader reader = null;

		try {

			conn = DBUtil.makeConnection();

			String sql = "SELECT * FROM reader WHERE user_name = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			// ps.setString(2, password);

			rs = ps.executeQuery();
			rs.next();
			String storedPassord = rs.getString("password");
			String salt = rs.getString("salt");
			boolean validation = SecureHash.validatePBKDF2Password(password, storedPassord, salt);

			if (validation) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String country = rs.getString("country");
				int postCode = rs.getInt("post_code");

				reader = new Reader(id, firstName, lastName, email, phone, username, storedPassord, address, country,
						postCode);

			} else {
				return null;
			}

			return reader;

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

	public boolean registerNewReader(String firstname, String lastname, String email, String phone, String username,
			String password, String address, String country, int postcode) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DBUtil.makeConnection();

			String salt = SecureHash.getSalt();
			String hashPassword = SecureHash.getPBKDF2Password(password, salt);

			String sql = "INSERT IGNORE INTO reader (`first_name`, `last_name`, `email`, `phone`,`user_name`, `password`, `address`, `country`, `post_code`, `salt`) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?,?)";

			ps = conn.prepareStatement(sql);
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setString(5, username);
			ps.setString(6, hashPassword);
			ps.setString(7, address);
			ps.setString(8, country);
			ps.setInt(9, postcode);
			ps.setString(10, salt);

			int rs = ps.executeUpdate();

			if (rs == 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

		return false;
	}

	public List<Reader> getAllReaders() throws SQLException {

		List<Reader> readers = new ArrayList<Reader>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.makeConnection();

			String sql = "SELECT * FROM reader";

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				int it = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String userName = rs.getString("user_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String country = rs.getString("country");
				int postCode = rs.getInt("post_code");

				Reader reader = new Reader(it, firstName, lastName, email, phone, userName, password, address, country,
						postCode);

				readers.add(reader);

			}

			return readers;

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

	public void deleteReader(int readerId) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DBUtil.makeConnection();
			String sql = "DELETE FROM reader WHERE id = ?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, readerId);

			ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}

	public String setReaderTokenByEmail(String email, String token) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement pstm = null;

		try {

			conn = DBUtil.makeConnection();
			String sql = "SELECT * FROM reader WHERE email = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				sql = "UPDATE reader SET token = ? WHERE email = ?";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, token);
				pstm.setString(2, email);

				pstm.executeUpdate();

				String mailTo = rs.getString("email");
				return mailTo;

			}

			ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (pstm != null) {
				pstm.close();
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

	public String getReaderTokenByEmail(String email) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DBUtil.makeConnection();
			String sql = "SELECT * FROM reader WHERE email = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				String token = rs.getString("token");

				return token;

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

		return null;
	}

	public boolean resetReaderPasswordByToken(String email, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DBUtil.makeConnection();

			String salt = SecureHash.getSalt();
			String hashPassword = SecureHash.getPBKDF2Password(password, salt);

			System.out.println("salt" + salt);
			System.out.println("password" + hashPassword);

			String sql = "UPDATE reader SET password = ?, salt = ? WHERE email = ?";

			ps = conn.prepareStatement(sql);

			ps.setString(1, hashPassword);
			ps.setString(2, salt);
			ps.setString(3, email);

			int rs = ps.executeUpdate();

			if (rs == 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

		return false;

	}

	public void updateReaderDetails(String firstname, String lastname, String userName, String phone, String address,
			String country, int postcode) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DBUtil.makeConnection();

			String sql = "UPDATE reader SET first_name = ?, last_name = ?, phone = ?, address = ?, country = ?, post_code = ? WHERE user_name = ?";

			ps = conn.prepareStatement(sql);

			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, phone);
			ps.setString(3, address);
			ps.setString(3, country);
			ps.setInt(6, postcode);
			ps.setString(7, userName);

			ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}
}
