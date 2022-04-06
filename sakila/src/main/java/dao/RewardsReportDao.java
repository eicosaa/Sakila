package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;
import vo.RewardsReport;

public class RewardsReportDao {

	public Map<String, Object> rewardsReportCall(int monthlyPurchases, int dollarAmountPurchased) {
		Map<String, Object> map = new HashMap<String, Object>();	
		List<RewardsReport> list = new ArrayList<RewardsReport>();
		
		Connection conn = null;
		// PreparedStatement : 쿼리를 실행
		// CallableStatement : 프로시저를 실행
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		Integer count = 0;
		
		conn = DBUtil.getConnection();
		try {
			stmt = conn.prepareCall("{CALL rewards_report(?, ?, ?)}");
			// -프로시저를 사용할 때 { } 사용
			stmt.setInt(1, monthlyPurchases);
			stmt.setInt(2, dollarAmountPurchased);
			stmt.registerOutParameter(3, Types.INTEGER); // -(count)숫자값으로 리턴 받을 것이므로 Types.INTEGER
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				RewardsReport r = new RewardsReport();
				r.setCustomerId(rs.getInt("customerId"));
				r.setStoreId(rs.getInt("storeId"));
				r.setFirstName(rs.getString("firstName"));
				r.setLastName(rs.getString("lastName"));
				r.setEmail(rs.getString("email"));
				r.setAddressId(rs.getInt("addressId"));
				r.setActive(rs.getInt("active"));
				r.setCreateDate(rs.getString("createDate"));
				r.setLastUpdate(rs.getString("lastUpdate"));
				list.add(r);
			}
			
			count = stmt.getInt(3); // 프로시저 3번째 out변수 값
			// -3번째 물음표의 값을 count라는 변수로 받는다.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("count", count); 
		
		return map;
	}
	
	// -단위 테스트 (테스트 코드)
	public static void main(String[] args) {
		RewardsReportDao rrd = new RewardsReportDao();
		int monthlyPurchases = 7; // -고객이 자격을 갖추기 위해 지난 달에 수행해야 하는 최소 구매 또는 렌탈 횟수
		int dollarAmountPurchased = 50; // -고객이 자격을 갖추기 위해 지난 달에 지출해야 하는 최소 달러 금액
		
		Map<String, Object> map = rrd.rewardsReportCall(monthlyPurchases, dollarAmountPurchased); 
		List<RewardsReport> list = new ArrayList<RewardsReport>();
		int count = (Integer)map.get("count");
		
		System.out.println("최소 구매 횟수는 " + monthlyPurchases + "번이고, "+ "고객 자격을 갖춘 최소 달러 금액은 " + dollarAmountPurchased);
		for(RewardsReport id : list) {
			System.out.println("조건을 만족한 고객 정보 : " + id);
		}
	}
}
