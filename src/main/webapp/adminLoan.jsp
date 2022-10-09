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
		

		<div class="container">
			<h3 class="text-center">Pending Loans</h3>
			<hr>
			
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>CibilScore</th>
						<th>Age</th>
						<th>Salary</th>
						<th>Amount</th>
						<th>Designation</th>
						<th>Company</th>
						<th>Tenure</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="loan" items="${listLoan}">
						<c:if test = "${loan.status == 'pending'}">
       						 <tr>
							<td><c:out value="${loan.lid}" /></td>
							<td><c:out value="${loan.name}" /></td>
							<td><c:out value="${loan.cibilScore}" /></td>
							<td><c:out value="${loan.age}" /></td>
							<td><c:out value="${loan.salary}" /></td>
							<td><c:out value="${loan.amount}" /></td>
							<td><c:out value="${loan.designation}" /></td>
							<td><c:out value="${loan.company}" /></td>
							<td><c:out value="${loan.tenure}" /></td>
							<td><a href="/BankingProj/adminApproveLoan?id=<c:out value='${loan.lid}' />">Approve</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="/BankingProj/adminDeleteLoan?id=<c:out value='${loan.lid}' />">Reject</a></td>
								
							</tr>
						</c:if>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
	
	<br>
	<br>
	<div class="row">
		

		<div class="container">
			<h3 class="text-center">Approved Loans</h3>
			<hr>
			
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>CibilScore</th>
						<th>Age</th>
						<th>Salary</th>
						<th>Amount</th>
						<th>Designation</th>
						<th>Company</th>
						<th>Tenure</th>
									
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="loan" items="${listLoan}">

						<c:if test = "${loan.status == 'approved'}">
       						 <tr>
							<td><c:out value="${loan.lid}" /></td>
							<td><c:out value="${loan.name}" /></td>
							<td><c:out value="${loan.cibilScore}" /></td>
							<td><c:out value="${loan.age}" /></td>
							<td><c:out value="${loan.salary}" /></td>
							<td><c:out value="${loan.amount}" /></td>
							<td><c:out value="${loan.designation}" /></td>
							<td><c:out value="${loan.company}" /></td>
							<td><c:out value="${loan.tenure}" /></td>
							
								
							</tr>
						</c:if>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
	
</body>
</html>