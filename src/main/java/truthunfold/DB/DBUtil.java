package truthunfold.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection makeConnection() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			// final String url = "jdbc:mysql:///coding_mentor";
			final String url = "jdbc:mysql://localhost:3306/truth_unfold";
			final String user = "admin";
			final String password = "admin";

			Connection conn = DriverManager.getConnection(url, user, password);

			return conn;

			// DriverManager.getConnection("jdbc:mysql://localhost:3306/coding_mentor?serverTimezone=UTC",

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

}
