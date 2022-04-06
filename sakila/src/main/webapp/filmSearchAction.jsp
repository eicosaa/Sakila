<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%
	int currentPage = 1; // -현재 페이지 변수 생성 및 초기화 
	
	System.out.println("[filmSearchAction.jsp] currentPage : " + currentPage);		
	if(request.getParameter("currentPage") != null) { // 이전 다음 링크를 통해서 왔다면 아래 블록 실행
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}	
	
	int rowPerPage = 10; // -페이지 행의 수 (한 페이지에 나타낼 정보의 수)  
	int beginRow = (currentPage - 1) * rowPerPage; // -한 페이지를 시작할 행의 수
	// 시작하는 페이지의 숫자 현재페이지가 변경되면 beginRow가 변경됨
	
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
	
	FilmDao filmDao = new FilmDao();
	
	int totalRow = filmDao.selectFilmSearchTotalRow(category, rating, price, length, title, actors); // -전체 행의 개수
	int lastPage = 0; // -마지막 페이지 수
	
	if (totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage;
	} else {  
		lastPage = (totalRow / rowPerPage) + 1;
	}
	
	List<FilmListView> list = filmDao.selectFilmListSearch(beginRow, rowPerPage, category, rating, price, length, title, actors);
	System.out.println(list.size());
	
	// -디버깅 코드
	System.out.println("[filmSearchAction.jsp] currentPage : " + currentPage);
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
	
		<tr>
			<td>FID</td>
			<td>Title</td>
			<td>Description</td>
			<td>Category</td>
			<td>Price</td>
			<td>Length</td>
			<td>Rating</td>
			<td>Actors</td>
		</tr>
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
	<!-- 페이지 버튼 -->
	<div>
		<%
			if(currentPage > 1) { 
		%>
				<a href="<%= request.getContextPath() %>/filmSearchAction.jsp?currentPage=<%= currentPage - 1 %>&category=<%= category %>&rating=<%= rating %>&price=<%= price %>&length=<%= length %>&title=<%= title %>&actors=<%= actors %>" class="btn btn-outline-secondary">이전</a>
		<%		
			} 
		
			if(currentPage < lastPage) {
		%>
				<a href="<%= request.getContextPath() %>/filmSearchAction.jsp?currentPage=<%= currentPage + 1 %>&category=<%= category %>&rating=<%= rating %>&price=<%= price %>&length=<%= length %>&title=<%= title %>&actors=<%= actors %>" class="btn btn btn-outline-secondary">다음</a>
		<%		
			}
		%>
	</div>
</body>
</html>