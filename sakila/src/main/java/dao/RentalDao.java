package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

public class RentalDao {
	
	public List<Map<String, Object>> selectRentalSearchList(
		int storeId, String customerName, String beginDate, String endDate) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		 /*
		      SELECT 
		      r.*, 
		      CONCAT(c.first_name, ' ', c.last_name) cName, 
		      s.store_id storeId, 
		      i.film_id filmId,
		      f.title
		      FROM rental r INNER JOIN customer c
		      ON r.customer_id = c.customer_id
		         INNER JOIN staff s
		         ON r.staff_id = s.staff_id
		            INNER JOIN inventory i
		            ON r.inventory_id = i.inventory_id
		               INNER JOIN film f
		               ON i.film_id = f.film_id
		      WHERE s.store_id = ? AND CONCAT(c.first_name, ' ', c.last_name) LIKE ?
		      AND r.rental_date BETWEEN STR_TO_DATE(?, '%Y-%m-%d') 
		      AND STR_TO_DATE(?, '%Y-%m-%d');
		 */
		try {
			// 동적쿼리
			String sql = "SELECT"
					+ "		r.*,"
					+ "		CONCAT(c.first_name, ' ', c.last_name) cName,"
					+ "		s.store_id storeId,"
					+ "		i.film_id filmId,"
					+ "		f.title title"
					+ "	  FROM rental r INNER JOIN customer c"
					+ "	  ON r.customer_id = c.customer_id"
					+ "		INNER JOIN staff s"
					+ "		ON r.staff_id = s.staff_id"
					+ "		   INNER JOIN inventory i"
					+ "		      ON r.inventory_id = i.inventory_id"
					+ "		         INNER JOIN film f"
					+ "		         ON i.film_id = f.film_id"
					+ " WHERE CONCAT(c.first_name, ' ', c.last_name) LIKE ?";
			
			// -1. 가게만 검색했을 때 (storeId)--------------------------------------
			if(storeId != -1 && beginDate.equals("") && endDate.equals("")) { // -가게 번호만 입력되었다
				sql += " AND s.store_id = ?"; // -앞에 빈칸이 있어야 한다.
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + customerName + "%");
				stmt.setInt(2, storeId);
			} 
			
			// -2. 대여 날짜만 검색했을 때 (beginDate, endDate)-----------------------
			else if(storeId == -1 && !beginDate.equals("") && !endDate.equals("")) { // -대여 날짜만 입력되었다
				sql += " AND r.rental_date BETWEEN STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d')";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + customerName + "%");
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
			}
			
			// -3. 가게, 대여 날짜만 검색했을 때 (storeId, beginDate, endDate)---------
			else if(storeId != -1 && !beginDate.equals("") && !endDate.equals("")) { // -가게, 대여 날짜만 입력되었다
				sql += " AND s.store_id = ? AND r.rental_date BETWEEN STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d')";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + customerName + "%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
			}
			
			// -4. 아무것도 입력하지 않았을 때-----------------------------------------
			else if(storeId == -1 && beginDate.equals("") && endDate.equals("")) { // -아무것도 입력되지 않았다.
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + customerName + "%");
			}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>(); // 다형성
		    	map.put("rentalId", rs.getInt("rental_id"));
		    	map.put("rentalDate", rs.getString("rental_date"));
		    	map.put("inventoryId", rs.getInt("inventory_id"));
		    	map.put("customerId", rs.getInt("customer_id"));
		    	map.put("returnDate", rs.getString("return_date"));
		    	map.put("staffId", rs.getInt("staff_id"));
		    	map.put("lastUpdate", rs.getString("last_update"));
		    	map.put("customerName", rs.getString("cName"));
		    	map.put("customerId", rs.getInt("rental_id"));
		    	map.put("filmId", rs.getInt("filmid"));
		    	map.put("title", rs.getString("title"));
		    	list.add(map);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// -단위 테스트 (테스트 코드)
	public static void main(String[] args) {
		/* ex) 2번 가게에서 JOEL이 2005-07-01 ~ 2005-07-30 대여한 목록 */
		int storeId = 2; // -가게 번호
		String customerName = "JOEL"; // -고객 이름
		String beginDate = "2005-07-01"; // -빌린 날짜
		String endDate = "2005-07-30"; // -빌린 날짜
		
		RentalDao rentalDao = new RentalDao();
		
		
		List<Map<String, Object>> list = rentalDao.selectRentalSearchList(storeId, customerName, beginDate, endDate);
		
		for(Map<String, Object> m : list) {
			System.out.println("[rentalDao.selectRentalSearchList] rentalId : " + m.get("rentalId"));
			System.out.println("[rentalDao.selectRentalSearchList] rentalDate : " + m.get("rentalDate"));
			System.out.println("[rentalDao.selectRentalSearchList] inventoryId : " + m.get("inventoryId"));
			System.out.println("[rentalDao.selectRentalSearchList] customerId : " + m.get("customerId"));
			System.out.println("[rentalDao.selectRentalSearchList] returnDate : " + m.get("returnDate"));
			System.out.println("[rentalDao.selectRentalSearchList] staffId : " + m.get("staffId"));
			System.out.println("[rentalDao.selectRentalSearchList] lastUpdate : " + m.get("lastUpdate"));
			System.out.println("[rentalDao.selectRentalSearchList] customerName : " + m.get("customerName"));
			System.out.println("[rentalDao.selectRentalSearchList] customerId : " + m.get("customerId"));
			System.out.println("[rentalDao.selectRentalSearchList] filmId : " + m.get("filmId"));
			System.out.println("[rentalDao.selectRentalSearchList] title : " + m.get("title"));
			System.out.println("");
		}
		
		System.out.println("[rentalDao.selectRentalSearchList] storeId : " + storeId);
		System.out.println("[rentalDao.selectRentalSearchList] customerName : " + customerName);
		System.out.println("[rentalDao.selectRentalSearchList] beginDate : " + beginDate);
		System.out.println("[rentalDao.selectRentalSearchList] endDate : " + endDate);
	}
}
