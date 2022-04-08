package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.FilmListView;

public class FilmListViewDao {
	// -FilmListView 페이징 메서드
	public List<FilmListView> selectFilmListViewByPage(int beginRow, int rowPerPage) { 
		List<FilmListView> list = new ArrayList<FilmListView>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT fid fid, title title, description description, category category,"
				+    " price price, length length, rating rating, actors actors"
				+    " FROM film_list ORDER BY fid LIMIT ?, ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			 stmt = conn.prepareStatement(sql);
			 stmt.setInt(1, beginRow);
			 stmt.setInt(2, rowPerPage);
			 
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
	
	// -FilmListView 전체 행의 개수를 구하는 메서드
	public int selectFilmListViewTotalRow() {
		int totalRow = 0; // -전체 행의 수를 넣을 int타입 변수 생성 및 초기화
		
		// -DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection(); // -mariaDb 드라이버 연결
		
		// -film_list의 전체 행을 구하는 쿼리
		String sql = "SELECT count(*) cnt FROM film_list";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()){
			 	totalRow = rs.getInt("cnt");
			 	System.out.println("[selectFilmListViewTotalRow] totalRow : " + totalRow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// -DB 자원 반납
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return totalRow; // -전체 행의 수 반환
	}
}
