<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
	CategoryDao categoryDao = new CategoryDao();
	List<Category> list = categoryDao.selectCategoryList();
	FilmDao filmDao = new FilmDao();
	List<Double> priceList = filmDao.selectFilmPriceList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmSearchForm</title>
</head>
<body>
	<h1>필름 리스트 뷰 검색</h1>
	<form action = "<%= request.getContextPath() %>/filmSearchAction.jsp" method = "post">
		<table border = "1">
			<tr>
				<td>카테고리</td>
				<td>
					<!-- category 테이블에서 select -->
					<select name = "categoryName">
						<option value = "">카테고리 선택</option>
						<%
							for(Category c : list) {
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
					<select name = "rating">
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
					<div><input type = "radio" name = "price" value = "" checked = "checked">선택 안함</div>
					<%
						for(Double p : priceList) {
					%>
							<div><input type = "radio" name = "price" value = "<%= p %>"><%= p %></div>
					<%
						}
					%>
				</td>
			</tr>
			<tr>
				<td>영화시간</td>
				<td>
				<div>
					<div><input type = "radio" name = "length" value = "" checked = "checked">선택 안함</div>
					<div><input type = "radio" name = "length" value = "0">0 ~ 1시간</div><!-- length 0 ~ 60 -->
					<div><input type = "radio" name = "length" value = "1">1 ~ 2시간</div><!-- length 61 ~ 120 -->
					<div><input type = "radio" name = "length" value = "2">2시간 초과</div><!-- length 121 ~ -->
				</td>
			</tr>
			<tr>
				<td>제목 검색</td>
				<td>
					<input type = "text" name = "title">
				</td>
			</tr>
			<tr>
				<td>배우 검색</td>
				<td>
					<input type="text" name="actor">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">검색</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>