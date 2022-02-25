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

import truthunfold.Constant.*;
import truthfunfold.DAO.ArticleDAO;
import truthunfold.Entity.Article;
import truthunfold.Entity.Comment;
import truthunfold.Entity.Topic;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArticleDAO articleDAO = new ArticleDAO();

	public ArticleServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// getting all articles or single article by ID (for end user)

		String articleId = request.getParameter("articleId");
		String articleTopic = request.getParameter("articleTopic");
		String currentPage = request.getParameter("currentPage");

		if (currentPage != null) {

			Constant.CURRENT_PAGE = Integer.parseUnsignedInt(currentPage);

		} else {
			Constant.CURRENT_PAGE = 1;
		}

		try {
			if (articleId != null) {

				Article article = null;

				int id = Integer.parseInt(articleId);

				article = articleDAO.getArticle(id);
				List<Comment> comments = articleDAO.getComment(id);

				request.setAttribute("article", article);
				request.setAttribute("comments", comments);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/viewer.jsp");
				dispatcher.forward(request, response);
			}

			else if (articleTopic != null && !articleTopic.equals("all")) {

				List<Topic> topics = articleDAO.getAllTopics();

				if (Constant.CURRENT_PAGE < 1) {
					Constant.CURRENT_PAGE = 1;
				}

				Constant.TOTAL_ARTICLES = articleDAO.getTotalArticlesByTopic(articleTopic);

				System.out.println(Constant.TOTAL_ARTICLES);

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

				List<Article> articles = articleDAO.getAllArticlesByTopic(articleTopic, Constant.CURRENT_PAGE);

				request.setAttribute("articles", articles);
				request.setAttribute("articleTopic", articleTopic);
				request.setAttribute("currentPage", Constant.CURRENT_PAGE);
				request.setAttribute("totalPage", Constant.TOTAL_PAGES);
				request.setAttribute("topics", topics);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/articles.jsp");
				dispatcher.forward(request, response);

			}

			else {

				List<Topic> topics = articleDAO.getAllTopics();

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
				request.setAttribute("topics", topics);
				request.setAttribute("currentPage", Constant.CURRENT_PAGE);
				request.setAttribute("totalPage", Constant.TOTAL_PAGES);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/articles.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action != null && action.equals("add_comment")) {
			try {
				String name = request.getParameter("commentName");
				String body = request.getParameter("commentBody");
				String articleId = request.getParameter("articleId");
				int id = Integer.parseInt(articleId);

				articleDAO.addComment(id, name, body);

				response.sendRedirect("ArticleServlet?articleId=" + id);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
