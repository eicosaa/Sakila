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

public class StatsDataDao {

	// -customer별 총 amount (제일 많이(금액) 빌려간 사람)
	public List<Map<String, Object>> amountByCustomer() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT p.customer_id customerId,"
				+ "		CONCAT(c.first_name, ' ', c.last_name) name,"
				+ "		SUM(p.amount) total"
				+ "		FROM payment p INNER JOIN customer c"
				+ "		ON p.customer_id = c.customer_id"
				+ "		GROUP BY p.customer_id"
				+ "		HAVING SUM(p.amount) > 180"
				+ "		ORDER BY SUM(p.amount) DESC LIMIT 0, 10";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("customerId",rs.getInt("customerId"));
	            m.put("name",rs.getString("name"));
	            m.put("total",rs.getInt("total"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -rental_rate별 영화 개수
	public List<Map<String, Object>> filmCountByRentalRate() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT rental_rate rentalRate, COUNT(*) cnt"
				+ "		FROM film"
				+ "		GROUP BY rental_rate"
				+ "		ORDER BY COUNT(*) DESC";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("rentalRate",rs.getDouble("rentalRate"));
	            m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -rating별 영화 개수
	public List<Map<String, Object>> filmCountByRating() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT rating, COUNT(*) cnt"
				+ "		FROM film"
				+ "		GROUP BY rating";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("rating",rs.getString("rating"));
	            m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -제일 많이(횟수) 빌려간 사람
	public List<Map<String, Object>> rentByCustomer() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT *"
				+ " FROM customer"
				+ " WHERE customer_id = (SELECT customer_id"
				+ "							FROM payment"
				+ "							GROUP BY customer_id"
				+ "							order by COUNT(*) DESC"
				+ "							LIMIT 0, 1)";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("customerId",rs.getInt("customer_id"));
				m.put("storeId",rs.getInt("store_id"));
				m.put("firstName",rs.getString("first_name"));
				m.put("lastName",rs.getString("last_name"));
				m.put("email",rs.getString("email"));
	            m.put("addressId",rs.getInt("address_id"));
	            m.put("active",rs.getInt("active"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -language별 영화 개수
	public List<Map<String, Object>> filmCountByLanguage() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT l.name language, COUNT(*) cnt"
				+ "		FROM film f INNER JOIN language l"
				+ "		ON f.language_id = l.language_id"
				+ "		GROUP BY l.name";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("language",rs.getString("language"));
				m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -length별 영화 개수
	public List<Map<String, Object>> filmCountByLength() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT t.length2 length, COUNT(*) cnt"
				+ "		FROM (SELECT title, LENGTH,"
				+ "		CASE WHEN LENGTH < 60 THEN '60분 미만' "
				+ "			  WHEN LENGTH BETWEEN 61 AND 120 THEN '61분 ~ 120분' "
				+ "			  WHEN LENGTH BETWEEN 121 AND 180 THEN '121분 ~ 180분' "
				+ "			  ELSE '180분 이상' "
				+ "		END LENGTH2"
				+ "		FROM film) t"
				+ "	  GROUP BY t.length2";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("length",rs.getString("length"));
				m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -store 매장 요일별 매출 (월화수목금토일)
	public List<Map<String, Object>> storeWeekAmount() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT s.store_id storeId, t.w weekDayNo, "
				+ "				case t.w"
				+ "					WHEN 0 THEN '월'"
				+ "					WHEN 1 THEN '화'"
				+ "					WHEN 2 THEN '수'"
				+ "					WHEN 3 THEN '목'"
				+ "					WHEN 4 THEN '금'"
				+ "					WHEN 5 THEN '토'"
				+ "					WHEN 6 THEN '일'"
				+ "				END DAYOFWEEK, t.c"
				+ " FROM (SELECT staff_id, WEEKDAY(payment_date) w,  COUNT(*) c"
				+ "		  FROM payment"
				+ "		  GROUP BY staff_id, WEEKDAY(payment_date)) t "
				+ "		  INNER JOIN staff s"
				+ "		  ON t.staff_id = s.staff_id"
				+ " ORDER BY s.store_id, t.w ASC";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("storeId",rs.getInt("storeId"));
				m.put("weekDayNo",rs.getString("weekDayNo"));
				m.put("DAYOFWEEK",rs.getString("DAYOFWEEK"));
				m.put("cnt",rs.getInt("c"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -actor별 영화 출연 횟수
	public List<Map<String, Object>> actorFilmCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT a.actor_id actorId, CONCAT(a.first_name, ' ', a.last_name) name, COUNT(*) cnt"
				+ "		FROM film_actor fa INNER JOIN actor a"
				+ "		ON fa.actor_id = a.actor_id"
				+ "		GROUP BY a.actor_id"
				+ "		ORDER BY COUNT(*) DESC LIMIT 0, 5"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("actorId",rs.getInt("actorId"));
				m.put("name",rs.getString("name"));
				m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -store별 영화 소지 개수 
	public List<Map<String, Object>> storeFilmCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT s.store_id storeId, COUNT(*) cnt"
				+ "		FROM inventory i INNER JOIN store s"
				+ "		ON i.store_id = s.store_id"
				+ "		GROUP BY s.store_id"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("storeId",rs.getInt("storeId"));
				m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -customer별 store를 이용한 횟수 
	public List<Map<String, Object>> customerUseStoreCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT r.customer_id customerId, s.store_id storeId, COUNT(*) cnt"
				+ "		FROM rental r INNER JOIN staff s"
				+ "		ON r.staff_id = s.staff_id"
				+ "		GROUP BY r.customer_id, s.store_id LIMIT 0, 5"; // -결과값이 너무 길어서 limit
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("customerId",rs.getInt("customerId"));
				m.put("storeId",rs.getInt("storeId"));
				m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -스태프별(staff_id) 대여(rental_id)해준 횟수
	public List<Map<String, Object>> staffRentalCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT s.staff_id staffId, COUNT(*) cnt"
				+ "		FROM staff s INNER JOIN rental r"
				+ "		ON s.staff_id = r.staff_id"
				+ "		GROUP BY s.store_id"
				+ "		ORDER BY COUNT(*) DESC";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("staffId",rs.getInt("staffId"));
				m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -영화(film)별 빌려간 횟수 
	public List<Map<String, Object>> filmRentalCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT f.film_id filmId, f.title title, COUNT(*) cnt"
				+ "		FROM film f INNER JOIN inventory i"
				+ "		ON f.film_id = i.film_id"
				+ "		INNER JOIN rental r"
				+ "		ON i.inventory_id = r.inventory_id"
				+ "		GROUP BY f.film_id LIMIT 0, 5";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("filmId",rs.getInt("filmId"));
				m.put("title",rs.getString("title"));
				m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -inventory별 빌려간 횟수
	public List<Map<String, Object>> inventoryRentalCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT i.inventory_id inventoryId, COUNT(*) cnt"
				+ "		FROM inventory i INNER JOIN rental r"
				+ "		ON i.inventory_id = r.inventory_id"
				+ "		GROUP BY i.inventory_id LIMIT 0, 5";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("inventoryId",rs.getInt("inventoryId"));
				m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -나라(country)별 고객(customer) 수
	public List<Map<String, Object>> coutryCustomerCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT c.country_id countryId, c.country country, COUNT(*) cnt"
				+ "		FROM country c INNER JOIN city ci"
				+ "		ON c.country_id = ci.country_id"
				+ "		INNER JOIN address a"
				+ "		ON ci.city_id = a.city_id"
				+ "		INNER JOIN customer cu"
				+ "		ON a.address_id = cu.address_id"
				+ "		GROUP BY c.country_id limit 0, 5";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("countryId",rs.getInt("countryId"));
				m.put("country",rs.getString("country"));
				m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	// -country별 대여(rental) 횟수
	public List<Map<String, Object>> coutryRentalCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT c.country_id countryId, c.country country, COUNT(*) cnt"
				+ "		FROM country c INNER JOIN city ci"
				+ "		ON c.country_id = ci.country_id"
				+ "		INNER JOIN address a"
				+ "		ON ci.city_id = a.city_id"
				+ "		INNER JOIN customer cu\r\n"
				+ "		ON a.address_id = cu.address_id"
				+ "		INNER JOIN rental r"
				+ "		ON cu.customer_id = r.customer_id"
				+ "		GROUP BY c.country_id LIMIT 0, 5";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("countryId",rs.getInt("countryId"));
				m.put("country",rs.getString("country"));
				m.put("cnt",rs.getInt("cnt"));
	            list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
}
