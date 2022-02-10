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

import truthunfold.dto.CartDTO;
import truthfunfold.DAO.ArticleDAO;
import truthfunfold.DAO.OrderDAO;
import truthfunfold.DAO.OrderDetailsDAO;
import truthunfold.Entity.Article;
import truthunfold.Entity.Reader;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_ACTION_VIEW = "VIEW";
	private static final String REQUEST_ACTION_ADD = "ADD";
	private static final String REQUEST_ACTION_REMOVE = "REMOVE";
	private static final String REQUEST_ACTION_CHECKOUT = "CHECKOUT";

	ArticleDAO articleDAO = new ArticleDAO();
	OrderDAO orderDAO = new OrderDAO();
	OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();

	public CartServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("action");

			switch (action) {

			case REQUEST_ACTION_VIEW: {

				request.getSession().setAttribute("showCart", true);

				RequestDispatcher dispatcher = request.getRequestDispatcher("account-details.jsp");
				dispatcher.forward(request, response);

				return;
			}
			case REQUEST_ACTION_ADD: {

				String articleId = request.getParameter("articleId");
				Article article = articleDAO.getArticle(Integer.parseInt(articleId));

				CartDTO cart = (CartDTO) request.getSession().getAttribute("cart");

				boolean isExist = false;
				for (Article articleInCart : cart.getArticles()) {
					if (articleId.equals(articleInCart.getId() + "")) {
						isExist = true;
					}
				}

				if (!isExist) {
					cart.getArticles().add(article);
				}

				request.getSession().setAttribute("cart", cart);
				response.sendRedirect("ArticleServlet");
				return;
			}

			case REQUEST_ACTION_REMOVE: {

				String articleId = request.getParameter("articleId");

				CartDTO cart = (CartDTO) request.getSession().getAttribute("cart");

				List<Article> toRemove = new ArrayList<Article>();

				for (Article articleInCart : cart.getArticles()) {
					if (articleId.equals(articleInCart.getId() + "")) {
						toRemove.add(articleInCart);
					}
				}

				cart.getArticles().removeAll(toRemove);

				request.getSession().setAttribute("cart", cart);
				request.getSession().setAttribute("showCart", true);
				response.sendRedirect("account-details.jsp");
				return;

			}
			case REQUEST_ACTION_CHECKOUT: {

				Reader me = (Reader) request.getSession().getAttribute("reader");

				System.out.println(me);

				int readerId = (int) me.getId();
				System.out.println(readerId);

				CartDTO cart = (CartDTO) request.getSession().getAttribute("cart");
				List<Article> articles = cart.getArticles();

				orderDetailsDAO.insertOrder(readerId, articles);
				System.out.println(articles);
				cart.setArticles(new ArrayList<Article>());
				request.getSession().setAttribute("cart", cart);
				response.sendRedirect("account-details.jsp");
				return;

			}
			}

		} catch (NumberFormatException e) {

			e.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
