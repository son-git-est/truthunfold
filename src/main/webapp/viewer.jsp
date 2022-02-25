<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reader</title>
<link rel="stylesheet" type="text/css" href="style.css" title="style" />
<style>
#content_full {
	display: grid;
}

#articleTextarea {
	padding: 5px;
	min-width: 98.5%;
	max-width: 98.5%;
	font: 100% arial;
	border: 1px solid #E5E5E5;
	background: #FFF;
	color: #555;
	font-size: 1em;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	resize: none;
}
</style>

</head>
<body>



	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="site_content">
			<div id="content_full">
				<div>
					<h3>${article.title}</h3>
					<h5>${article.date}</h5>
					<h5>Read: ${article.visit}</h5>
					<h5>${article.head}</h5>
					<p>
						<img src="image/${article.id}.jpg" alt="article image"
							style="float: right; height: 300px;">${article.lead}</p>
					${article.body }
				</div>



				<div>
					<h3>Comments</h3>
					<form action="ArticleServlet" method="post">
						<div class="form_settings">

							<input type="hidden" name="action" value="add_comment"> <input
								type="hidden" name="articleId" value="${article.id }">
							<p>
								<input type="text" name="commentName" placeholder="your name"
									required>
							</p>
							<textarea id="articleTextarea" rows="3" name="commentBody"
								required style="font-size: 1em"
								placeholder="your comment here..."></textarea>


							<p style="padding-top: 15px">
								<span>&nbsp;</span><input class="submit" type="submit"
									name="article_submitted" value="add comment"
									style="margin-left: 500px" />
							</p>
						</div>

					</form>
				</div>

				<div>

					<c:forEach items="${comments }" var="comment">
						<h5>${comment.name }:</h5>

						<p>${comment.body }</p>
						<br />

					</c:forEach>

				</div>

			</div>


		</div>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>