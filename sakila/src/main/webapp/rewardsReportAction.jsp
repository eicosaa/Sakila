<%@page import="vo.RewardsReport"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%
	int monthlyPurchases = 0;
	int dollarAmountPurchased = 0;
	int count = 0;
	
	// -요청값	
	if (request.getParameter("monthlyPurchases") != null) {
		monthlyPurchases= Integer.parseInt(request.getParameter("monthlyPurchases"));
	}
	if (request.getParameter("dollarAmountPurchased") != null) {
		dollarAmountPurchased= Integer.parseInt(request.getParameter("dollarAmountPurchased"));
	}

	RewardsReportDao rrd = new RewardsReportDao();
	Map<String, Object> map = rrd.rewardsReportCall(monthlyPurchases, dollarAmountPurchased);
	List<RewardsReport> list = (List<RewardsReport>)map.get("list");
	//List<String> list = (List<String>)map.get("list");
	count = (Integer)map.get("count");
	
	// -디버깅
	System.out.println("[rewardsReportAction.jsp] monthlyPurchases : " + monthlyPurchases);
	System.out.println("[rewardsReportAction.jsp] dollarAmountPurchased : " + dollarAmountPurchased);
	System.out.println("[rewardsReportAction.jsp] list : " + list);
	System.out.println("[rewardsReportAction.jsp] count : " + count);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>rewardsReportAction</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class = "container">	
<br>
	<a href = "<%= request.getContextPath() %>/index.jsp" class = "btn btn-outline-secondary">index</a>
	<h1>
		<div class="container p-3 my-3 bg-secondary text-white text-center">rewardsReport (procedure)</div>
	</h1>
	
	<%
		if (monthlyPurchases != 0 && dollarAmountPurchased != 0) {
	%>
			<table class = "table table-bordered text-center table-hover">
				<tr>
					<td>최소 구매 횟수</td>
					<td><%= monthlyPurchases %></td>
				</tr>
				<tr>
					<td>최소 달러 금액</td>
					<td><%= dollarAmountPurchased %></td>
				</tr>
				<tr>
					<td>조건을 만족한 고객의 수</td>
					<td><%= count %></td>
				</tr>
			</table>
			
			<div class="container p-3 my-3 bg-secondary text-white text-center">조건을 만족한 고객 정보</div>
			<table class = "table table-bordered text-center table-hover">
				<tr class="bg-dark text-light">
					<td>customerId</td>
					<td>storeId</td>
					<td>firstName</td>
					<td>lastName</td>
					<td>email</td>
					<td>address</td>
					<td>active</td>
					<td>createDate</td>
					<td>updateDate</td>
				</tr>
					
				    <%
						for(RewardsReport r : list) {
					%>
							<tr>
								<td><%=r.getCustomerId()%></td>
							    <td><%=r.getStoreId()%></td>
							    <td><%=r.getFirstName()%></td>
							    <td><%=r.getLastName()%></td>
							    <td><%=r.getEmail()%></td>
							    <td><%=r.getAddressId()%></td>
							    <td><%=r.getActive()%></td>
							    <td><%=r.getCreateDate()%></td>
			      				<td><%=r.getLastUpdate()%></td>
							</tr>
					<%
						}
					%>
			</table>
	<%
		} 
	%>
</div>
</body>
</html>