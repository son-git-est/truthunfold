package truthunfold.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import truthfunfold.DAO.ArticleDAO;
import truthunfold.Constant.*;
import truthunfold.Entity.Article;
import truthunfold.Entity.Topic;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArticleDAO articleDAO = new ArticleDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("search_field");
		String currentPage = request.getParameter("currentPage");

		if (currentPage != null) {

			Constant.CURRENT_PAGE = Integer.parseUnsignedInt(currentPage);

		} else {
			Constant.CURRENT_PAGE = 1;
		}

		try {

			if (Constant.CURRENT_PAGE < 1) {
				Constant.CURRENT_PAGE = 1;
			}

			Constant.TOTAL_ARTICLES = articleDAO.getTotalArticlesByKeyword(keyword);

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

			List<Article> articles = articleDAO.searchArticlesByKeyword(keyword, Constant.CURRENT_PAGE);

			request.setAttribute("articles", articles);
			request.setAttribute("search_keyword", keyword);
			request.setAttribute("currentPage", Constant.CURRENT_PAGE);
			request.setAttribute("totalPage", Constant.TOTAL_PAGES);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/search-result.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
