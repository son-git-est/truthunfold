package truthunfold.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import truthfunfold.DAO.ArticleDAO;
import truthunfold.Constant.Constant;
import truthunfold.Entity.Article;

/**
 * Servlet implementation class ArticleImageUploadServlet
 */
@MultipartConfig
@WebServlet("/ArticleImageUploadServlet")
public class ArticleImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleDAO articleDAO = new ArticleDAO();

	public ArticleImageUploadServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String articleId = request.getParameter("articleId");

		try {

			if (articleId != null) {

				Article article = null;

				int id = Integer.parseInt(articleId);

				article = articleDAO.getArticle(id);
				request.setAttribute("article", article);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/viewerBO.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String command = request.getParameter("command");
		String path = Constant.IMAGE_PATH;
		String articleId = request.getParameter("articleId");

		String title = request.getParameter("title");
		String topic = request.getParameter("topic");
		String date = request.getParameter("date");
		String head = request.getParameter("head");
		String lead = request.getParameter("lead");
		String body = request.getParameter("body");

		if (command.equals("UPLOAD_ARTICLE")) {

			try {
				String alertArticle;

				int id = articleDAO.addArticleWithImage(title, topic, date, head, lead, body);

				if (id > 0) {
					session.setAttribute("articleTitle", title);
					alertArticle = " is successfully added to database!";

					for (Part part : request.getParts()) {

						if (part.getName().equals("articleImage")) {

							System.out.println(part.getName());

							part.write(path + id + ".jpg");

						}

					}
				}

				else {
					alertArticle = "Sorry, an error has occured while adding the article. Please try again!";
				}

				session.setAttribute("alertArticle", alertArticle);

				response.sendRedirect("ArticleServletBO?action=add_article");

			} catch (SQLException e) {

				e.printStackTrace();
			}

		} else if (command.equals("UPLOAD_IMAGE")) {

			if (articleId != null) {

				int id = Integer.parseInt(articleId);

				for (Part part : request.getParts()) {

					if (part.getName().equals("articleImage")) {

						System.out.println(part.getName());

						part.write(path + id + ".jpg");

					}

				}

				//request.setAttribute("articleId", articleId);
				response.sendRedirect("ArticleImageUploadServlet?articleId="+articleId);
				

			}

		}

	}

}
