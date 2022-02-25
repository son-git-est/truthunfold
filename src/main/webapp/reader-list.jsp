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
<title>Manage Readers</title>
<link rel="stylesheet" type="text/css" href="style.css" title="style" />
<style>
.moreBody {
	display: none;
}

th, td {
	word-wrap: break-word;
}
</style>
</head>
<body>
	<jsp:include page="headerBO.jsp" />
	<div id="main">

		<div id="site_content">
			<div id="content_full">

				<h1>List of Readers</h1>

				<table style="table-layout: fixed; width: 100%">
					<tr>

						<th>ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Phone</th>
						<th>User Name</th>

						<th>Address</th>
						<th>Country</th>
						<th>Postal code</th>
						<th colspan="2">Actions</th>
					</tr>

					<c:forEach items="${readers}" var="reader">
						<tr>

							<td>${reader.id }</td>
							<td>${reader.firstName }</td>
							<td>${reader.lastName }</td>
							<td>${reader.email }</td>
							<td>${reader.phone }</td>
							<td>${reader.userName }</td>

							<td>${reader.address }</td>
							<td>${reader.country }</td>
							<td>${reader.postCode }</td>
							<td><a
								href="ReaderServletBO?action=view_reader&readerId=${reader.id }">view
									reader</a></td>
							<td><a
								href="ReaderServletBO?action=delete_reader&readerId=${reader.id }">delete
									reader</a></td>

						</tr>

					</c:forEach>

				</table>



			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>