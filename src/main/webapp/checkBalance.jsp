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
<title>Check Balance</title>

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
						

					<div class="signin-form">
						<h2 class="form-title">Current Balance</h2>
						<form method="post" action="login" class="register-form"
							id="login-form">
							<div class="form-group">
								<h2><%=session.getAttribute("currentBalance") %></h2>
							</div>
							
							
							<a href="index.jsp" class="signup-image-link">Back to Dashboard</a>
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