<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "java.util.*" %>
<%
	StoreDao storeDao = new StoreDao();
	List<Store> storeIdList = storeDao.selectStoreIdList();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>rentalSearchForm</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class = "container">	
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
	<h1>
		<div class="container p-3 my-3 bg-secondary text-white text-center">대여 상세 검색</div>
	</h1>
	
	<form action="<%= request.getContextPath() %>/rentalSearchAction.jsp" method = "post">
		<table class = "table table-bordered text-center table-hover">
			<!-- 가게 번호 검색 -->
			<tr>
				<td>store ID</td>
				<td>
					<div><input type="radio" name="storeId" class = "form-check-input" value="" checked="checked">선택안함</div>
					<%
						for(Store i : storeIdList) {
					%>
							<div><input type = "radio" name = "storeId" class = "form-check-input" value = "<%= i.getStoreId() %>"> <%= i.getStoreId() %>번 가게</div>
					<%
						}
					%>
				</td>
			</tr>
			<!-- 고객 이름 검색 -->
			<tr>
				<td>customer Name</td>
				<td>
					<input type = "text" name = "customerName"  class = "form-control">
				</td>
			</tr>
			<!-- 대여 일자 -->
			<tr>
				<td>rent Date</td>
				<td>
					begin date <input type = "date" name = "beginDate"  class = "form-control">
					end date <input type = "date" name = "endDate"  class = "form-control">
				</td>
			</tr>
			<tr>
				<td colspan = "2"><button type = "submit" class = "btn btn-outline-dark">입력</button></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>