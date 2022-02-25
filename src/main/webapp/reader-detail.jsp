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
<title>Manage Reader Details</title>
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
			<div id="content">

				<h1>Reader Details</h1>
				
				<c:if test="${reader != null }">
				
				
				<form action="ReaderServletBO" method="post">
					<div class="form_settings">
						<label>ID</label><br/>
						<input type="hidden" name="action" value ="update_reader}">
						
						<input type="hidden" name="id" value ="${reader.id }">
						<input type="text" disabled="disabled" value ="${reader.id }"><br/>
						
						<label>First Name</label><br/>
						<input type="text" name="firstName" value ="${reader.firstName }"><br/>
						<label>Last Name</label><br/>
						<input type="text" name="lastName" value ="${reader.lastName }"><br/>
						<label>Email</label><br/>
						<input type="text" name="email" value ="${reader.email }"><br/>
						<label>Phone</label><br/>
						<input type="text" name="phone" value ="${reader.phone }"><br/>
						<label>User Name</label><br/>
						<input type="text" name="userName" value ="${reader.userName }"><br/>
						<label>Password</label><br/>
						<input type="text" disabled="disabled" name="password" value ="${reader.password }"><br/>
						<label>Address</label><br/>
						<input type="text" name="address" value ="${reader.address }"><br/>
						<label>Country</label><br/>
						<input type="text" name="country" value ="${reader.country }"><br/>
						<label>Postal code</label><br/>
						<input type="text" name="postcode" value ="${reader.postCode }"><br/>
						
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="update" />
						</p>
					</div>
				</form>
				
				</c:if>
				


			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>