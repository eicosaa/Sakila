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
import vo.ActorInfo;

public class ActorInfoDao {
	
	// - ActorInfo 페이징 메서드
	public List<ActorInfo> selectActorInfoListByPage(int beginRow, int rowPerPage) { 
		List<ActorInfo> list = new ArrayList<ActorInfo>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT actor_id actorId, first_name firstName, last_name lastName, film_info filmInfo FROM actor_info ORDER BY actor_id LIMIT ?, ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			 stmt = conn.prepareStatement(sql);
			 stmt.setInt(1, beginRow);
			 stmt.setInt(2, rowPerPage);
			 
			 rs = stmt.executeQuery();
			 while(rs.next()) {
			    	ActorInfo actorInfo = new ActorInfo();
			    	actorInfo.setActorId(rs.getInt("actorId"));
			    	actorInfo.setFirstName(rs.getString("firstName"));
			    	actorInfo.setLastName(rs.getString("lastName"));
			    	actorInfo.setFilmInfo(rs.getString("filmInfo"));
			    	list.add(actorInfo);
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
	
	// -actorInfo 전체 행의 개수를 구하는 메서드
	public int selectActorInfoTotalRow() {
		int totalRow = 0; // -전체 행의 수를 넣을 int타입 변수 생성 및 초기화
		
		// -DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection(); // -mariaDb 드라이버 연결
		
		// -actor_Info의 전체 행을 구하는 쿼리
		String sql = "SELECT count(*) cnt FROM actor_Info";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()){
			 	totalRow = rs.getInt("cnt");
			 	System.out.println("[selectActorInfoTotalRow] totalRow : " + totalRow);
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
	
	/*
	// 단위 테스트 (테스트 코드)
	public static void main(String[] args) {
		ActorInfoDao dao = new ActorInfoDao(); // -ActorInfoDao 객체 생성
		List<ActorInfo> list = dao.selectActorInfoListByPage(beginRow, rowPerPage);
		for(List m : list) {
			System.out.println("[selectActorInfoListByPage] actorId : " + m.get("actorId"));
			System.out.println("[selectActorInfoListByPage] firstName : " + m.get("firstName"));
			System.out.println("[selectActorInfoListByPage] lastName : " + m.get("lastName"));
			System.out.println("[selectActorInfoListByPage] staffAddress : " + m.get("staffAddress"));
			System.out.println("");
		}
	}
	*/
}
