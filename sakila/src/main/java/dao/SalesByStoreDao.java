package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.SalesByStore;

public class SalesByStoreDao {
	// -SalesByStoreDao select 메서드
	public List<SalesByStore> selectSalesByStore() { 
		List<SalesByStore> list = new ArrayList<SalesByStore>();
		
		Connection conn = null;
		conn = DBUtil.getConnection();
		
		String sql = "SELECT store store, manager manager, total_sales totalSales FROM sales_by_store";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			 stmt = conn.prepareStatement(sql);
			 
			 rs = stmt.executeQuery();
			 while(rs.next()) {
				 SalesByStore s = new SalesByStore();
				 	s.setStore(rs.getString("store")); 
				 	s.setManager(rs.getString("manager"));
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
