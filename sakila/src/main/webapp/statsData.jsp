<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="dao.*"%>
<%
	StatsDataDao statsDataDao = new StatsDataDao();

	// -1. 제일 많이 빌려간 사람(횟수)
	List<Map<String, Object>> rentByCustomer = statsDataDao.rentByCustomer();
	//  2. customer별 총 amount
	List<Map<String, Object>> amountByCustomer = statsDataDao.amountByCustomer();
	// -3. rental_rate별 영화 개수
	List<Map<String, Object>> filmCountByRentalRate = statsDataDao.filmCountByRentalRate();
	// -4. rating별 영화 개수
	List<Map<String, Object>> filmCountByRating = statsDataDao.filmCountByRating();
	// -5. language별 영화 개수
	List<Map<String, Object>> filmCountByLanguage = statsDataDao.filmCountByLanguage();
	// -6. length별 영화 개수
	List<Map<String, Object>> filmCountByLength = statsDataDao.filmCountByLength();
	// -7. store 매장 요일별 매출
	List<Map<String, Object>> storeWeekAmount = statsDataDao.storeWeekAmount();
	// -8. actor별 영화 출연 횟수
	List<Map<String, Object>> actorFilmCount = statsDataDao.actorFilmCount();
	// -9. store별 영화 소지 개수
	List<Map<String, Object>> storeFilmCount = statsDataDao.storeFilmCount();
	// -10. customer별 store를 이용한 횟수
	List<Map<String, Object>> customerUseStoreCount = statsDataDao.customerUseStoreCount();
	// -11. 스태프별(staff_id) 대여(rental_id)해준 횟수
	List<Map<String, Object>> staffRentalCount = statsDataDao.staffRentalCount();
	// -12. 영화(film)별 빌려간 횟수 
	List<Map<String, Object>> filmRentalCount = statsDataDao.filmRentalCount();
	// -13. inventory별 빌려간 횟수 
	List<Map<String, Object>> inventoryRentalCount = statsDataDao.inventoryRentalCount();
	// -14. 나라(country)별 고객(customer) 수
	List<Map<String, Object>> coutryCustomerCount = statsDataDao.coutryCustomerCount();	
	// -15. country별 대여(rental) 횟수
	List<Map<String, Object>> coutryRentalCount = statsDataDao.coutryRentalCount();	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>1. 제일 많이 빌려간 사람(횟수)</h1>
	<table border = "1">
		<tr>
			<th>고객 번호</th>
			<th>매장 번호</th>
			<th>이름</th>
			<th>이름(성)</th>
			<th>이메일</th>
			<th>주소 번호</th>
			<th>활동 여부</th>
		</tr>
		<%
			for(Map<String, Object> m : rentByCustomer) {
		%>
				<tr>
					<td><%= m.get("customerId") %></td>
					<td><%= m.get("storeId") %></td>
					<td><%= m.get("firstName") %></td>
					<td><%= m.get("lastName") %></td>
					<td><%= m.get("email") %></td>
					<td><%= m.get("addressId") %></td>
					<td><%= m.get("active") %></td>
				</tr>
		<%
			}
		%>
	</table>

	<h1>2. 제일 많이 빌려간 사람(금액 180이상)</h1>
	<table border = "1">
		<tr>
			<th>고객 아이디</th>
			<th>고객 이름</th>
			<th>총 지불액</th>
		</tr>
		<%
			for(Map<String, Object> m : amountByCustomer) {
		%>
				<tr>
					<td><%= m.get("customerId") %></td>
					<td><%= m.get("name") %></td>
					<td><%= m.get("total") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>3. rental_rate별 영화 개수</h1>
	<table border = "1">
		<tr>
			<th>대여료</th>
			<th>총 영화 수</th>
		</tr>
		<%
			for(Map<String, Object> m : filmCountByRentalRate) {
		%>
				<tr>
					<td><%= m.get("rentalRate") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>4. rating별 영화 개수</h1>
	<table border = "1">
		<tr>
			<th>등급</th>
			<th>총 영화 수</th>
		</tr>
		<%
			for(Map<String, Object> m : filmCountByRating) {
		%>
				<tr>
					<td><%= m.get("rating") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>5. language별 영화 개수</h1>
	<table border = "1">
		<tr>
			<th>언어</th>
			<th>총 영화 수</th>
		</tr>
		<%
			for(Map<String, Object> m : filmCountByLanguage) {
		%>
				<tr>
					<td><%= m.get("language") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>6. length별 영화 개수</h1>
	<table border = "1">
		<tr>
			<th>영화 상영 시간</th>
			<th>총 영화 수</th>
		</tr>
		<%
			for(Map<String, Object> m : filmCountByLength) {
		%>
				<tr>
					<td><%= m.get("length") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>7. store 매장 요일별 매출</h1>
	<table border = "1">
		<tr>
			<th>매장 번호</th>
			<th>요일 번호</th>
			<th>요일</th>
			<th>수</th>
		</tr>
		<%
			for(Map<String, Object> m : storeWeekAmount) {
		%>
				<tr>
					<td><%= m.get("storeId") %></td>
					<td><%= m.get("weekDayNo") %></td>
					<td><%= m.get("DAYOFWEEK") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>8. actor별 영화 출연 횟수 (top 5)</h1>
	<table border = "1">
		<tr>
			<th>배우 번호</th>
			<th>배우 이름</th>
			<th>출연 횟수</th>
		</tr>
		<%
			for(Map<String, Object> m : actorFilmCount) {
		%>
				<tr>
					<td><%= m.get("actorId") %></td>
					<td><%= m.get("name") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>9. store별 영화 소지 개수</h1>
	<table border = "1">
		<tr>
			<th>매장 번호</th>
			<th>영화 소지 개수</th>
		</tr>
		<%
			for(Map<String, Object> m : storeFilmCount) {
		%>
				<tr>
					<td><%= m.get("storeId") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>10. customer별 store를 이용한 횟수</h1>
	<table border = "1">
		<tr>
			<th>고객 번호</th>
			<th>매장 번호</th>
			<th>store 사용 횟수</th>
		</tr>
		<%
			for(Map<String, Object> m : customerUseStoreCount) {
		%>
				<tr>
					<td><%= m.get("customerId") %></td>
					<td><%= m.get("storeId") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>11. 스태프별(staff_id) 대여(rental_id)해준 횟수</h1>
	<table border = "1">
		<tr>
			<th>직원 번호</th>
			<th>대여해준 횟수</th>
		</tr>
		<%
			for(Map<String, Object> m : staffRentalCount) {
		%>
				<tr>
					<td><%= m.get("staffId") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>12. 영화(film)별 빌려간 횟수</h1>
	<table border = "1">
		<tr>
			<th>영화 번호</th>
			<th>제목</th>
			<th>대여해간 횟수</th>
		</tr>
		<%
			for(Map<String, Object> m : filmRentalCount) {
		%>
				<tr>
					<td><%= m.get("filmId") %></td>
					<td><%= m.get("title") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>13. inventory별 빌려간 횟수</h1>
	<table border = "1">
		<tr>
			<th>inventory 번호</th>
			<th>빌려간 횟수</th>
		</tr>
		<%
			for(Map<String, Object> m : inventoryRentalCount) {
		%>
				<tr>
					<td><%= m.get("inventoryId") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>14. 나라(country)별 고객(customer)수</h1>
	<table border = "1">
		<tr>
			<th>나라 번호</th>
			<th>나라</th>
			<th>고객 수</th>
		</tr>
		<%
			for(Map<String, Object> m : coutryCustomerCount) {
		%>
				<tr>
					<td><%= m.get("countryId") %></td>
					<td><%= m.get("country") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>15. country별 대여(rental) 횟수</h1>
	<table border = "1">
		<tr>
			<th>나라 번호</th>
			<th>나라</th>
			<th>대여 횟수</th>
		</tr>
		<%
			for(Map<String, Object> m : coutryRentalCount) {
		%>
				<tr>
					<td><%= m.get("countryId") %></td>
					<td><%= m.get("country") %></td>
					<td><%= m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>