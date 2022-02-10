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
<title>Articles</title>
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
	<jsp:include page="headerBO.jsp" />
	<div id="main">

		<div id="site_content">
			<div id="content_full">

				<h1>List of Articles</h1>
				<%-- <form action="ArticleServletBO" method="get">
					<div class="form_settings">
						<select id="id" name="articleTopic">
							<option value="all">all</option>
							<option value="business"
								<c:if test="${param.articleTopic == 'business'}">selected</c:if>>business</option>
							<option value="education"
								<c:if test="${param.articleTopic == 'education'}">selected</c:if>>education</option>
							<option value="cloud"
								<c:if test="${param.articleTopic == 'cloud'}">selected</c:if>>cloud</option>
							<option value="networking"
								<c:if test="${param.articleTopic == 'networking'}">selected</c:if>>networking</option>
							<option value="hardware"
								<c:if test="${param.articleTopic == 'hardware'}">selected</c:if>>hardware</option>
							<option value="security"
								<c:if test="${param.articleTopic == 'security'}">selected</c:if>>security</option>
							<option value="software"
								<c:if test="${param.articleTopic == 'software'}">selected</c:if>>software</option>
							<option value="finance"
								<c:if test="${param.articleTopic == 'finance'}">selected</c:if>>finance</option>
							<option value="industrial"
								<c:if test="${param.articleTopic == 'industrial'}">selected</c:if>>industrial</option>
							<option value="storage"
								<c:if test="${param.articleTopic == 'storage'}">selected</c:if>>storage</option>
							<option value="technology"
								<c:if test="${param.articleTopic == 'technology'}">selected</c:if>>technology</option>
						</select>
						<p style="padding-top: 15px">
							<input class="submit" type="submit" value="search" />
						</p>
					</div>
				</form> --%>

				<c:if test="${msg != null }">${msg}</c:if>

				<c:forEach items="${articles}" var="article">



					<c:choose>

						<c:when test="${fn:toLowerCase(articleTopic) eq 'all'}">
							<h3>${article.title}</h3>
							<h5>${article.date}</h5>
							<p>
								<a
									href="ArticleServletBO?action=delete_article&articleId=${article.id }">delete
									article</a> | <a
									href="ArticleServletBO?action=update_article&articleId=${article.id }">update
									article</a>
							</p>

						</c:when>

						<c:when test="${fn:toLowerCase(article.topic) eq articleTopic}">
							<h3>${article.title}</h3>
							<h5>${article.date}</h5>
							<p>
								<a
									href="ArticleServletBO?action=delete_article&articleId=${article.id }">delete
									article</a> | <a
									href="ArticleServletBO?action=update_article&articleId=${article.id }">update
									article</a>
							</p>

						</c:when>

					</c:choose>

				</c:forEach>

			</div>
			
			
			<div class="center">
				<div class="pagination">
					<c:if test="${currentPage>1}">
						<a
							href="ArticleServletBO?currentPage=1&articleTopic=${articleTopic}">&laquo;</a>
						<a
							href="ArticleServletBO?currentPage=${currentPage-1}&articleTopic=${articleTopic}">&lt;</a>
						<a
							href="ArticleServletBO?currentPage=${currentPage-1}&articleTopic=${articleTopic}">${currentPage-1}</a>
					</c:if>
					<a
						href="ArticleServletBO?currentPage=${currentPage}&articleTopic=${articleTopic}"
						class="active">${currentPage }</a>
					<c:if test="${currentPage<totalPage }">
						<a
							href="ArticleServletBO?currentPage=${currentPage+1}&articleTopic=${articleTopic}">${currentPage+1}</a>
						<a
							href="ArticleServletBO?currentPage=${currentPage+1}&articleTopic=${articleTopic}">&gt;</a>
						<a
							href="ArticleServletBO?currentPage=${totalPage}&articleTopic=${articleTopic}">&raquo;</a>
					</c:if>


				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>