<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Admin Login</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<link rel="stylesheet" href="css/style.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
	<div class="main">

		<!-- Sing in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
							<img src="images/signin-image.jpg" alt="sing up image">
						</figure>
						<a href="login.jsp" class="signup-image-link">Back to User Login</a>
					</div>

					<div class="signin-form">
						<h2 class="form-title">Admin Login</h2>
						<form method="post" action="adminLogin" class="register-form"
							id="login-form">
							<div class="form-group">
								<label for="adminId"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="number" name="adminId" id="username"
									placeholder="Your Admin Id." required="required"/>
							</div>
							<div class="form-group">
								<label for="adminPassword"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="password" id="password"
									placeholder="Password" required="required"/>
							</div>
							
							<div class="form-group form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />									
							</div>
						</form>
						
					</div>
				</div>
			</div>
		</section>

	</div>	
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">

<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status=='failed'){
		swal("Sorry","Wrong Username or Password","error");
	}
	
</script>
</body>
</html>