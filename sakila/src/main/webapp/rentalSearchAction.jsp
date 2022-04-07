<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%
	int storeId = -1; // -storeId가 입력되지 않았을 때
	String customerName = request.getParameter("customerName");
	String beginDate = request.getParameter("beginDate");
	String endDate = request.getParameter("endDate");
	if(!request.getParameter("storeId").equals("")) { // -storeId가 입력되었을 때
		storeId = Integer.parseInt(request.getParameter("storeId"));
	}
	
	RentalDao rentalDao = new RentalDao();
	List<Map<String, Object>> list = rentalDao.selectRentalSearchList(storeId, customerName, beginDate, endDate);
	
	// -디버깅 코드
	System.out.println("[rentalSearchAction.jsp] storeId : " + storeId);
	System.out.println("[rentalSearchAction.jsp] customerName : " + customerName);
	System.out.println("[rentalSearchAction.jsp] beginDate : " + beginDate);
	System.out.println("[rentalSearchAction.jsp] endDate : " + endDate);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>rentalSearchAction</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class = "container">	
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
	<h1>
		<div class="container p-3 my-3 bg-secondary text-white text-center">대여 검색 결과 리스트</div>
	</h1>
	
	
	<table class="table table-bordered text-center table-hover">
		<thead>
			<tr>
				<th>rentalId</th>
				<th>customerId</th>
				<th>customerName</th>
				<th>inventoryId</th>
				<th>filmId</th>
				<th>title</th>
				<th>rentalDate</th>
				<th>returnDate</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(Map m : list) {
			%>
					<tr>
						<td><%= m.get("rentalId") %></td>
						<td><%= m.get("customerId") %></td>
						<td><%= m.get("customerName") %></td>
						<td><%= m.get("inventoryId") %></td>
						<td><%= m.get("filmId") %></td>
						<td><%= m.get("title") %></td>
						<td><%= m.get("rentalDate") %></td>
						<td><%= m.get("returnDate") %></td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>
</body>
</html>