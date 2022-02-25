<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact</title>
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
				<h1>Account Recovery</h1>



				<c:if test="${msg == null }">

					<h3>Please fill in the form below and follow steps in the
						recovery email to set up a new password.</h3>
					<form action="AccountRecoveryServlet" method="get">
						<div class="form_settings">

							<p>
								<span>Email Address</span><input class="contact" type="text"
									name="email" value="" />
							</p>

							<p style="padding-top: 15px">
								<span>&nbsp;</span><input class="submit" type="submit"
									name="contact_submitted" value="send email" />
							</p>
						</div>
					</form>
				</c:if>

				<c:if test="${msg !=  null }">

					<p>${msg }</p>

					<%
					request.setAttribute("msg", null);
					%>

				</c:if>



				<h3>Got a recovery token? Enter here to reset your password</h3>

				<form action="AccountRecoveryServlet" method="post">
					<div class="form_settings">
						<p>
							<span>Email Address</span><input class="contact" type="text"
								name="email" value="" id="email" />
						</p>
						<p>
							<span>Recovery Token</span><input class="contact" type="text"
								name="token" value="" id="token" />
						</p>

						<p>
							<span>New Password</span><input class="contact" type="password"
								name="password" id="password" value="" />
						</p>
						<p>
							<span>Confirm Password</span><input class="contact"
								type="password" name="password" id="confirmPassword" value="" />
						</p>



						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="set password"
								onclick='return validatePassword()' />
						</p>
					</div>
				</form>






			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

	<script>
		function validatePassword() {

			let email = document.getElementById('email').value;
			let token = document.getElementById('token').value;
			let password = document.getElementById('password').value;
			let confirmPassword = document.getElementById('confirmPassword').value;

			if (!email || !token || !password || !confirmPassword) {

				alert('Please fill in the blank field!');
				return false;
			}

			if (password === confirmPassword) {

				return true;

			} else {
				alert('Password does not match');
				
				return false;

			}

		}
	</script>
</body>
</html>