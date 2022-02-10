<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account BO</title>
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

	String alertMsg = (String) session.getAttribute("alertMsg");
	String alertMsgReg = (String) session.getAttribute("alertMsgReg");
	%>


	<jsp:include page="headerBO.jsp" />
	<div id="main">
		<div id="site_content">
			<div id="content">

				<h1>Sign In</h1>
				<c:if test="${alertMsg!=null}">
					<h4>${alertMsg}</h4>
					
				</c:if>
				<p>Please enter your account details here:</p>
				<form action="LoginServletBO" method="post">
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


			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>