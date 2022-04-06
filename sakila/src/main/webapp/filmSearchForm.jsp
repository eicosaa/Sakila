<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
	CategoryDao categoryDao = new CategoryDao();
	List<Category> categorylist = categoryDao.selectCategoryList();
	FilmDao filmDao = new FilmDao();
	List<Double> priceList = filmDao.selectFilmPriceList();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>filmSearchForm</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
		<h1>
			<div class="container p-3 my-3 bg-secondary text-white text-center">필름 리스트 뷰 검색</div>
		</h1>
		
	<form action = "<%= request.getContextPath() %>/filmSearchAction.jsp" method = "post">
		<table class = "table table-bordered text-center table-hover">
			<tr>
				<td>카테고리</td>
				<td>
					<!-- category 테이블에서 select -->
					<select name = "category" class = "form-control">
						<option value = "">카테고리 선택</option>
						<%
							for(Category c : categorylist) {
						%>
								<option value = "<%= c.getName() %>"><%= c.getName() %></option>
						<%
							}
						%>
					</select>
				</td>
			</tr>
			<tr>
				<!-- rating은 고정값이기 때문에 직접 입력 -->
				<td>등급</td>
				<td>
					<select name = "rating" class = "form-control">
						<option value = "">등급 선택</option>
						<option value = "G">G</option>
						<option value = "PG">PG</option>
						<option value = "PG-13">PG-13</option>
						<option value = "R">R</option>
						<option value = "NC-17">NC-17</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>대여료</td>
				<td>
					<div><input type = "radio" name = "price" value = "" checked = "checked" class = "form-check-input">선택 안함</div>
					<%
						for(Double p : priceList) {
					%>
							<div><input type = "radio" name = "price" value = "<%= p %>" class = "form-check-input"><%= p %></div>
					<%
						}
					%>
				</td>
			</tr>
			<tr>
				<td>영화 시간</td>
				<td>
					<div><input type = "radio" name = "length" value = "" checked = "checked" class = "form-check-input">선택 안함</div>
					<div><input type = "radio" name = "length" value = "0" class = "form-check-input">1시간 미만</div><!-- length < 60 -->
					<div><input type = "radio" name = "length" value = "1" class = "form-check-input">1시간 이상</div><!-- length >= 60 -->
				</td>
			</tr>
			<tr>
				<td>제목 검색</td>
				<td>
					<input type = "text" name = "title" class = "form-control">
				</td>
			</tr>
			<tr>
				<td>배우 검색</td>
				<td>
					<input type="text" name="actors" class = "form-control">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit" class = "btn btn-outline-secondary">검색</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>