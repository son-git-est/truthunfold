<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.*"%>
<%@page import="truthunfold.Entity.Article"%>
<%@page import="truthfunfold.DAO.ReaderDAO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
String me = (String) session.getAttribute("me");
pageContext.setAttribute("me", me);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SideBar</title>
</head>
<body>
	<div class="sidebar">

		<c:if test="${me != null }">

			<h3>You may like:</h3>

			<c:forEach items="${articlesSide}" var="articleSide">

				<h4>${articleSide.title}</h4>
				<h5>${articleSide.date}</h5>
				<h5>Read: ${articleSide.visit}</h5>
				<h5>${articleSide.head}<br /> <a
						href="ArticleServlet?articleId=${articleSide.id}">read more</a>
				</h5>




			</c:forEach>


		</c:if>
		<c:if test="${me == null }">

			<h3>Latest News</h3>
			<c:forEach items="${articlesSide}" var="articleSide">

				<h4>${articleSide.title}</h4>
				<h5>${articleSide.date}</h5>
				<h5>Read: ${articleSide.visit}</h5>
				<h5>${articleSide.head}<br /> <a
						href="ArticleServlet?articleId=${articleSide.id}">read more</a>
				</h5>




			</c:forEach>
		</c:if>




		<h3>Sources</h3>
		<ul>
			<li><a href="https://www.msn.com/en-nz">MSN</a></li>
			<li><a href="https://nz.news.yahoo.com/">Yahoo News</a></li>
			<li><a href="https://www.itnews.com.au/">IT News</a></li>
			<li><a href="https://www.cnet.com/news/">CNET</a></li>
			<li><a href="https://www.stuff.co.nz/">STUFF</a></li>
			<li><a href="https://www.nzherald.co.nz/">NZ HERALD</a></li>
			<li><a href="https://www.1news.co.nz/">1 NEWS</a></li>
			<li><a href="https://www.rnz.co.nz/">RNZ</a></li>
			<li><a href="https://www.newsroom.co.nz/">NEWSROOM</a></li>
			<li><a href="https://www.scoop.co.nz/">SCOOP</a></li>
			<li><a href="https://www.newstalkzb.co.nz/news/">NEWSTALKZB</a></li>
			<li><a href="https://www.newshub.co.nz/home/new-zealand.html">NEWSHUB</a></li>
		</ul>
		<h3>Search</h3>
		<form method="get" action="SearchServlet" id="search_form">
			<p>
				<input class="search" type="text" name="search_field"
					placeholder="Enter keywords....." minlength="3" /> <input
					name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;"
					src="style/search.png" alt="Search" title="Search" />
			</p>
		</form>
	</div>

</body>
</html>