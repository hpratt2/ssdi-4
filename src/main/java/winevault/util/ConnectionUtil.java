package winevault.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
	public static Connection getConnection(IConnectionData connData) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connData.getURL(), connData.getUser(), connData.getPassword());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
