<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%
	StaffDao staffDao = new StaffDao();
	List<Map<String, Object>> list = staffDao.selectStaffList();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Staff List</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
		<h1>
			<div class="container p-3 my-3 bg-secondary text-white text-center">Staff List</div>
		</h1>
</div>
	<div>
	<table class="table table-bordered text-center table-hover container-sm border">
		<thead>
			<tr>
				<th>storeId</th>
				<th>staffName</th>
				<th>addressId</th>
				<th>staffAddress</th>
				<th>email</th>
				<th>storeId</th>
				<th>active</th>
				<th>username</th>
				<th>password</th>
				<th>lastUpdate</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(Map m : list) {
			%>
					<tr>
						<td><%= m.get("staffId") %></td>
						<td><%= m.get("staffName") %></td>
						<td><%= m.get("addressId") %></td>
						<td><%= m.get("staffAddress") %></td>
						<td><%= m.get("email") %></td>
						<td><%= m.get("storeId") %></td>
						<td><%= m.get("active") %></td>
						<td><%= m.get("username") %></td>
						<td><%= m.get("password") %></td>
						<td><%= m.get("lastUpdate") %></td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
	</div>

</body>
</html>