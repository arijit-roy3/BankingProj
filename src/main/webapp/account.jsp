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
<title>Account</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>	

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
						<h2 class="form-title">Hi ,<%=session.getAttribute("name") %></h2>
						<h3 class="form-title">Edit Account</h3>
					
						<form method="post" action="account" class="register-form"
							id="register-form">
							
							
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email"/>
							</div>
							<div class="form-group">
								<label for="pan"><i class="zmdi zmdi-account-box"></i></label> <input
									type="text" name="pan" id="pan" placeholder="Your PAN"/>
							</div>
							<div class="form-group">
								<label for="old_pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="old_pass" id="old_pass" placeholder="Old Password (Required)" required="required"/>
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pass" id="pass" placeholder="New Password"/>
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="password" name="re_pass" id="re_pass"
									placeholder="Repeat your new password"/>
							</div>
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-account"></i></label>
								<input type="text" name="contact" id="contact"
									placeholder="Pls enter 10 digit Contact no"/>
							</div>
							<div class="form-group">
								<label for="address"><i class="zmdi zmdi-home"></i></label>
								<input type="text" name="address" id="adress"
									placeholder="Your Address"/>
							</div>
							
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Update" />
							</div>
								<button class="btn btn-danger">
								<a  href="delete.jsp">Delete</a>	
								</button>
																
							
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sing up image">
						</figure>
						<a href="index.jsp" class="signup-image-link">Back to Dashboard</a>
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
		swal("Congrats!!! Updated Successfully");		
	}
	else if(status=='failed'){
		swal("Sorry","Internal Error","error");
	}
	else if(status=='passwordNotMatch'){
		swal("Sorry","Invalid Password","error");
	}
	else if(status=='upwdNotMatch'){
		swal("Sorry","Passwords did not match","error");
	}
	else if(status=='retypePassword'){
		swal("Sorry","Retype Password","error");
	}
	else if(status=='upwdNotMatch'){
		swal("Sorry","Passwords don't match","error");
	}
	
</script>

</body>
</html>