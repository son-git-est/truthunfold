<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reader</title>
<link rel="stylesheet" type="text/css" href="style.css" title="style" />


</head>
<body>

	<div id="fb-root"></div>
	<script async defer
		src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.6"></script>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="site_content">
			<div id="content_full">

				<h3>${article.title}</h3>
				<h5>${article.date}</h5>
				<h5>Read: ${article.visit}</h5>
				<h5>${article.head}</h5>
				<p>${article.lead}</p>
				${article.body }

				<div class="fb-comment-embed"
					data-href="https://www.facebook.com/zuck/posts/10102735452532991?comment_id=1070233703036185"
					data-width="500"></div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>