package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

public class FilmNotInStockDao {
	
	public Map<String, Object> filmNotInStockCall(int filmId, int storeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Connection conn = null;
		// PreparedStatement : 쿼리를 실행
		// CallableStatement : 프로시저를 실행
		CallableStatement stmt = null;
		ResultSet rs = null;
		// select inventory_id .... -(select 결과물)
		List<Integer> list = new ArrayList<>();
		// select count(inventory_id) ....
		Integer count = 0;
		
		conn = DBUtil.getConnection();
		try {
			stmt = conn.prepareCall("{CALL film_not_in_stock(?, ?, ?)}");
			// -프로시저를 사용할 때 { } 사용
			stmt.setInt(1, filmId);
			stmt.setInt(2, storeId);
			stmt.registerOutParameter(3, Types.INTEGER); // -숫자값으로 리턴 받을 것이므로 Types.INTEGER
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt(1)); // rs.getInt("inventory_id")
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
		FilmNotInStockDao fd = new FilmNotInStockDao();
		int filmId = 1; // -영화 ID
		int storeId = 2; // -가게 ID
		
		Map<String, Object> map = fd.filmNotInStockCall(filmId, storeId); // - ex> 2번 영화, 1번 가게
		List<Integer> list = (List<Integer>)map.get("list");
		int count = (Integer)map.get("count");
		
		System.out.println(filmId + "번 영화는 " + storeId + "번 가게에 " + count + "개 없음"); // - ex> 2번 영화, 1번 가게, 1개 없음 / 없는 재고 번호는 6
		for(int id : list) {
			System.out.println(id);
		}
	}
}
