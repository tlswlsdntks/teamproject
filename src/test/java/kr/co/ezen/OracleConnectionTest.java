package kr.co.ezen;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;

public class OracleConnectionTest {

	@Inject
	private DataSource dataFactory;
	
	@Test
	public void testConnection() {
		
		Connection conn = null;
		
		try {
			conn=dataFactory.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
}
