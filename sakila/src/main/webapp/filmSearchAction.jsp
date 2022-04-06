<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%
	String category = request.getParameter("category");
	String rating = request.getParameter("rating");
	double price = -1; // price데이터가 입력되지 않았을 때 -0이라는 값이 존재할 수 있으므로 -1로 초기화
	if(!request.getParameter("price").equals("")) {
		price = Double.parseDouble(request.getParameter("price"));
	}
	int length = -1; // length 데이터가 입력되지 않았을때 -0이라는 값이 존재할 수 있으므로 -1로 초기화
	if(!request.getParameter("length").equals("")) {
		length = Integer.parseInt(request.getParameter("length"));
	}
	String title = request.getParameter("title");
	String actors = request.getParameter("actors");
	
	int beginRow = 1;
	int rowPerPage = 10;
	
	FilmDao filmDao = new FilmDao();
	List<FilmListView> list = filmDao.selectFilmListSearch(beginRow ,rowPerPage ,category, rating, price, length, title, actors);
	System.out.println(list.size());
	
	// -디버깅 코드
	System.out.println("[filmSearchAction.jsp] beginRow : " + beginRow);
	System.out.println("[filmSearchAction.jsp] rowPerPage : " + rowPerPage);
	System.out.println("[filmSearchAction.jsp] category : " + category);
	System.out.println("[filmSearchAction.jsp] rating : " + rating);
	System.out.println("[filmSearchAction.jsp] price : " + price);
	System.out.println("[filmSearchAction.jsp] length : " + length);
	System.out.println("[filmSearchAction.jsp] title : " + title);
	System.out.println("[filmSearchAction.jsp] actors : " + actors);
	
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>filmSerachAction</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
		<h1>
			<div class="container p-3 my-3 bg-secondary text-white text-center">필름 리스트 뷰 검색 결과</div>
		</h1>
		
	<table class = "table table-bordered text-center table-hover">
		<%
			for(FilmListView f : list) {
		%>
				<tr>
					<td><%= f.getFid() %></td>
					<td><%= f.getTitle() %></td>
					<td><%= f.getDescription() %></td>
					<td><%= f.getCategory() %></td>
					<td><%= f.getPrice() %></td>
					<td><%= f.getLength() %></td>
					<td><%= f.getRating() %></td>
					<td><%= f.getActors() %></td>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>