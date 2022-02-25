package truthunfold.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import truthunfold.Entity.Reader;
import truthfunfold.DAO.ReaderDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReaderDAO readerDAO = new ReaderDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String country = request.getParameter("country");
		int postcode = Integer.parseInt(request.getParameter("postcode"));

		try {

			boolean rs = readerDAO.registerNewReader(firstname, lastname, email, phone, username, password, address,
					country, postcode);
			HttpSession session = request.getSession();
			if (rs == true) {

				session.setAttribute("me", username);
				session.setAttribute("first", firstname);
				session.setAttribute("last", lastname);
				response.sendRedirect("HomeServlet");
			} else {
				String alertMsgReg = "Sorry, your username or email is already registered. Please choose a different one.";
				session.setAttribute("alertMsgReg", alertMsgReg);
				response.sendRedirect("account.jsp");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
