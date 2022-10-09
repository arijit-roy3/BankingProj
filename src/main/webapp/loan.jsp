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
<title>Loan</title>

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
						<h2 class="form-title">Apply for Loan</h2>
					
						<form method="post" action="loan" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Your Name" required="required"/>
							</div>
							<div class="form-group">
								<label for="cibilScore"><i class="zmdi zmdi-balance"></i></label> <input
									type="number" name="cibilScore" id="cibilScore" placeholder="Enter Cibil Score" required="required"/>
							</div>
							<div class="form-group">
								<label for="age"><i class="zmdi zmdi-email"></i></label> <input
									type="number" name="age" id="age" placeholder="Age" required="required"/>
							</div>
							<div class="form-group">
								<label for="salary"><i class="zmdi zmdi-account-box"></i></label> <input
									type="number" name="salary" id="salary" placeholder="Salary" required="required"/>
							</div>
							<div class="form-group">
								<label for="amount"><i class="zmdi zmdi-lock"></i></label> <input
									type="number" name="amount" id="amount" placeholder="Amount" required="required"/>
							</div>
							<div class="form-group">
								<label for="designation"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="text" name="designation" id="designation"
									placeholder="Designation" required="required"/>
							</div>
							<div class="form-group">
								<label for="company"><i class="zmdi zmdi-account"></i></label>
								<input type="text" name="company" id="company"
									placeholder="Company Name" required="required"/>
							</div>
							<div class="form-group">
								<label for="tenure"><i class="zmdi zmdi-home"></i></label>
								<input type="number" name="tenure" id="tenure"
									placeholder="Enter Tenure in Months(Min 6 months)" required="required"/>
							</div>
							
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sing up image">
						</figure>
						<a href="index.jsp" class="signup-image-link">Back To Dashboard</a>
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
	if(status=='success'){
		swal("Congrats!!! Registered Successfully");		
	}
	else if(status=='failed'){
		swal("Sorry","Internal Error","error");
	}
	else if(status=='invalidTenure'){
		swal("Sorry","Minimum Tenure 6 Months","error");
	}
	
	
</script>

</body>
</html>