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
<title>Read Logs</title>
<link rel="stylesheet" type="text/css" href="style.css" title="style" />

</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">

		<div id="site_content">
			<div id="content_full">
				<c:if test="${orders != null }">

					<h3>Read Logs</h3>

					<c:forEach items="${orders}" var="order">
						<p>Log ID: ${order.id }</p>
						<p>Log Date: ${order.date}</p>
						<a href="OrderServlet?orderId=${order.id}">View Order Details</a>
						<br />
						<hr>
						<br />
					</c:forEach>
				</c:if>

				<c:if test="${orderDetails != null }">

					<h3>Log Details</h3>

					<c:forEach items="${orderDetails}" var="orderDetail">
						<p>Log ID: ${orderDetail.orderId }</p>
						<p>Article ID: ${orderDetail.articleId }</p>
						<p>
							Article Title: <a
								href="ArticleServlet?articleId=${orderDetail.articleId}">${orderDetail.articleTitle}</a>
						</p>

						<hr>
						<br />
					</c:forEach>
				</c:if>
			</div>




		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>