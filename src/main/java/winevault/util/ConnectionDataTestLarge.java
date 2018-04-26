package winevault.util;

public class ConnectionDataTestLarge implements IConnectionData {
	private static final String url = 
			"jdbc:mysql://localhost/winevaultdbtest150?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private static final String user = "root";
	private static final String password = "password";
	
	public String getURL() { return url; }

	public String getUser() { return user; }

	public String getPassword() { return password; }
}
