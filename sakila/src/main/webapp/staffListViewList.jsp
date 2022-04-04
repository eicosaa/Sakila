<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%	
	// -Dao 메서드 객체 생성
	StaffListViewDao staffListViewDao = new StaffListViewDao();
	List<StaffListView> list = staffListViewDao.selectStaffListView();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>StaffListView List</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
		<h1>
			<div class="container p-3 my-3 bg-secondary text-white text-center">StaffListView List</div>
		</h1>
	<table class="table table-bordered text-center table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Address</th>
				<th>Zip code</th>
				<th>Phone</th>
				<th>City</th>
				<th>Country</th>
				<th>SID</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(StaffListView s : list) {
			%>
					<tr>
						<td><%= s.getId() %></td>
						<td><%= s.getName() %></td>
						<td><%= s.getAddress() %></td>
						<td><%= s.getZipCode() %></td>
						<td><%= s.getPhone() %></td>
						<td><%= s.getCity() %></td>
						<td><%= s.getCountry() %></td>
						<td><%= s.getSid() %></td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>
</body>
</html>