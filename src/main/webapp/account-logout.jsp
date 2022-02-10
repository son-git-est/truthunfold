<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				session.setAttribute("userSigned",null);
				response.sendRedirect("home.jsp");
				%>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>