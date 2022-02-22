<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<h1>Contact Us</h1>
				<p>If you need to contact us for non urgent matter, please fill
					in the form below:</p>
				<form action="HomeServlet" method="post">
					<div class="form_settings">
						<p>
							<span>Name</span><input class="contact" type="text" name="name"
								value="" />
						</p>
						<p>
							<span>Email Address</span><input class="contact" type="text"
								name="email" value="" />
						</p>
						<p>
							<span>Message</span>
							<textarea class="contact textarea" rows="16" cols="50"
								name="inquiry"></textarea>
						</p>
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="send" onclick="alertFunc()" />
						</p>
					</div>
				</form>
				<p>
					<br /> <br />We aim to response your inquiry within 3 working
					days.<br /> If you have any urgent request, please call us at <strong>0211-222-333</strong>.
				</p>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

	<script>
		function alertFunc() {
			alert('Thank you for sending the inquiry!');
		}
	</script>
</body>
</html>