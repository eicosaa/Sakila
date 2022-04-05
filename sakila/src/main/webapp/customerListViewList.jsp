<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
	int currentPage = 1; // -현재 페이지 변수 생성 및 초기화 
	
	System.out.println("[customerListViewList] currentPage : " + currentPage);		
	if(request.getParameter("currentPage") != null) { // 이전 다음 링크를 통해서 왔다면 아래 블록 실행
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}	
	
	int rowPerPage = 10; // -페이지 행의 수 (한 페이지에 나타낼 정보의 수) 
	int beginRow = (currentPage - 1) * rowPerPage; // -한 페이지를 시작할 행의 수
	
	// -Dao, vo 메서드 객체 생성
	CustomerListViewDao customerListViewDao = new CustomerListViewDao();
	CustomerListView customerListView = new CustomerListView();
	
	List<CustomerListView> list = customerListViewDao.selectCustomerListViewByPage(beginRow, rowPerPage);

	int totalRow = customerListViewDao.selectCustomerListViewTotalRow(); // -전체 행의 개수
	int lastPage = 0; // -마지막 페이지 수
	
	if (totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage;
	} else {  
		lastPage = (totalRow / rowPerPage) + 1;
	}
	
	System.out.println("[customerListViewList] rowPerPage : " + rowPerPage);	
	System.out.println("[customerListViewList] beginRow : " + beginRow);
	System.out.println("[customerListViewList] lastPage : " + lastPage);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CustomerListView List</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
		<h1>
			<div class="container p-3 my-3 bg-secondary text-white text-center">CustomerListView List</div>
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
				<th>Notes</th>
				<th>SID</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(CustomerListView c : list) {
			%>
					<tr>
						<td><%= c.getId() %></td>
						<td><%= c.getName() %></td>
						<td><%= c.getAddress() %></td>
						<td><%= c.getZipCode() %></td>
						<td><%= c.getPhone() %></td>
						<td><%= c.getCity() %></td>
						<td><%= c.getCountry() %></td>
						<td><%= c.getNotes() %></td> 
						<td><%= c.getSid() %></td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<!-- 페이지 버튼 -->
	<div>
		<%
			if(currentPage > 1) { 
		%>
				<a href="<%= request.getContextPath() %>/customerListViewList.jsp?currentPage=<%= currentPage - 1 %>" class = "btn btn-outline-secondary">이전</a>
		<%		
			} 
		
			if(currentPage < lastPage) {
		%>
				<a href="<%= request.getContextPath() %>/customerListViewList.jsp?currentPage=<%= currentPage + 1 %>" class = "btn btn-outline-secondary">다음</a>
		<%		
			}
		%>
	</div>
</div>
</body>
</html>