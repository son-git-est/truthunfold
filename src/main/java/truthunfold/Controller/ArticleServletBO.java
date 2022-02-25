package truthunfold.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import truthfunfold.DAO.ArticleDAO;
import truthunfold.Constant.Constant;
import truthunfold.Entity.Article;
import truthunfold.Entity.Topic;

/**
 * Servlet implementation class ArticleServletBO
 */
@WebServlet("/ArticleServletBO")
public class ArticleServletBO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleDAO articleDAO = new ArticleDAO();

	private final static String ADD_ARTICLE = "add_article";
	private final static String GET_ALL_TOPICS = "view_topics";
	private final static String DELETE_ARTICLE = "delete_article";
	private final static String UPDATE_ARTICLE = "update_article";
	private final static String DELETE_TOPIC = "delete_topic";
	private final static String ADD_TOPIC = "add_topic";
	private final static String EDIT_TOPIC = "edit_topic";

	public ArticleServletBO() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		String currentPage = request.getParameter("currentPage");
		if (currentPage != null) {

			Constant.CURRENT_PAGE = Integer.parseUnsignedInt(currentPage);

		} else {
			Constant.CURRENT_PAGE = 1;
		}

		try {

			if (action != null) {

				switch (action) {

				case ADD_ARTICLE: {
					List<Topic> topics = articleDAO.getAllTopics();

					request.setAttribute("topics", topics);

					RequestDispatcher dispatcher = request.getRequestDispatcher("/editor.jsp");
					dispatcher.forward(request, response);
					break;

				}
				case DELETE_ARTICLE: {

					if (request.getParameter("articleId") != null) {
						int articleId = Integer.parseInt(request.getParameter("articleId"));
						articleDAO.deleteArticle(articleId);

						String msg = "article " + articleId + " has been deleted!";
						request.setAttribute("msg", msg);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/articlesBO.jsp");
						dispatcher.forward(request, response);
					}

					break;

				}
				case UPDATE_ARTICLE: {

					String msg = "article has been updated!";

					request.setAttribute("msg", msg);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/articlesBO.jsp");
					dispatcher.forward(request, response);
					break;
				}

				case GET_ALL_TOPICS: {

					List<Topic> topics = articleDAO.getAllTopics();

					request.setAttribute("topics", topics);

					RequestDispatcher dispatcher = request.getRequestDispatcher("/topicsBO.jsp");
					dispatcher.forward(request, response);
					break;
				}

				case DELETE_TOPIC: {

					if (request.getParameter("articleTopic") != null) {
						String topic = request.getParameter("articleTopic");
						articleDAO.deleteTopic(topic);

						String msg = "Topic " + topic + " has been deleted along with all the articles!";
						request.setAttribute("msg", msg);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/articlesBO.jsp");
						dispatcher.forward(request, response);
					}
					break;
				}

				case ADD_TOPIC: {

					if (request.getParameter("topicName") != null) {
						String topic = request.getParameter("topicName");
						articleDAO.addnewTopic(topic);
						System.out.println(topic);
						String msg = "Topic " + topic + " has been added!";
						request.setAttribute("msg", msg);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("ArticleServletBO?action=view_topics");
						dispatcher.forward(request, response);
					}
					break;
				}

				case EDIT_TOPIC: {

					if (request.getParameter("id") != null) {
						String topic = request.getParameter("topicName");
						int id = Integer.parseInt(request.getParameter("id"));
						articleDAO.editTopic(id, topic);
						// System.out.println(topic);
						// String msg = "Topic " + topic + " has been added!";
						// request.setAttribute("msg", msg);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("ArticleServletBO?action=view_topics");
						dispatcher.forward(request, response);
					}
					break;
				}

				}
			} else {
				if (Constant.CURRENT_PAGE < 1) {
					Constant.CURRENT_PAGE = 1;
				}

				Constant.TOTAL_ARTICLES = articleDAO.getTotalArticles();

				if (Constant.TOTAL_ARTICLES == 0) {

					Constant.TOTAL_ARTICLES = 1;

				}

				else if (Constant.TOTAL_ARTICLES % Constant.ARTICLES_PER_PAGE == 0) {

					Constant.TOTAL_PAGES = Constant.TOTAL_ARTICLES / Constant.ARTICLES_PER_PAGE;

				} else {

					Constant.TOTAL_PAGES = Constant.TOTAL_ARTICLES / Constant.ARTICLES_PER_PAGE + 1;
				}

				if (Constant.CURRENT_PAGE > Constant.TOTAL_PAGES) {
					Constant.CURRENT_PAGE = Constant.TOTAL_PAGES;
				}

				List<Article> articles = articleDAO.getAllArticles(Constant.CURRENT_PAGE);

				request.setAttribute("articles", articles);
				request.setAttribute("currentPage", Constant.CURRENT_PAGE);
				request.setAttribute("totalPage", Constant.TOTAL_PAGES);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/articlesBO.jsp");
				dispatcher.forward(request, response);

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String topic = request.getParameter("topic");
		String date = request.getParameter("date");
		String head = request.getParameter("head");
		String lead = request.getParameter("lead");
		String body = request.getParameter("body");

		try {
			String alertArticle;

			boolean rs = articleDAO.addArticle(title, topic, date, head, lead, body);
			HttpSession session = request.getSession();

			if (rs == true) {

				alertArticle = " is successfully added to database!";
			}

			else {
				alertArticle = "Sorry, an error has occured while adding the article. Please try again!";
			}

			session.setAttribute("articleTitle", title);
			session.setAttribute("alertArticle", alertArticle);

			response.sendRedirect("editor.jsp");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
