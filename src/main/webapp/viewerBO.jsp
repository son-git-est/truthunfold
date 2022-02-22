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


	<jsp:include page="headerBO.jsp" />
	<div id="main">
		<div id="site_content">
			<div id="content_full">

				<br />
				<form action="ArticleImageUploadServlet" method="post"
					enctype="multipart/form-data">
					<label>Upload a new image:</label><br /> <input type="hidden"
						name="command" value="UPLOAD_IMAGE"> <input type="hidden"
						name="articleId" value="${article.id}"> <input type="file"
						id="articleImage" name="articleImage" required> <input type="submit"
						value="upload">
				</form>


				<h3>${article.title}</h3>
				<h5>${article.date}</h5>
				<h5>Read: ${article.visit}</h5>
				<h5>${article.head}</h5>
				<p>
					<img src="image/${article.id}.jpg" alt="article image"
						style="float: right; height: 300px;">${article.lead}</p>
				${article.body }

			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>