package truthunfold.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import truthfunfold.DAO.UserDAO;
import truthunfold.Entity.User;

@WebServlet("/LoginServletBO")
public class LoginServletBO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();

	public LoginServletBO() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String admin = (String) session.getAttribute("admin");

		if (admin == null) {

			response.sendRedirect("accountBO.jsp");

		} else {

			if (action == null) {

				response.sendRedirect("accountBO.jsp");

			} else {

				switch (action) {

				case ("logout"):
					session.setAttribute("admin", null);

					String alert = null;
					session.setAttribute("alertMsg", alert);

					response.sendRedirect("accountBO.jsp");

					break;

				default:
					response.sendRedirect("accountBO.jsp");

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

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			User user = userDAO.getUserByUserNameAndPassword(username, password);
			HttpSession session = request.getSession();
			if (user != null) {

				session.setAttribute("admin", user.getUserName());
				response.sendRedirect("ArticleServletBO");

			} else {
				String alertMsg = "<strong>Opps... Login failed.</strong><br/>";
				session.setAttribute("alertMsg", alertMsg);
				response.sendRedirect("accountBO.jsp");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
