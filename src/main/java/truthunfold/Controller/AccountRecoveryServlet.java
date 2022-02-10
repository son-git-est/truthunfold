package truthunfold.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import truthfunfold.DAO.Email;
import truthfunfold.DAO.ReaderDAO;
import truthunfold.Constant.SecureHash;

/**
 * Servlet implementation class AccountRecoveryServlet
 */
@WebServlet("/AccountRecoveryServlet")
public class AccountRecoveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReaderDAO readerDAO = new ReaderDAO();
	Email emailRecovery = new Email();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountRecoveryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mailTo = request.getParameter("email");
		String token = SecureHash.generateToken();

		try {
			if (mailTo.equals(readerDAO.setReaderTokenByEmail(mailTo, token))) {

				emailRecovery.sendRecoveryEmail(mailTo, token);

				System.out.println("sent token" + token);

				String msg = "Please check your inbox for the recovery email!";
				request.setAttribute("msg", msg);
				request.setAttribute("token", token);
				RequestDispatcher dispatcher = request.getRequestDispatcher("account-recovery.jsp");
				dispatcher.forward(request, response);
			} else {

				RequestDispatcher dispatcher = request.getRequestDispatcher("/account.jsp");
				dispatcher.forward(request, response);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String token = request.getParameter("token").trim();
		String password = request.getParameter("password");
		HttpSession session = request.getSession();

		try {
			String storedToken = readerDAO.getReaderTokenByEmail(email);

			System.out.println("token" + token);

			System.out.println(storedToken);

			boolean validation = token.equals(storedToken);

			if (validation) {

				boolean resetPassword = readerDAO.resetReaderPasswordByToken(email, password);

				if (resetPassword) {

					String msgSuccess = "You password has been suceesfully reset!<br/>Please sign in with your new password";
					request.setAttribute("msgSuccess", msgSuccess);
					session.setAttribute("alertMsg", null);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/account.jsp");
					dispatcher.forward(request, response);

				}
			} else {

				RequestDispatcher dispatcher = request.getRequestDispatcher("/account.jsp");
				dispatcher.forward(request, response);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		// boolean validation =

	}

}
