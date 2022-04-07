package dao;

import java.util.*;
import java.sql.*;

import util.DBUtil;

public class StoreDao {
	
	// - storeId를 가져오는 메서드
	public List<Integer> selectStoreIdList() {
		List<Integer> list = new ArrayList<Integer>();
		// 
		
		return list;
	}
	
	public List<Map<String, Object>> selectStoreList() {
		// ArrayList는 List 인터페이스의 구현체 중 하나이다.
		// HashMap은 Map 인터페이스의 구현체 중 하나이다.
		List<Map<String, Object>> list = new ArrayList<>(); // 다형성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try { // -클래스가 없으면 ClassNotFoundException이 발생하니 예외처리를 한다.
			conn = DBUtil.getConnection(); 
			
			String sql = "SELECT"
					+ "		s1.store_id storeId,"
					+ "		s1.manager_staff_id staffId,"
					+ "		CONCAT(s2.first_name, ' ', s2.last_name) staffName,"
					+ "		s1.address_id addressId,"
					+ " 	CONCAT(a.address, IFNULL(a.address2, ' '), district) staffAddress,"
					+ "		s1.last_update lastUpdate"
					+ " FROM store s1"
					+ " INNER JOIN staff s2"
					+ " INNER JOIN address a"
					+ " ON s1.manager_staff_id = s2.staff_id"
					+ " AND s1.address_id = a.address_id;";
			stmt = conn.prepareStatement(sql);
		    rs = stmt.executeQuery();
		    while(rs.next()) {
		    	Map<String, Object> map = new HashMap<>(); // 다형성
		    	map.put("storeId", rs.getInt("storeId"));
		    	map.put("staffId", rs.getInt("staffId"));
		    	map.put("staffName", rs.getString("staffName"));
		    	map.put("addressId", rs.getInt("addressId"));
		    	map.put("staffAddress", rs.getString("staffAddress"));
		    	map.put("lastUpdate", rs.getString("lastUpdate"));
		    	list.add(map);
		    }
		} catch (Exception e) { // ClassNotFoundException, SQLException 두 개의 예외를 부모 타입 Exception으로 처리 -> 다형성
			e.printStackTrace();
			System.out.println("예외 발생");
		} finally {
			// DB자원 해지 - try절에서 예외가 발생하면 자원해지 못한 상태에서 코드가 종료된다. finally절이 필요
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	// selectStoreList() 테스트 코드 <- 단위테스트
	public static void main(String[] args) {
		StoreDao dao = new StoreDao();
		List<Map<String, Object>> list = dao.selectStoreList();
		for(Map m : list) {
			System.out.println(m.get("storeId") + ", ");
			System.out.println(m.get("staffId") + ", ");
			System.out.println(m.get("staffName") + ", ");
			System.out.println(m.get("addressId") + ", ");
			System.out.println(m.get("staffAddress") + ", ");
			System.out.println(m.get("lastUpdate") + ", ");
			System.out.println("");
		}
	}
}
