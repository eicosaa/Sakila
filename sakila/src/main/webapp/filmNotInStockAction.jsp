<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%
	int filmId = 0;
	int storeId = 0;
	int count = 0;
	
	// -요청값	
	if (request.getParameter("filmId") != null) {
		filmId= Integer.parseInt(request.getParameter("filmId"));
	}
	if (request.getParameter("storeId") != null) {
		storeId= Integer.parseInt(request.getParameter("storeId"));
	}
	
	FilmNotInStockDao filmNotInStockDao = new FilmNotInStockDao();
	Map<String, Object> map = filmNotInStockDao.filmNotInStockCall(filmId, storeId);
	List<Integer> list = (List<Integer>)map.get("list");
	count = (Integer)map.get("count");
	
	// -디버깅
	System.out.println("[filmNotInStockAction.jsp] filmId : " + filmId);
	System.out.println("[filmNotInStockAction.jsp] storeId : " + storeId);
	System.out.println("[filmNotInStockAction.jsp] list : " + list);
	System.out.println("[filmNotInStockAction.jsp] count : " + count);
	
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>filmNotInStockAction</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class = "container">	
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
	<h1>
		<div class="container p-3 my-3 bg-secondary text-white text-center">filmNotInStock (procedure)</div>
	</h1>
	
	<%
		if (filmId != 0 && storeId != 0) {
	%>
			<table class = "table table-bordered text-center table-hover">
				<tr>
					<td>영화 번호</td>
					<td><%= filmId %></td>
				</tr>
				<tr>
					<td>가게 번호</td>
					<td><%= storeId %></td>
				</tr>
				<tr>
					<td>없는 재고 개수</td>
					<td><%= count %></td>
				</tr>
				<tr>
					<td>없는 재고 번호</td>
					<td>  <% 
							for(int id : list) {
						  %>
						   		<%= id %>
						  <%
							}
						  %> 
					</td>
				</tr>
			</table>
	<%
		} 
	%>
</div>
</body>
</html>