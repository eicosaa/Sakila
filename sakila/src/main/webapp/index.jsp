<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>INDEX</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<h1>
		<div class="container p-3 my-3 bg-secondary text-white text-center">INDEX</div>
	</h1>

	<div>
		<table class="table table-bordered text-center table-hover">
				<tr>
					<td><a href = "<%= request.getContextPath() %>/storeList.jsp">Store List</a></td>
				</tr>
				<tr>
					<td><a href = "<%= request.getContextPath() %>/staffList.jsp">Staff List</a></td>
				</tr>
				<!-- view 7개 리스트 -->
				<tr>
					<td><a href = "<%= request.getContextPath() %>/actorInfoList.jsp">actorInfo List (view)</a></td>
				</tr>
				<tr>
					<td><a href = "<%= request.getContextPath() %>/customerListViewList.jsp">customerList List (view)</a></td>
				</tr>
				<tr>
					<td><a href = "<%= request.getContextPath() %>/filmListViewList.jsp">filmList List (view)</a></td>
				</tr>
				<tr>
					<td><a href = "<%= request.getContextPath() %>/nicerButSlowerFilmListViewList.jsp">nicerButSlowerFilmList List (view)</a></td>
				</tr>
				<tr>
					<td><a href = "<%= request.getContextPath() %>/salesByFilmCategoryList.jsp">salesByFilmCategory List (view)</a></td>
				</tr>
				<tr>
					<td><a href = "<%= request.getContextPath() %>/salesByStoreList.jsp">salesByStore List (view)</a></td>
				</tr>
				<tr>
					<td><a href = "<%= request.getContextPath() %>/staffListViewList.jsp">staffList List (view)</a></td>
				</tr>
				<!-- procedure 3개 결과 화면 -->
				<tr>
					<td><a href = "<%= request.getContextPath() %>/filmInStock.jsp">filmInStock (procedure)</a></td>
				</tr>
				<tr>
					<td><a href = "<%= request.getContextPath() %>/filmNotInStock.jsp">filmNotInStock (procedure)</a></td>
				</tr>
				<tr>
					<td><a href = "<%= request.getContextPath() %>/rewardsReport.jsp">rewardsReport (procedure)</a></td>
				</tr>
		</table>
	</div>
</div>
</body>
</html>