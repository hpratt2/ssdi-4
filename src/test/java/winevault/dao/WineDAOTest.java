package winevault.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import winevault.model.IWine;
import winevault.model.Wine;
import winevault.util.ConnectionData;
import winevault.util.ConnectionUtil;
import winevault.util.IConnectionData;


public class WineDAOTest {
	@Mock
	private IConnectionData connData = mock(ConnectionData.class);
	
	private WineDAO dao = new WineDAO(connData);
	private Connection conn;
	private Statement statement;
	private ResultSet rs;
	
	@Test
	public void testAddWine() {
		Mockito.when(connData.getURL()).thenReturn(
				"jdbc:mysql://localhost/winevaultdbtest?useUnicode=true&characterEncoding=utf-8"
		);
		Mockito.when(connData.getUser()).thenReturn("root");
		Mockito.when(connData.getPassword()).thenReturn("password");
		
		try {
			conn = ConnectionUtil.getConnection(connData);
			statement = conn.createStatement();
			// Empty DB
			String sql = "DELETE FROM wines";
			statement.execute(sql);
			
			IWine wine = new Wine();
			wine.setName("New Wine");
			dao.addWine(wine);
			
			sql = "SELECT * FROM wines";
			rs = statement.executeQuery(sql);
			
			assertEquals(true, rs.next());
			assertEquals(6, rs.getMetaData().getColumnCount());
			assertNotEquals(-1,rs.getInt("id"));
			assertEquals("New Wine", rs.getString("variety"));
			assertEquals(false, rs.next());
			
			sql = "DELETE FROM wines";
			statement.execute(sql);
			
		}catch(Exception e) {
			//e.printStackTrace();
			assertFalse(true);
		}
	}
	
	@Test
	public void testGetWineListEmpty() {
		Mockito.when(connData.getURL()).thenReturn(
				"jdbc:mysql://localhost/winevaultdbtest?useUnicode=true&characterEncoding=utf-8"
		);
		Mockito.when(connData.getUser()).thenReturn("root");
		Mockito.when(connData.getPassword()).thenReturn("password");
		
		try {
			conn = ConnectionUtil.getConnection(connData);
			statement = conn.createStatement();
			
			// Empty DB
			String sql = "DELETE FROM wines";
			statement.execute(sql);
			
			List<IWine> wines = dao.getWineList();
			
			assertEquals(0, wines.size());
			
		}catch(Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testGetWineListNonEmpty() {
		Mockito.when(connData.getURL()).thenReturn(
				"jdbc:mysql://localhost/winevaultdbtest?useUnicode=true&characterEncoding=utf-8"
		);
		Mockito.when(connData.getUser()).thenReturn("root");
		Mockito.when(connData.getPassword()).thenReturn("password");
		
		try {
			conn = ConnectionUtil.getConnection(connData);
			statement = conn.createStatement();
			
			// Empty DB
			String sql = "DELETE FROM wines";
			statement.execute(sql);
			
			// Insert 2 wines
			sql = "INSERT INTO wines (variety) VALUES (\"Wine A\")";
			statement.executeUpdate(sql);
			
			sql = "INSERT INTO wines (variety) VALUES (\"Wine B\")";
			statement.executeUpdate(sql);
			
			List<IWine> wines = dao.getWineList();
			assertEquals(2, wines.size());
			assertEquals("Wine A", wines.get(0).getName());
			assertEquals("Wine B", wines.get(1).getName());
			
			sql = "DELETE FROM wines";
			statement.execute(sql);
			
		}catch(Exception e) {
			//e.printStackTrace();
			assertFalse(true);
		}
	}
	
}
