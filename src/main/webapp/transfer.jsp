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
<title>Transfer</title>

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
						<h2 class="form-title">Transfer Funds</h2>
					
						<form method="post" action="transfer" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="accountNo"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="number" name="accountNo" id="accountNo" placeholder="Account Number" required="required"/>
							</div>
							
							<div class="form-group">
								<label for="amount"><i class="zmdi zmdi-balance"></i></label> <input
									type="number" name="amount" id="amount" placeholder="Amount" required="required"/>
							</div>						
							<select name="type" id="type">
  								<option value="upi">UPI</option>
  								<option value="neft">NEFT</option>
 								 <option value="cash">Cash</option>
  								<option value="cheque">Cheque</option>
							</select>
							
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Transfer" />
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
	if(status=='successfulTransaction'){
		swal("Transaction done successfully");
	}
	else if(status=='notEnoughBalance'){
		swal("Sorry","Not Enough Balance","error");
	}
	else if(status=='accountNotPresent'){
		swal("Sorry","Account Not Present","error");
	}
	
	else if(status=='sameAccount'){
		swal("Sorry","Can't transfer to same account","error");
	}
	
</script>

</body>
</html>