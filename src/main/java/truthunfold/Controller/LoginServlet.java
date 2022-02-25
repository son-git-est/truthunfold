package truthunfold.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import truthunfold.dto.CartDTO;
import truthfunfold.DAO.ReaderDAO;
import truthunfold.Entity.Reader;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReaderDAO userDAO = new ReaderDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// logout request sent by Header/Log Out
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String me = (String) session.getAttribute("me");

//		if (me != null) {
//			if (action != null && action.equals("logout")) {
//				session.setAttribute("me", null);
//				session.setAttribute("first", null);
//				session.setAttribute("last", null);
//				String alert = null;
//				session.setAttribute("alertMsg", alert);
//			}
//			response.sendRedirect("home.jsp");
//		} else {
//			response.sendRedirect("account.jsp");
//		}

		if (me == null) {

			response.sendRedirect("account.jsp");

		} else {

			if (action == null) {

				response.sendRedirect("HomeServlet");

			} else {

				switch (action) {

				case ("logout"):
					session.setAttribute("reader", null);
					session.setAttribute("me", null);
					session.setAttribute("first", null);
					session.setAttribute("last", null);

					String alert = null;
					session.setAttribute("alertMsg", alert);

					response.sendRedirect("HomeServlet");

					break;

				default:
					response.sendRedirect("account.jsp");

				}
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {

			Reader reader = userDAO.getReaderByUserNameAndPassword(username, password);
			HttpSession session = request.getSession();
			if (reader != null) {

				session.setAttribute("first", reader.getFirstName());
				session.setAttribute("last", reader.getLastName());
				session.setAttribute("me", reader.getUserName());
				session.setAttribute("reader", reader);
				session.setAttribute("cart", new CartDTO(new ArrayList<>()));
				response.sendRedirect("HomeServlet");

			} else {
				String alertMsg = "<strong>Opps... Login failed.</strong><br/>Your username and password do not match.<br/><em><i>Forgot your password?<i></em>";
				session.setAttribute("alertMsg", alertMsg);
				response.sendRedirect("account.jsp");

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
