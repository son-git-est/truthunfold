<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String me = (String) session.getAttribute("me");
pageContext.setAttribute("me", me);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
</head>
<body>


	<div id="header">
		<div id="logo">
			<div id="logo_text">

				<h1>
					<a href="HomeServlet">truth<span class="logo_colour">
							unfold</span></a>
				</h1>

				<h3>- latest IT News -</h3>
			</div>
		</div>
		<div id="menubar">
			<ul id="menu">
				<li><a href="HomeServlet">Home</a></li>
				<!-- point to HomeServlet instead of home.jsp so sidebar can get data - latest news -->
				<li><a href="ArticleServlet">Articles</a></li>
				<!-- point to articleservlet instead of articles.sjp - articleservlet will get data from mysql and put data to articles.jsp -->
				<li><a href="contribution.jsp">Donation</a></li>
				<li><a href="contact.jsp">Contact Us</a></li>
				
				<li><c:if test="${me==null}">
						<a href="account.jsp">Account</a>
					</c:if> <c:if test="${me!=null}">
						<a href="account-details.jsp">${me}</a>
						<a href="LoginServlet?action=logout">Log Out</a>
					</c:if></li>
			</ul>
		</div>
	</div>
</body>
</html>
