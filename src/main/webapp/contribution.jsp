<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contribution</title>
<link rel="stylesheet" type="text/css" href="style.css" title="style" />


</head>
<body>
<jsp:include page="header.jsp" />
	<div id="main">
		<div id="site_content">
			<div id="content">
			
<h1>Your donation is greatly appreciated!</h1>
			
<h2>Select a payment type:</h2>

	<div id="smart-button-container">
		<div style="text-align: center;">
			<div id="paypal-button-container"></div>
		</div>
	</div>
	<script
		src="https://www.paypal.com/sdk/js?client-id=ARnhNKZ7-8euw3i01bAF_pR5uI6qZCJR-cWfbaluo1IeIm4Mpoi1cOb4OvZICCLycn-UvbWrMbVg04xA&currency=USD"
		data-sdk-integration-source="button-factory"></script>
	<script>
		function initPayPalButton() {
			paypal.Buttons({style : {
									shape : 'pill',
									color : 'white',
									layout : 'vertical',
									label : 'paypal',},

			createOrder : function(data, actions) {
			return actions.order.create({
			purchase_units : [ {
				"amount" : {
				"currency_code" : "USD",
				"value" : 0.01
								}
								} ]
							});
					},

			onApprove : function(data, actions) {
			return actions.order
					.capture()
					.then(
					function(orderData) {
					// Full available details
			console
				.log(
					'Capture result',
					orderData,
			JSON
				.stringify(
					orderData,
					null,
					2));
				// Show a success message within this page, e.g.
				const element = document
				.getElementById('paypal-button-container');
				element.innerHTML = '';
				element.innerHTML = '<h3>Thank you for your payment!</h3>';
// Or go to another URL:  actions.redirect('thank_you.html');
				});
					},
					onError : function(err) {
							console.log(err);
							}
						}).render('#paypal-button-container');
		}
		initPayPalButton();
	</script>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>