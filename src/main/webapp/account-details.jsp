<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String first_name = (String) session.getAttribute("first");
pageContext.setAttribute("first_name", first_name);
String last_name = (String) session.getAttribute("last");
pageContext.setAttribute("last_name", last_name);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Details</title>
<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	crossorigin="anonymous" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">

		<div id="site_content">
			<div id="content">
				<!-- insert the page content here -->
				<h1>Welcome, ${first_name} ${last_name}</h1>


				<h4>
					<a href="CartServlet?action=VIEW"> Read List</a>:
					${sessionScope.cart.articles.size()} | <a href="OrderServlet">Read
						Later Logs</a>
				</h4>

				<c:if test="${sessionScope.showCart }">
					<c:forEach items="${cart.getArticles() }" var="article">
						<h5>
							&#9755; ${article.title } <a
								href="ArticleServlet?articleId=${article.id}">(read more</a> | <a
								href="CartServlet?action=REMOVE&articleId=${article.id}">remove)</a>
						</h5>

						<br />

					</c:forEach>

				</c:if>
				<br />
				<c:if test="${sessionScope.cart.articles.size()>0}">

					<form method="get" action="CartServlet">

						<input hidden="true" name="action" value="CHECKOUT"> <input
							type="submit" value="save to read logs">

					</form>

				</c:if>
				<br />
				<hr>

				<p>Please use the form below to update your contact details:</p>
				<form action="ReaderServlet" method="post">
					<div class="form_settings">
						<p>
							<span>First Name</span><input class="contact" type="text"
								name="firstname" value="${reader.firstName }" />
						</p>
						<p>
							<span>Last Name</span><input class="contact" type="text"
								name="lastname" value="${reader.lastName }" />
						</p>
						<p>
							<span>Phone</span><input class="contact" type="text" name="phone"
								value="${reader.phone }" />
						</p>
						<p>
							<span>Email Address</span><input
								style="background-color: #EEEEEE;" class="contact" type="text"
								name="email" value="${reader.email}" disabled="disabled" />
						</p>

						<p>
							<span>Username</span><input style="background-color: #EEEEEE;"
								class="contact" type="text" name="username"
								value="${reader.userName}" disabled="disabled" />
						</p>
						<p>
							<span>Password</span><input class="contact" type="password"
								style="background-color: #EEEEEE;" name="password"
								placeholder="***" disabled="disabled" />
						</p>
						<p>
							<span>Address</span><input class="contact" type="text"
								name="address" value="${reader.address}" />
						</p>

						<p>
							<span>Country</span><input class="contact" type="text"
								name="country" value="${reader.country}" />
						</p>

						<p>
							<span>Postal Code</span><input class="contact" type="text"
								name="address" value="${reader.postCode}" />
						</p>
						<p>
							<span>Reason</span>
							<textarea class="contact textarea" rows="16" cols="50"
								name="reason" required
								placeholder="reason is required to proceed with the update"></textarea>
						</p>
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="update" />
						</p>
					</div>
				</form>

			</div>
		</div>
	</div>

	<%
	request.getSession().setAttribute("showCart", false);
	%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>