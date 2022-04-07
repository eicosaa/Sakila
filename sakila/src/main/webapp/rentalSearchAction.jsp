<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // 현재 날짜 yyyy-MM-dd 형식으로 불러오기
	Date now = new Date();

	int storeId = -1; // -storeId가 입력되지 않았을 때
	String customerName = request.getParameter("customerName");
	String beginDate = request.getParameter("beginDate");
	String endDate = request.getParameter("endDate");
	// String endDate = format.format(now); // 디폴트 값을 현재 날짜로 설정
	if(!request.getParameter("storeId").equals("")) { // -storeId가 입력되었을 때
		storeId = Integer.parseInt(request.getParameter("storeId"));
	}
	
	// -디버깅 코드
	System.out.println("[rentalSearchAction.jsp] storeId : " + storeId);
	System.out.println("[rentalSearchAction.jsp] customerName : " + customerName);
	System.out.println("[rentalSearchAction.jsp] beginDate : " + beginDate);
	System.out.println("[rentalSearchAction.jsp] endDate : " + endDate);
	System.out.println("------------------------------------------------------------");
	
	
	
	// -페이징
	int currentPage = 1; // -현재 페이지 변수 생성 및 초기화 
	if(request.getParameter("currentPage") != null) { // 이전 다음 링크를 통해서 왔다면 아래 블록 실행
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int rowPerPage = 10; // -페이지 행의 수 (한 페이지에 나타낼 정보의 수)  
	int beginRow = (currentPage - 1) * rowPerPage; // -한 페이지를 시작할 행의 수
	// 시작하는 페이지의 숫자 현재 페이지가 변경되면 beginRow가 변경됨
	
	RentalDao rentalDao = new RentalDao();
	List<Map<String, Object>> list = rentalDao.selectRentalSearchList(storeId, customerName, beginDate, endDate, beginRow, rowPerPage);
	
	int totalRow = rentalDao.rentalSearchTotalRow(storeId, customerName, beginDate, endDate); // -전체 행의 개수 
	int lastPage = (int)(Math.ceil((double)totalRow/(double)rowPerPage)); // -마지막 페이지 수
	
	// -디버깅 코드
	System.out.println("[rentalSearchAction.jsp] currentPage : " + currentPage);
	System.out.println("[rentalSearchAction.jsp] rowPerPage : " + rowPerPage);
	System.out.println("[rentalSearchAction.jsp] beginRow : " + beginRow);
	System.out.println("[rentalSearchAction.jsp] totalRow : " + totalRow);
	System.out.println("[rentalSearchAction.jsp] lastPage : " + lastPage);
	System.out.println("************************************************************");
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
	<!-- 페이지 버튼 -->
	<div>
		<%
			if(currentPage > 1) { 
		%>
				<a href="<%= request.getContextPath() %>/rentalSearchAction.jsp?currentPage=<%= currentPage - 1 %>&customerName=<%= customerName %>&storeId=<%= storeId %>&beginDate=<%= beginDate %>&endDate=<%= endDate %>" class="btn btn-outline-secondary">이전</a>
		<%		
			} 
		
			if(currentPage < lastPage) {
		%>
				<a href="<%= request.getContextPath() %>/rentalSearchAction.jsp?currentPage=<%= currentPage + 1 %>&customerName=<%= customerName %>&storeId=<%= storeId %>&beginDate=<%= beginDate %>&endDate=<%= endDate %>" class="btn btn btn-outline-secondary">다음</a>
		<%		
			}
		%>
	</div>
</div>
</body>
</html>