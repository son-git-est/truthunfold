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

import truthfunfold.DAO.OrderDAO;
import truthfunfold.DAO.OrderDetailsDAO;
import truthfunfold.DAO.ReaderDAO;
import truthunfold.Entity.Article;
import truthunfold.Entity.Order;
import truthunfold.Entity.OrderDetails;
import truthunfold.Entity.Reader;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Article> articles = new ArrayList<Article>();
	List<Order> orders = new ArrayList<Order>();
	List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
	OrderDAO orderDAO = new OrderDAO();
	OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();

	public OrderServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Reader me = (Reader) request.getSession().getAttribute("reader");

		String orderId = request.getParameter("orderId");
		Reader reader = (Reader) request.getSession().getAttribute("reader");

		if (orderId != null) {

			try {

				orderDetails = orderDetailsDAO.viewOrderDetails(Integer.parseInt(orderId));

				request.setAttribute("orderDetails", orderDetails);

				RequestDispatcher dispacher = request.getRequestDispatcher("read-logs.jsp");
				dispacher.forward(request, response);

			} catch (Exception e) {
				// TODO: handle exception
			}

		} else {

			try {

				orders = orderDAO.viewOrder(reader.getId());

				request.setAttribute("orders", orders);

				RequestDispatcher dispacher = request.getRequestDispatcher("read-logs.jsp");
				dispacher.forward(request, response);

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
