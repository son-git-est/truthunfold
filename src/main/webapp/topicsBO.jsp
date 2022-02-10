<%@page import="java.util.*"%>

<%@page import="truthunfold.Entity.Article"%>
<%@page import="truthfunfold.DAO.ReaderDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Articles</title>
<link rel="stylesheet" type="text/css" href="style.css" title="style" />
<style>
.moreBody {
	display: none;
}
</style>
</head>
<body>
	<jsp:include page="headerBO.jsp" />
	<div id="main">

		<div id="site_content">
			<div id="content_full">

				<h1>List of topics</h1>
				<form action="ArticleServletBO?action=add_topic" method="get">
					<table style="max-width: 100%">
						<tr>

							<th>Topic Name</th>
							<th>Number of Articles</th>
							<th>Total Visits</th>
							<th colspan="4">Actions</th>
						</tr>

						<c:forEach items="${topics}" var="topic">
							<tr>

								<td>${topic.name}</td>
								<td>${topic.quantity}</td>
								<td>${topic.visit}</td>
								<td><a href="ArticleServletBO?articleTopic=${topic.name}">view
										articles</a></td>
								<td><a href="#">update name</a></td>
								<td><a href="#">show/hide topic</a></td>
								<td><a href="#">delete topics & articles</a></td>

							</tr>

						</c:forEach>

						<tr>
							<td colspan="1"><input type="text" name="topicName"
								placeholder="new topic here"></td>
							<td colspan="2"></td>
							<td><input type="submit" value="add new"></td>

						</tr>

					</table>
				</form>


			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>