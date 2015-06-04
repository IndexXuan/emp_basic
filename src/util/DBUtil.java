package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 获取驱动类
			Class.forName("com.mysql.jdbc.Driver");
			// 获取与制定数据库的连接
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/indexxuan", "root", "123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭连接
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
