<%@page import="java.util.*"%>

<%@page import="truthunfold.Entity.Article"%>
<%@page import="truthfunfold.DAO.ReaderDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%
String articleTopic = request.getParameter("articleTopic");

if (articleTopic == null) {

	articleTopic = "all";

}
pageContext.setAttribute("articleTopic", articleTopic);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Result</title>
<link rel="stylesheet" type="text/css" href="style.css" title="style" />
<style>
.center {
	text-align: center;
}

.pagination {
	display: inline-block;
}

.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
	transition: background-color .3s;
	border: 1px solid #ddd;
	margin: 0 4px;
}

.pagination a.active {
	background-color: #727575;
	color: white;
	border: 1px solid #727575;
}

.pagination a:hover:not(.active) {
	background-color: #ddd;
}
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">

		<div id="site_content">
			<div id="content_full">

				<h1>Search results for ${search_keyword}</h1>


				<c:forEach items="${articles}" var="article">

					<h3>${article.title}</h3>
					<h5>${article.date}</h5>
					<h5>Read: ${article.visit}</h5>
					<h5>${article.head}</h5>
					<p>${article.lead}</p>
					<p>
						<a href="ArticleServlet?articleId=${article.id }">read more</a>
					</p>

				</c:forEach>

			</div>
			<div class="center">
				<div class="pagination">
					<c:if test="${currentPage>1}">
						<a
							href="SearchServlet?currentPage=1&search_field=${search_keyword}">&laquo;</a>
						<a
							href="SearchServlet?currentPage=${currentPage-1}&search_field=${search_keyword}">&lt;</a>
						<a
							href="SearchServlet?currentPage=${currentPage-1}&search_field=${search_keyword}">${currentPage-1}</a>
					</c:if>
					<a
						href="SearchServlet?currentPage=${currentPage}&articleTopic=${articleTopic}"
						class="active">${currentPage }</a>
					<c:if test="${currentPage<totalPage }">
						<a
							href="SearchServlet?currentPage=${currentPage+1}&search_field=${search_keyword}">${currentPage+1}</a>
						<a
							href="SearchServlet?currentPage=${currentPage+1}&search_field=${search_keyword}">&gt;</a>
						<a
							href="SearchServlet?currentPage=${totalPage}&search_field=${search_keyword}">&raquo;</a>
					</c:if>


				</div>
			</div>


		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>