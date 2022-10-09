<%
	if(session.getAttribute("admin")==null){
		response.sendRedirect("adminLogin.jsp");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Password Change</title>

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
						<a href="adminIndex.jsp" class="signup-image-link">Back to DashBoard</a>
							
					</div>

					<div class="signin-form">
						<h2 class="form-title">Change Password</h2>
						<form method="post" action="changePassword" class="register-form"
							id="login-form">
							<div class="form-group">
								<label for="password"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="password" id="password"
									placeholder="Updated Password" required="required"/>
							</div>
							
							
							<div class="form-group form-button">
								<input type="submit" name="Change Password" id="signin"
									class="form-submit" value="Change Password" />									
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


</body>
</html>