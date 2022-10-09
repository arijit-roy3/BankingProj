<%
	if(session.getAttribute("name")==null){
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Deposit</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">

	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Deposit</h2>
					
						<form method="post" action="deposit" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="amount"><i
									class="zmdi zmdi-balance"></i></label> <input
									type="number" name="amount" id="amount" placeholder="Enter amount to be deposited" required="required"/>
							</div>
							
							
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Deposit" />
							</div>
							
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sing up image">
							<a href="index.jsp" class="signup-image-link">Back to Dashboard</a>
						</figure>
						
					</div>
				</div>
			</div>
		</section>


	</div>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status=='successfulDeposit'){
		swal("Deposited Successfully");
	}
	
	else if(status=='internalError'){
		swal("Sorry","Please enter Email","error");
	}
	else if(status=='invalidAmount'){
		swal("Sorry","Please enter valid amount","error");
	}
	
	
</script>

</body>
</html>