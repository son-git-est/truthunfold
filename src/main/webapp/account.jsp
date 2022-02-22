<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account</title>
<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	crossorigin="anonymous" />
</head>
<body>

	<%
	String me = (String) session.getAttribute("me");

	if (me != null) {
		response.sendRedirect("home.jsp");
	}

	String alertMsgReg = (String) session.getAttribute("alertMsgReg");
	%>


	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="site_content">
			<div id="content">

				<h1>Sign In</h1>

				<c:if test="${msgSuccess != null }">${msgSuccess}</c:if>


				<c:if test="${alertMsg != null}">
					<h4>${alertMsg}</h4>
					<a href="account-recovery.jsp">CLICK HERE</a>
				</c:if>
				<p>Please enter your account details here:</p>
				<form action="LoginServlet" method="post">
					<div class="form_settings">
						<p>
							<span>User Name</span><input class="contact" type="text"
								name="username" value="" required />
						</p>
						<p>
							<span>Password</span><input class="contact" type="password"
								name="password" value="" required />
						</p>
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="let's go" />
						</p>
					</div>
				</form>



				<h1>Don't have an account?</h1>
				<c:if test="${alertMsgReg!=null}">
					<h4>${alertMsgReg}</h4>
				</c:if>
				<p>Register here:</p>
				<form action="RegisterServlet" method="post">
					<div class="form_settings">
						<p>
							<span>First Name</span><input class="contact" type="text"
								name="firstname" value="" required />
						</p>
						<p>
							<span>Last Name</span><input class="contact" type="text"
								name="lastname" value="" required />
						</p>
						<p>
							<span>Email Address</span><input class="contact" type="text"
								name="email" value="" required />
						</p>
						<p>
							<span>Phone Number</span><input class="contact" type="text"
								name="phone" value="" required />
						</p>
						<p>
							<span>User Name</span><input class="contact" type="text"
								name="username" value="" required />
						</p>
						<p>
							<span>Password</span><input class="contact" type="password"
								name="password" id="password" value="" required />
						</p>
						<p>
							<span>Confirm Password</span><input class="contact"
								type="password" name="confirmPassword" id="confirmPassword"
								value="" required />
						</p>
						<p>
							<span>Address</span><input class="contact" type="text"
								name="address" value="" required />
						</p>
						<p>
							<span>Country</span><input class="contact" type="text"
								name="country" value="" required />
						</p>
						<!-- <p>
							<span>Country</span> <select id="id" name="name">
								<option value="1">Vietnam</option>
								<option value="2">New Zeland</option>
								<option value="3">Australia</option>
								<option value="4">US</option>
								<option value="5">Britan</option>
							</select>
						</p> -->

						<p>
							<span>Postal Code</span><input class="contact" type="number"
								name="postcode" value="" />
						</p>
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="create account"
								onclick="return Validate()" />
						</p>
					</div>
				</form>



			</div>
			<%-- <jsp:include page="sidebar.jsp"></jsp:include> --%>

		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

	<script>
		function Validate() {
			if (document.getElementById('password').value == document
					.getElementById('confirmPassword').value) {
				return true;
			} else {
				alert('Sorry, repeat password does not match');
				return false;
			}
		}
	</script>
</body>
</html>