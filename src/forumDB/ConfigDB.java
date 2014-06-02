package forumDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
	public static String dbs = "test";
	public static String dbUser = "root";
	public static String dbPasswd = "13841311895";
	public static Connection conn = null;
	
	public static void setConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=" + dbUser + "&password=" + dbPasswd);
		System.out.println("Connet to the MySql");		
	}
	
	public static void stopConnection() throws SQLException {
		conn.close(); 
	}
}
