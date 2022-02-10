<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
/* List<Topic> topics = new ArrayList<>();
topics.add(new Topic("Business", 14, 1546));
topics.add(new Topic("Education", 34, 7845));
topics.add(new Topic("Cloud",53, 9684));
topics.add(new Topic("Networking",53, 9445));
topics.add(new Topic("Hardware", 75, 7845));
topics.add(new Topic("Security", 75, 12344));
topics.add(new Topic("Software", 124, 8945));
topics.add(new Topic("Finance", 256, 6845));
topics.add(new Topic("Industrial", 345, 6887));
topics.add(new Topic("Storage", 88, 16984));
topics.add(new Topic("Technology", 12, 1247));

pageContext.setAttribute("topics", topics); */

String first_name = (String) session.getAttribute("first");
pageContext.setAttribute("first_name", first_name);
String last_name = (String) session.getAttribute("last");
pageContext.setAttribute("last_name", last_name);
%>
<!DOCTYPE HTML>
<html>

<head>
<title>truth unfold</title>

<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div id="main">

		<div id="site_content">

			<div id="content">
				<!-- insert the page content here -->
				<h1>${first_name}
					${last_name}<br />Welcome to truth unfold: <br>- stories that
					you need to know.
				</h1>
				<p>In an age where the right information at the right time can
					make or break a deal, technology leaders rely on IT News for their
					daily fix of accurate, up-to-the-minute news, analysis and
					research.</p>
				<p>Information and communications technology is the engine room
					of the modern business. Business leaders tell us they rely on IT
					News to inform their strategy, make business cases for technology
					investments, set policies and chart their careers.</p>
				<p>Collectively, the team has continuously won a swag of awards
					which include Technology Title of the Year, Best News Title, Best
					Editor, Best Business Journalist, Best News Journalist and Best
					Technical Journalist.</p>
				<p>The team also curates technology conferences and judges the
					annual Benchmark Awards for excellence in ICT project delivery.</p>
				<p>The website is an important hub for technology enthusiasts to
					discuss the issues that interest them. Few publications can boast
					such intimacy with its audience, connected via feedback comments
					and forums.</p>

				<h2>News</h2>
				<table>
					<tr>

						<th>Topic</th>
						<th>Articles</th>
						<th>Visits</th>
						
					</tr>

					<c:forEach items="${topics}" var="topic">
						<tr>

							<td><a href="ArticleServlet?articleTopic=${topic.name}">${topic.name}</a></td>
							<td>${topic.quantity}</td>
							<td>${topic.visit}</td>
							
						</tr>

					</c:forEach>

				</table>

			</div>

			<jsp:include page="sidebar.jsp"></jsp:include>

		</div>

	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>

</html>