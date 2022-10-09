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
			<h3 class="text-center">Transactions</h3>
			<hr>
			<div class="container text-left">

				<a href="report.jsp" class="btn btn-primary">Download</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>From Account</th>
						<th>To Account</th>
						<th>Amount</th>
						<th>Date</th>
						<th>Type</th>						
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="transaction" items="${listTransaction}">

						<tr>
							<td><c:out value="${transaction.id}" /></td>
							<td><c:out value="${transaction.from}" /></td>
							<td><c:out value="${transaction.to}" /></td>
							<td><c:out value="${transaction.amount}" /></td>	
							<td><c:out value="${transaction.date}" /></td>
							<td><c:out value="${transaction.type}" /></td>						
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>