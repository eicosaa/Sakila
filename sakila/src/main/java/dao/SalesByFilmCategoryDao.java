package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.SalesByFilmCategory;

public class SalesByFilmCategoryDao {
	
	// -SalesByFilmCategory select 메서드
	public List<SalesByFilmCategory> selectSalesByFilmCategory() { 
		List<SalesByFilmCategory> list = new ArrayList<SalesByFilmCategory>();
		
		Connection conn = null;
		conn = DBUtil.getConnection();
		
		String sql = "SELECT category category, total_sales totalSales FROM sales_by_film_category";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			 stmt = conn.prepareStatement(sql);
			 
			 rs = stmt.executeQuery();
			 while(rs.next()) {
				 SalesByFilmCategory s = new SalesByFilmCategory();
				 	s.setCategory(rs.getString("category"));
				 	s.setTotalSales(rs.getInt("totalSales"));
			    	list.add(s);
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
