package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.ActorInfo;

public class ActorInfoDao {
	public List<ActorInfo> selectActorInfoListByPage(int beginRow, int rowPerPage) { // -페이징
		List<ActorInfo> list = new ArrayList<ActorInfo>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT * FROM actor_info ORDER BY actor_id LIMIT ?, ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			 stmt = conn.prepareStatement(sql);
			 // ?
			 rs = stmt.executeQuery();
			 // rs.next() .... list.add
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
