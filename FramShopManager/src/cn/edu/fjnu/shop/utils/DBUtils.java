package cn.edu.fjnu.shop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author GaoFei
 *
 */
public class DBUtils {

	/*private static final String USER="root";
	private static final String PASSWORD="gf6548914";
	private static final String CONNURL="jdbc:mysql://112.74.77.24/farmshop";*/
	private static Connection connection;
	
	public static Connection getConn(){
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		     
			connection=DataSource.getInstance().getConnection();
		    return connection;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	
	     
		//return null;
	}
	
	public static void closeConn(){
		if(connection!=null){
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
