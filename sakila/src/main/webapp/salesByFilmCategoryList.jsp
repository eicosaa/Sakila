<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%	
	// -Dao, vo 메서드 객체 생성
	SalesByFilmCategoryDao sbfcDao = new SalesByFilmCategoryDao();
	SalesByFilmCategory sbfc = new SalesByFilmCategory();
	
	List<SalesByFilmCategory> list = sbfcDao.selectSalesByFilmCategory();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>SalesByFilmCategory List</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
		<h1>
			<div class="container p-3 my-3 bg-secondary text-white text-center">SalesByFilmCategory List</div>
		</h1>
	<table class="table table-bordered text-center table-hover">
		<thead>
			<tr>
				<th>Category</th>
				<th>Total Sales</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(SalesByFilmCategory s : list) {
			%>
					<tr>
						<td><%= s.getCategory() %></td>
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