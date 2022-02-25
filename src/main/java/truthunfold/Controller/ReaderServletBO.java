package truthunfold.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import truthfunfold.DAO.ReaderDAO;
import truthunfold.Entity.Reader;

@WebServlet("/ReaderServletBO")
public class ReaderServletBO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DELETE_READER = "delete_reader";
	private final static String VIEW_READER = "view_reader";
	ReaderDAO readerDAO = new ReaderDAO();

	public ReaderServletBO() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Reader> readers = null;
		String action = request.getParameter("action");
		String readerId = request.getParameter("readerId");
		RequestDispatcher dispatcher = request.getRequestDispatcher("reader-list.jsp");
		try {

			if (action != null && readerId != null) {

				switch (action) {
				case DELETE_READER:
					readerDAO.deleteReader(Integer.parseInt(readerId));

					readers = readerDAO.getAllReaders();

					request.setAttribute("readers", readers);

					dispatcher = request.getRequestDispatcher("reader-list.jsp");
					dispatcher.forward(request, response);
					break;
				case VIEW_READER:

					Reader reader = readerDAO.getReader(Integer.parseInt(readerId));

					request.setAttribute("reader", reader);

					dispatcher = request.getRequestDispatcher("reader-detail.jsp");
					dispatcher.forward(request, response);
					break;

				default:
					break;

				}

			} else {
				readers = readerDAO.getAllReaders();

				request.setAttribute("readers", readers);

				dispatcher = request.getRequestDispatcher("reader-list.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
