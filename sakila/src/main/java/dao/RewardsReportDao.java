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

public class RewardsReportDao {

	public Map<String, Object> rewardsReportCall(int monthlyPurchases, int dollarAmountPurchased) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Connection conn = null;
		// PreparedStatement : 쿼리를 실행
		// CallableStatement : 프로시저를 실행
		CallableStatement stmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();
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
				for(int i = 1; i < 10; i++) {
					list.add(rs.getString(i));
				}
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
		
		Map<String, Object> map = rrd.rewardsReportCall(monthlyPurchases, dollarAmountPurchased); // - ex> 1번 영화, 1번 가게
		List<String> list = (List<String>)map.get("list");
		int count = (Integer)map.get("count");
		
		System.out.println("최소 구매 횟수는 " + monthlyPurchases + "번이고, "+ "고객 자격을 갖춘 최소 달러 금액은 " + dollarAmountPurchased);
		for(String id : list) {
			System.out.println("조건을 만족한 고객 정보 : " + id);
		}
	}
}
