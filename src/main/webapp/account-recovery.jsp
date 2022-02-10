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
							<span>Email</span><input class="contact" type="text" name="email"
								value="" />
						</p>
						<p>
							<span>Token</span><input class="contact" type="text" name="token"
								value="" />
						</p>

						<p>
							<span>New Password</span><input class="contact" type="password"
								name="password" value="" />
						</p>



						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="set password" />
						</p>
					</div>
				</form>






			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>