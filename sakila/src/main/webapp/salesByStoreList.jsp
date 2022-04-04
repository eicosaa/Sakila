<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%	
	// -Dao, vo 메서드 객체 생성
	SalesByStoreDao salesByStoreDao = new SalesByStoreDao();
	SalesByStore salesByStore = new SalesByStore();
	
	List<SalesByStore> list = salesByStoreDao.selectSalesByStore();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>SalesByStore List</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
		<h1>
			<div class="container p-3 my-3 bg-secondary text-white text-center">SalesByStore List</div>
		</h1>
	<table class="table table-bordered text-center table-hover">
		<thead>
			<tr>
				<th>Store</th>
				<th>Manager</th>
				<th>Total Sales</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(SalesByStore s : list) {
			%>
					<tr>
						<td><%= s.getStore() %></td>
						<td><%= s.getManager() %></td>
						<td><%= s.getTotalSales() %></td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>
</body>
</html>