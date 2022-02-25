package truthunfold.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import truthfunfold.DAO.ArticleDAO;
import truthfunfold.DAO.Email;
import truthunfold.Entity.Article;
import truthunfold.Entity.Reader;
import truthunfold.Entity.Topic;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleDAO articleDAO = new ArticleDAO();
	Email emailEnquiry = new Email();

	public HomeServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Article> articlesSide = new ArrayList<Article>();
			Reader me = (Reader) request.getSession().getAttribute("reader");
			if (me != null) {

				int id = me.getId();
				articlesSide = articleDAO.getRecommendedArticles(id);

				if (articlesSide.size() < 3) {
					articlesSide = articleDAO.getLatestArticles();
				}

			} else {

				articlesSide = articleDAO.getLatestArticles();

			}
			List<Topic> topics = articleDAO.getAllTopics();
			
			request.setAttribute("articlesSide", articlesSide);
			request.setAttribute("topics", topics);

			HttpSession session = request.getSession();
			session.setAttribute("topicAll", topics);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * try {
		 * 
		 * List<Topic> topics = articleDAO.getAllTopics();
		 * 
		 * request.setAttribute("topics", topics);
		 * 
		 * RequestDispatcher dispatcher2 = request.getRequestDispatcher("/home.jsp");
		 * dispatcher2.forward(request, response);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String inquiry = request.getParameter("inquiry");

		emailEnquiry.sendContactUsEmail(name, email, inquiry);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/contact-sent.jsp");
		dispatcher.forward(request, response);

	}

}
