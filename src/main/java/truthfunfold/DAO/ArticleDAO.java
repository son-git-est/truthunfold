package truthfunfold.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import truthunfold.Constant.*;
import truthunfold.DB.DBUtil;
import truthunfold.Entity.Article;
import truthunfold.Entity.Topic;

public class ArticleDAO {

	public int getTotalArticles() {
		int totalArticles = 0;
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = DBUtil.makeConnection();
			String sql = "SELECT COUNT(*) FROM article";
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {

				totalArticles = rs.getInt(1);

				return totalArticles;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalArticles;
	}

	public Article getArticle(int articleId) throws SQLException {

		Connection conn = null;
		PreparedStatement pstm = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.makeConnection();

			String sql = "SELECT * FROM article WHERE id = ?";
			PreparedStatement pstn = conn.prepareStatement(sql);
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, articleId);
			rs = pstm.executeQuery();

			rs.next();

			int id = articleId;
			String title = rs.getString("title");
			String topic = rs.getString("topic");
			String date = rs.getString("date");
			String head = rs.getString("head");
			String lead = rs.getString("lead");
			String body = rs.getString("body");
			int visit = rs.getInt("visit") + 1;

			Article article = new Article(id, title, topic, date, head, lead, body, visit);

			sql = "UPDATE article SET visit = ? WHERE (id = ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, visit);
			ps.setInt(2, articleId);

			ps.executeUpdate();
			return article;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			close(conn, pstm, rs);
		}

		return null;
	}

	public List<Article> getAllArticles(int currentPage) throws SQLException {

		List<Article> articles = new ArrayList<Article>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.makeConnection();

			currentPage = (currentPage - 1) * Constant.ARTICLES_PER_PAGE;

			System.out.println("conn " + conn);
			String sql = "SELECT * FROM article ORDER BY date DESC LIMIT ? OFFSET ?";

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, Constant.ARTICLES_PER_PAGE);
			pstm.setInt(2, currentPage);

			rs = pstm.executeQuery();

			System.out.println("rs " + rs);
			while (rs.next()) {

				int id = rs.getInt("id");
				String title = rs.getString("title");
				String topic = rs.getString("topic");
				String date = rs.getString("date");
				String head = rs.getString("head");
				String lead = rs.getString("lead");
				String body = rs.getString("body");
				int visit = rs.getInt("visit");

				Article article = new Article(id, title, topic, date, head, lead, body, visit);
				articles.add(article);
			}

		} finally {

			close(conn, pstm, rs);
		}

		return articles;

	}

	public List<Article> getLatestArticles() throws SQLException {

		List<Article> articlesSide = new ArrayList<Article>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.makeConnection();

			System.out.println("conn " + conn);

			String sql = "SELECT * FROM article ORDER BY date DESC LIMIT 3";

			pstm = conn.prepareStatement(sql);
			System.out.println("sql " + sql);

			System.out.println("pstm " + pstm);
			rs = pstm.executeQuery();

			System.out.println("rs " + rs);
			while (rs.next()) {

				int id = rs.getInt("id");
				String title = rs.getString("title");
				String topic = rs.getString("topic");
				String date = rs.getString("date");
				String head = rs.getString("head");
				String lead = rs.getString("lead");
				String body = rs.getString("body");
				int visit = rs.getInt("visit");

				Article article = new Article(id, title, topic, date, head, lead, body, visit);
				articlesSide.add(article);
			}

		} finally {

			close(conn, pstm, rs);
		}

		return articlesSide;

	}

	public List<Topic> getAllTopics() throws SQLException {

		List<Topic> topics = new ArrayList<Topic>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.makeConnection();

			// System.out.println("conn " + conn);

			String sql = "select t.id, t.name, count(a.id) as num, sum(a.visit) as visit\r\n" + "from topic as t\r\n"
					+ "left join article as a on t.name = a.topic\r\n" + "group by t.id";

			pstm = conn.prepareStatement(sql);
			// System.out.println("sql " + sql);

			// System.out.println("pstm " + pstm);
			rs = pstm.executeQuery();

			// System.out.println("rs " + rs);
			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				int quantity = rs.getInt("num");
				int visit = rs.getInt("visit");

				Topic topic = new Topic(id, name, quantity, visit);
				topics.add(topic);
			}

		} finally {

			close(conn, pstm, rs);
		}

		return topics;

	}

	public boolean addArticle(String title, String topic, String date, String head, String lead, String body)
			throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DBUtil.makeConnection();

			String sql = "INSERT IGNORE INTO article (`title`, `topic`, `date`, `head`,`lead`, `body`) VALUES (?, ?, ?,?, ?, ?)";

			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, topic);
			ps.setString(3, date);
			ps.setString(4, head);
			ps.setString(5, lead);
			ps.setString(6, body);

			int rs = ps.executeUpdate();

			if (rs == 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally { // must be closed after all queries finish to prevent overwhelming server

			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

		return false;
	}

	public int addArticleWithImage(String title, String topic, String date, String head, String lead, String body)
			throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.makeConnection();

			String sql = "INSERT IGNORE INTO article (`title`, `topic`, `date`, `head`,`lead`, `body`) VALUES (?, ?, ?,?, ?, ?)";

			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, title);
			ps.setString(2, topic);
			ps.setString(3, date);
			ps.setString(4, head);
			ps.setString(5, lead);
			ps.setString(6, body);

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				int articleId = rs.getInt(1);
				return articleId;
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally { // must be closed after all queries finish to prevent overwhelming server

			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}

		}

		return 0;
	}

	private void close(Connection conn, PreparedStatement pstm, ResultSet rs) {

		try {

			if (rs != null) {
				rs.close();
			}

			if (pstm != null) {
				pstm.close();
			}

			if (conn != null) {
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean deleteArticle(int articleId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DBUtil.makeConnection();

			String sql = "DELETE FROM article WHERE id = ?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, articleId);

			int rs = ps.executeUpdate();

			if (rs == 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally { // must be closed after all queries finish to prevent overwhelming server

			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return false;

	}

	public List<Article> getAllArticlesByTopic(String articleTopic, int currentPage) throws SQLException {

		List<Article> articles = new ArrayList<Article>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.makeConnection();

			currentPage = (currentPage - 1) * Constant.ARTICLES_PER_PAGE;

			System.out.println("conn " + conn);
			String sql = "SELECT * FROM article WHERE article.topic = ? ORDER BY date DESC LIMIT ? OFFSET ?";

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, articleTopic);
			pstm.setInt(2, Constant.ARTICLES_PER_PAGE);
			pstm.setInt(3, currentPage);

			rs = pstm.executeQuery();

			System.out.println("rs " + rs);
			while (rs.next()) {

				int id = rs.getInt("id");
				String title = rs.getString("title");
				String topic = rs.getString("topic");
				String date = rs.getString("date");
				String head = rs.getString("head");
				String lead = rs.getString("lead");
				String body = rs.getString("body");
				int visit = rs.getInt("visit");

				Article article = new Article(id, title, topic, date, head, lead, body, visit);
				articles.add(article);
			}

		} finally {

			close(conn, pstm, rs);
		}

		return articles;

	}

	public int getTotalArticlesByTopic(String articleTopic) {
		int totalArticles = 0;
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = DBUtil.makeConnection();
			String sql = "SELECT COUNT(*) FROM article WHERE article.topic = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, articleTopic);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {

				totalArticles = rs.getInt(1);

				return totalArticles;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalArticles;
	}

	public List<Article> searchArticlesByKeyword(String keyword, int currentPage) throws SQLException {

		List<Article> articles = new ArrayList<Article>();

		// keyword = "'%" + keyword + "%'";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.makeConnection();
			currentPage = (currentPage - 1) * Constant.ARTICLES_PER_PAGE;
			// currentPage = (currentPage - 1) * Constant.ARTICLES_PER_PAGE;

			System.out.println("conn " + conn);
			System.out.println(Constant.ARTICLES_PER_PAGE);
			System.out.println(currentPage);
			String sql = "SELECT * FROM article WHERE `body` LIKE ? OR `title` LIKE ? OR `lead` LIKE ? OR `head` LIKE ? ORDER BY date DESC LIMIT ? OFFSET ?";

			pstm = conn.prepareStatement(sql);
			// pstm.setInt(1, Constant.ARTICLES_PER_PAGE);
			// pstm.setInt(2, currentPage);
			pstm.setString(1, "%" + keyword + "%");
			pstm.setString(2, "%" + keyword + "%");
			pstm.setString(3, "%" + keyword + "%");
			pstm.setString(4, "%" + keyword + "%");
			pstm.setInt(5, Constant.ARTICLES_PER_PAGE);
			pstm.setInt(6, currentPage);

			rs = pstm.executeQuery();

			// System.out.println("sql" + sql);
			while (rs.next()) {

				int id = rs.getInt("id");
				String title = rs.getString("title");
				String topic = rs.getString("topic");
				String date = rs.getString("date");
				String head = rs.getString("head");
				String lead = rs.getString("lead");
				String body = rs.getString("body");
				int visit = rs.getInt("visit");

				Article article = new Article(id, title, topic, date, head, lead, body, visit);
				articles.add(article);
			}

			return articles;

		} finally {

			close(conn, pstm, rs);
		}

	}

	public int getTotalArticlesByKeyword(String keyword) throws SQLException {

		// List<Article> articles = new ArrayList<Article>();

		// keyword = "'%" + keyword + "%'";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		System.out.println(keyword);
		try {

			conn = DBUtil.makeConnection();

			// currentPage = (currentPage - 1) * Constant.ARTICLES_PER_PAGE;

			// System.out.println("conn " + conn);
			String sql = "SELECT COUNT(*) FROM article WHERE `body` LIKE ? OR `title` LIKE ? OR `lead` LIKE ? OR `head` LIKE ?";

			pstm = conn.prepareStatement(sql);
			// pstm.setInt(1, Constant.ARTICLES_PER_PAGE);
			// pstm.setInt(2, currentPage);
			pstm.setString(1, "%" + keyword + "%");
			pstm.setString(2, "%" + keyword + "%");
			pstm.setString(3, "%" + keyword + "%");
			pstm.setString(4, "%" + keyword + "%");

			rs = pstm.executeQuery();

			// System.out.println("sql" + sql);
			if (rs.next()) {

				int totalArticles = rs.getInt(1);

				System.out.println(totalArticles);
				return totalArticles;
			}

			return 0;

		} finally {

			close(conn, pstm, rs);
		}

	}
}
