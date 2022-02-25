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
				<p>Firstly, please let me say thank you for going this far to
					review my application. As I do not come from an IT background, this
					project will help show how much I have learned, and how far I have
					gone on the way to become a web developer. Hopefully, you will find
					the answer here.</p>
				<p>This home page provides brief information about the site. A
					table shows topics covered, number of articles in each topic, and
					number of visits</p>
				<p>Header provides links to other functions/pages of the site.
					Reader can choose to create an account and contact the site
					administrator by using the contact form.</p>
				<p>In case of lost password, reader can always use the recovery
					function which will supply them a recovery token and enables them
					to reset their password. Reader's password is encrypted and cannot
					be read by site administrator.</p>
				<p>The sidebar shows five latest pieces of news. For a
					registered reader, it will be able to suggest articles based on
					their reading logs.</p>
				<p>A registered reader can save articles to read later, and keep
					of a log of all read articles.</p>
				<p>At bottom right corner, readers can search for articles based
					on keywords.</p>
				<p>Reader can show their support for the site by making a small
					donation. The donation function employs Paypal API, and is tested
					successfully in sandbox.</p>
				<p>For back office, admin is provided these functions: edit,
					delete, add new articles. Edit and add new topics. Manage readers.</p>
				<p>Unfortunately, this project currently does not employ any
					modern framework, and not distinguish between front-end and
					back-end. However, I believe it is not a bad thing. One may need
					to master the cores before moving on using supporting frameworks and
					becoming not understanding the underlying processes.</p>
				<p>This website is being rebuilt to make clear the functions of
					front-end and back-end in which the two sides will talk via RESTful
					APIs in JSON language. Front-end will send request either via HTML
					forms, or Javascript fetch. Back-end will employ the new modern
					framework Spring and Hibernate. For now, you will not find them
					here. It will be another project.</p>

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