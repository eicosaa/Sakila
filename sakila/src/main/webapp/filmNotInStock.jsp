<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>filmNotInStock</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class = "container">	
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
	<h1>
		<div class="container p-3 my-3 bg-secondary text-white text-center">filmNotInStock (procedure)</div>
	</h1>
	
	<form method = "post" action = "<%= request.getContextPath() %>/filmNotInStockAction.jsp">
		<table class = "table table-active">
			<tr>
				<td>조회 할 영화 번호</td>
				<td><input type = "number" name = "filmId" placeholder = "only number" class = "form-control"></td>
			</tr>
			<tr>
				<td>조회 할 가게 번호</td>
				<td><input type = "number" name = "storeId" placeholder = "only number" class = "form-control"></td>
			</tr>
		</table>
		<button type = "submit" class = "btn btn-outline-dark">입력</button>
	</form>
</div>
</body>
</html>