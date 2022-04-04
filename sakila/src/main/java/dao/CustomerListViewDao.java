package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.CustomerListView;

public class CustomerListViewDao {
	
	// -CustomerListView 페이징 메서드
	public List<CustomerListView> selectCustomerListViewByPage(int beginRow, int rowPerPage) { 
		List<CustomerListView> list = new ArrayList<CustomerListView>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT ID, name, address, `zip code` zipCode, phone, city, country, notes, SID FROM customer_list ORDER BY ID LIMIT ?,?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			 stmt = conn.prepareStatement(sql);
			 stmt.setInt(1, beginRow);
			 stmt.setInt(2, rowPerPage);
			 
			 rs = stmt.executeQuery();
			 while(rs.next()) {
				 CustomerListView c = new CustomerListView();
			    	c.setId(rs.getInt("ID"));
			    	c.setName(rs.getString("name"));
			    	c.setAddress(rs.getString("address"));
			    	c.setZipCode(rs.getString("zipCode"));
			    	c.setPhone(rs.getInt("phone"));
			    	c.setCity(rs.getString("city"));
			    	c.setCountry(rs.getString("country"));
			    	c.setNotes(rs.getString("notes"));
			    	c.setSid(rs.getInt("SID"));
			    	list.add(c);
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
	
	// -CustomerListView 전체 행의 개수를 구하는 메서드
	public int selectCustomerListViewTotalRow() {
		int totalRow = 0; // -전체 행의 수를 넣을 int타입 변수 생성 및 초기화
		
		// -DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection(); // -mariaDb 드라이버 연결
		
		// -customer_list의 전체 행을 구하는 쿼리
		String sql = "SELECT count(*) cnt FROM customer_list";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()){
			 	totalRow = rs.getInt("cnt");
			 	System.out.println("[selectCustomerListViewTotalRow] totalRow : " + totalRow);
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
