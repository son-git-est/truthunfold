<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editor</title>
<link rel="stylesheet" type="text/css" href="style.css" title="style" />
<script
	src="https://cdn.ckeditor.com/ckeditor5/30.0.0/classic/ckeditor.js"></script>

<style>
.ck-editor__editable_inline {
	min-height: 500px;
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
}
</style>


</head>
<body>
	<jsp:include page="headerBO.jsp" />

	<%
	
	String alertArticle = (String) session.getAttribute("alertArticle");
	String articleTitle = (String) session.getAttribute("articleTitle");

	%>
	<div id="main">

		<div id="site_content">

			<div id="content_full">

				<h1>
					Welcome to Editor Mode.<br />Use the form below to add a new
					article to database.
				</h1>

				<c:if test="${alertArticle!=null}">
					<h4>The article: '${articleTitle}'${alertArticle}</h4>
				</c:if>



				<p>Add a new Article:</p>
				<form action="ArticleServletBO" method="post">
					<div class="form_settings">
						<p>
							<span>Title</span>
							<textarea id="articleTextarea" rows="2" name="title" required
								style="font-size: 1.5em" value="${article.name }"></textarea>
						</p>
						<P>
							<span>Topic</span><br /> <select name="topic">
								<c:forEach items="${topics}" var="topic">
									<option value="${topic.name}">${topic.name}</option>
								</c:forEach>
							</select>
						</P>
						<p>
							<span>Date added</span><br />
							<input type="date" name="date" value="2022-01-01"
								min="2000-01-01" required></input>
						</p>
						<p>
							<span>Head</span>
							<textarea id="articleTextarea" rows="3" name="head" required
								style="font-size: 1.2em"></textarea>
						</p>
						<p>
							<span>Lead</span>
							<textarea id="articleTextarea" rows="3" name="lead" required
								style="font-size: 1.2em"></textarea>
						</p>
						<p>
							<span>Body</span>
							<textarea id="editor" name="body"></textarea>
						</p>

						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="add now" />
						</p>
					</div>
				</form>

			</div>



		</div>

	</div>

	<%
	session.setAttribute("alertArticle", null);
	session.setAttribute("articleTitle", null);
	%>


	<script>
				ClassicEditor
			    .create( document.querySelector( '#editor' ), {
			        toolbar: [ 'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote' ],
			        heading: {
			            options: [
			            	 { model: 'heading3', view: 'h3', title: 'Heading 3', class: 'ck-heading_heading3' },
			            	 { model: 'heading4', view: 'h4', title: 'Heading 4', class: 'ck-heading_heading4' },
			                { model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' }
			              
			            ]
			        }
			    } )
			    .catch( error => {
			        console.log( error );
			    } );
				</script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>