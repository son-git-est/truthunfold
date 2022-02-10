<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String admin = (String) session.getAttribute("admin");
pageContext.setAttribute("admin", admin);
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

				<h3>- welcome to back office page -</h3>
			</div>
		</div>
		<div id="menubar">
			<ul id="menu">
				<li><a href="ArticleServletBO">Manage Articles</a></li>

				<li><a href="ArticleServletBO?action=add_article">Add New
						Article</a></li>

				<li><a href="ArticleServletBO?action=view_topics">Manage
						Topics</a></li>

				<li><a href="ReaderServletBO">Manage Readers</a></li>

				<li><c:if test="${admin==null}">
						<a href="accountBO.jsp">Log In</a>
					</c:if></li>

				<li><c:if test="${admin!=null}">
						<a href="LoginServletBO?action=logout">Log Out</a>
					</c:if></li>
			</ul>
		</div>
	</div>
</body>
</html>
