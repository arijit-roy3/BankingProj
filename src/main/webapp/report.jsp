<%
	if(session.getAttribute("admin")==null){
		response.sendRedirect("adminLogin.jsp");
	}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@ page import="com.perfios.pojo.Transactions" %>
<html>
<head>
<title>EazyBank</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
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
				<tr>
					<%					
						response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition","inline; filename=transactions.xls");
						List<Transactions> transactions=(List<Transactions>)session.getAttribute("transactions");
						System.out.println(transactions);
						for(Transactions transaction:transactions){
							%>
							<td><%= transaction.getId() %></td>
							<td><%= transaction.getFrom() %></td>
							<td><%= transaction.getTo() %></td>	
							<td><%= transaction.getAmount() %></td>
							<td><%= transaction.getDate() %></td>
							<td><%= transaction.getType() %></td>
							</tr>
						<%
						}
						%>
					
					
					
					<!-- } -->
				</tbody>

			</table>
</body>
</html>