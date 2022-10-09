<%
	if(session.getAttribute("admin")==null){
		response.sendRedirect("adminLogin.jsp");
	}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>EazyBank</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: black">
			<div>
				<a href="adminIndex.jsp" class="navbar-brand"> Dashboard </a>
			</div>

			
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Users</h3>
			<hr>
			
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Balance</th>
						<th>Email</th>
						<th>Pan</th>
						<th>Password</th>
						<th>Mobile</th>
						<th>Address</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.uname}" /></td>
							<td><c:out value="${user.balance}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.pan}" /></td>
							<td><c:out value="${user.pwd}" /></td>
							<td><c:out value="${user.mobile}" /></td>
							<td><c:out value="${user.address}" /></td>
							<td><a href="/BankingProj/adminPwdChange?id=<c:out value='${user.id}' />">Change Password</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="/BankingProj/adminDelete?id=<c:out value='${user.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>