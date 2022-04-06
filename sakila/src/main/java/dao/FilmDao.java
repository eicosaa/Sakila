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
import vo.Category;
import vo.FilmListView;

public class FilmDao {
	
	// -영화 검색
	public List<FilmListView> selectFilmListSearch(int beginRow, int rowPerPage, String category, String rating, double price, int length, String title, String actors) {

		List<FilmListView> list = new ArrayList<FilmListView>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		// -쿼리의 분기로 ?값이 동일하지 않으므로 stmt도 분기 필요
		try {
			// 동적쿼리
			String sql = "SELECT fid, title, description, category, price, length, rating, actors FROM film_list WHERE title LIKE ? AND actors LIKE ?";
			if(category.equals("") && rating.equals("") && price == -1 && length == -1) {
				sql += " ORDER BY fid LIMIT ?, ?"; // -앞에 빈칸이 있어야 한다.
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + title + "%");
				stmt.setString(2, "%" + actors + "%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(category.equals("") && rating.equals("") && price == -1 && length != -1) { // length만 입력되었다
				if(length == 0) {
					sql += " AND length<60 ORDER BY fid LIMIT ?, ?"; 
				} else if(length == 1) {
					sql += " AND length>=60 ORDER BY fid LIMIT ?, ?";
				}
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + title + "%"); // -title 검색어
				stmt.setString(2, "%" + actors + "%"); // -actor 검색어
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(category.equals("") && rating.equals("") && price != -1 && length == -1) { // -price만 입력되었다
				sql += " AND price=? ORDER BY fid LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + title + "%"); // -title 검색어
				stmt.setString(2, "%" + actors + "%"); // -actor 검색어
				stmt.setDouble(3, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} 
			rs = stmt.executeQuery();
			while(rs.next()) {
				FilmListView f = new FilmListView();
				f.setFid(rs.getInt("fid"));
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setCategory(rs.getString("category"));
				f.setPrice(rs.getDouble("price"));
				f.setLength(rs.getInt("length"));
				f.setRating(rs.getString("rating"));
				f.setActors(rs.getString("actors"));
				list.add(f);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Double> selectFilmPriceList() {
		List<Double> list = new ArrayList<Double>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT DISTINCT price FROM film_list ORDER BY price";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getDouble("price"));
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
	
	public Map<String, Object> filmInStockCall(int filmId, int storeId) {
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
			stmt = conn.prepareCall("{CALL film_in_stock(?, ?, ?)}");
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
		FilmDao fd = new FilmDao();
		int filmId = 1; // -영화 ID
		int storeId = 1; // -가게 ID
		
		Map<String, Object> map = fd.filmInStockCall(filmId, storeId); // - ex> 1번 영화, 1번 가게
		List<Integer> list = (List<Integer>)map.get("list");
		int count = (Integer)map.get("count");
		
		System.out.println(filmId + "번 영화는 " + storeId + "번 가게에 " + count + "개 남음");
		for(int id : list) {
			System.out.println(id);
		}
	}
}
