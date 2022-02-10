//package servlet;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet("/AccountServlet")
//public class AccountServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public AccountServlet() {
//		super();
//
//	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		// logout request sent by Header/Log Out
//		HttpSession session = request.getSession();
//		String action = request.getParameter("action");
//		String me = (String) session.getAttribute("userSigned");
//
//		if (me != null) {
//			if (action != null && action.equals("logout")) {
//				session.setAttribute("userSigned", null);
//				String alert = null;
//				session.setAttribute("alertMsg", alert);
//			}
//			response.sendRedirect("home.jsp");
//		} else {
//			response.sendRedirect("account.jsp");
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		// log in when username/password match and send user to Home page, otherwise
//		// redirect back to Log In page
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//
//		if (username.equals("admin") && password.equals("admin")) {
//			HttpSession session = request.getSession();
//			session.setAttribute("userSigned", username);
//			response.sendRedirect("home.jsp");
//
//		}
//
//		else if (username.equals("sonpham") && password.equals("password")) {
//			HttpSession session = request.getSession();
//			session.setAttribute("userSigned", username);
//			response.sendRedirect("home.jsp");
//		}
//
//		else {
//			HttpSession session = request.getSession();
//			String alert = "<strong>Opps... Login failed.</strong><br/>Your username and password do not match.";
//			session.setAttribute("alertMsg", alert);
//			response.sendRedirect("account.jsp");
//		}
//
//	}
//
//}
