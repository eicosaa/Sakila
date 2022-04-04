package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	public static Connection getConnection() { // -중복된 부분의 유지보수, 관리를 위해서 생성 (ex> 비밀번호의 변경이라던지)
		/* -static을 사용하여 객체 생성을 안할 수 있음 (객체 DBUtil dbUtil = new DBUtil();)
			ex>
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			
			-> conn = DBUtil.getConnection();
		*/
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila", "root", "java1234");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn; // -conn을 return해야하기 때문에 finally에 conn.close();를 넣으면 안된다.
	}
}
