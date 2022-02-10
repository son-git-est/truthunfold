package truthunfold.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import truthfunfold.DAO.ReaderDAO;

/**
 * Servlet implementation class ReaderServlet
 */
@WebServlet("/ReaderServlet")
public class ReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReaderDAO readerDAO = new ReaderDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReaderServlet() {
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

		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String userName = request.getParameter("username");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String country = request.getParameter("country");
		int postcode = Integer.parseInt(request.getParameter("postcode"));

		try {

			readerDAO.updateReaderDetails(firstName, lastName, userName, phone, address, country, postcode);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
