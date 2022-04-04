package dao;

import java.util.*;
import java.sql.*;

import util.DBUtil;

public class StaffDao {
	
	public List<Map<String, Object>> selectStaffList() {
		// ArrayList는 List 인터페이스의 구현체 중 하나이다.
		// HashMap은 Map 인터페이스의 구현체 중 하나이다.
		List<Map<String, Object>> list = new ArrayList<>(); // 다형성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try { // -클래스가 없으면 ClassNotFoundException이 발생하니 예외처리를 한다.
			conn = DBUtil.getConnection();
			
			String sql = "SELECT"
					+ "		s1.staff_id staffId,"
					+ "		concat(s1.first_name, ' ', s1.last_name) staffName,"
					+ "		s1.address_id addressId,"
					+ " 	CONCAT(a.address, IFNULL(a.address2, ' '), district) staffAddress,"
					+ "		s1.email email,"
					+ "		s1.store_id storeId,"
					+ "		s1.active active,"
					+ "		s1.username username,"
					+ "		s1.password password,"
					+ "		s1.last_update lastUpdate"
					+ " FROM staff s1"
					+ " INNER JOIN store s2"
					+ " INNER JOIN address a"
					+ " ON s1.store_id = s2.store_id"
					+ " AND s1.address_id = a.address_id;";
			
			stmt = conn.prepareStatement(sql);
		    rs = stmt.executeQuery();
		    while(rs.next()) {
		    	Map<String, Object> map = new HashMap<>(); // 다형성
		    	map.put("staffId", rs.getInt("staffId"));
		    	map.put("staffName", rs.getString("staffName"));
		    	map.put("addressId", rs.getInt("addressId"));
		    	map.put("staffAddress", rs.getString("staffAddress"));
		    	map.put("email", rs.getString("email"));
		    	map.put("storeId", rs.getInt("storeId"));
		    	map.put("active", rs.getInt("active"));
		    	map.put("username", rs.getString("username"));
		    	map.put("password", rs.getString("password"));
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
	
	// selectStaffList() 테스트 코드 <- 단위테스트
	public static void main(String[] args) {
		StaffDao dao = new StaffDao();
		List<Map<String, Object>> list = dao.selectStaffList();
		for(Map m : list) {
			System.out.println("[selectStaffList] staffId : " + m.get("staffId"));
			System.out.println("[selectStaffList] staffName : " + m.get("staffName"));
			System.out.println("[selectStaffList] addressId : " + m.get("addressId"));
			System.out.println("[selectStaffList] staffAddress : " + m.get("staffAddress"));
			System.out.println("[selectStaffList] email : " + m.get("email"));
			System.out.println("[selectStaffList] storeId : " + m.get("storeId"));
			System.out.println("[selectStaffList] active : " + m.get("active"));
			System.out.println("[selectStaffList] username : " + m.get("username"));
			System.out.println("[selectStaffList] password : " + m.get("password"));
			System.out.println("[selectStaffList] lastUpdate : " + m.get("lastUpdate"));
			System.out.println("");
		}
	}
}
