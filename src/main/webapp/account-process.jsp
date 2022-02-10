
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
/* List<User> users = new ArrayList<>();
users.add(new User("Son", "Pham", "sonpham", "son.pham@email.com", "password"));
users.add(new User("Lucy", "Pham", "lucypham", "lucy.pham@email.com", "password"));
users.add(new User("tuyet", "Pham", "tuyetpham", "tuyet.pham@email.com", "password"));
users.add(new User("sally", "Pham", "sallypham", "sally.pham@email.com", "password"));
users.add(new User("anna", "Pham", "annapham", "anna.pham@email.com", "password")); */

//pageContext.setAttribute("users", users);

String username = request.getParameter("username");
String password = request.getParameter("password");

pageContext.setAttribute("username", username);
pageContext.setAttribute("password", password);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account</title>
<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	crossorigin="anonymous" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="site_content">
			<div id="content">

				<%
				if (username.equals("sonpham") && password.equals("password")) {
					session.setAttribute("userSigned", username);
					response.sendRedirect("home.jsp");
				}
				
				else if (username.equals("admin") && password.equals("admin")) {
					session.setAttribute("userSigned", username);
					response.sendRedirect("home.jsp");
				}

				else {
				%>
				<h2>Opps...</h2>
				<h1>Login FAILED!!!</h1>
				<br />
				<h1>Your Username and Password do not match.</h1>
				<br /> <a href="account.jsp">Click here to go back to the Login
					Page</a>
				<%
				}
				%>
				




			<%-- 	<c:if test="${username eq 'admin' && password == 'admin"}>
					session.setAttribute("userSigned", username);
					response.sendRedirect("home.jsp");
				</c:if>

				<c:forEach items="${users}" var="user">
					<c:if
						test="${username eq user.userName && password == user.password}">
							session.setAttribute("userSigned", username);
							response.sendRedirect("home.jsp");
					</c:if>

				</c:forEach> --%>



			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>