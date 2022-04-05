<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>RewardsReport</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class = "container">	
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
	<h1>
		<div class="container p-3 my-3 bg-secondary text-white text-center">RewardsReport (procedure)</div>
	</h1>
	
	<form method = "post" action = "<%= request.getContextPath() %>/rewardsReportAction.jsp">
		<table class = "table table-active">
			<tr>
				<td>최소 구매 횟수</td>
				<td><input type = "number" name = "monthlyPurchases" placeholder = "only number" class = "form-control"></td>
			</tr>
			<tr>
				<td>최소 달러 금액</td>
				<td><input type = "number" name = "dollarAmountPurchased" placeholder = "only number" class = "form-control"></td>
			</tr>
		</table>
		<button type = "submit" class = "btn btn-outline-dark">입력</button>
	</form>
</div>
</body>
</html>