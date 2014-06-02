package forumDB;
import java.util.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDB {
	private String userName;
	private String passwd;
	private String email;
	private Date time;
	static Connection conn = ConfigDB.conn;
	
	public UserDB(){
		GregorianCalendar current = new GregorianCalendar();
		time = new Date(current.getTimeInMillis());
	}
	
	public UserDB(String name, String pass, String mail){
		GregorianCalendar current = new GregorianCalendar();
		time = new Date(current.getTimeInMillis());
		userName = name;
		passwd = pass;
		email = mail;
	}
	
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }
	public String getPasswd() { return passwd; }
	public void setPasswd(String passwd) { this.passwd = passwd; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }	
	public Date getTime() { return time; }
	public void setTime(Date time) { this.time = time; }

	public void updateAll() throws SQLException{
		Statement st = conn.createStatement();
		String query = "insert into forumdb.users (user_name, user_pass, user_email, create_date) values ('"+userName+"','"+passwd+"','"+email+"','"+time+"');";
		try{
			st.executeUpdate(query);
			System.out.println("Successful to insert a user !");
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static void changePass(String pass, String name) throws SQLException{
		Statement st = conn.createStatement();
		String query = "update forumdb.users set user_pass = '" + pass + "' where user_name = '" + name + "'";
		try{
			st.execute(query);
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static boolean isSameName(String name) throws SQLException{
		Statement st = ConfigDB.conn.createStatement();
		String query = "select * from forumdb.users";
		boolean flag = false;
		try{			
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				String temp = rs.getString("user_name");
				System.out.println(temp);
				if(temp.equals(name)){
					flag = true;
				}
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return flag;
	}
	
	public static boolean isSamePasswd(String name, String pass) throws SQLException{
		Statement st = ConfigDB.conn.createStatement();
		String query = "select * from forumdb.users where user_name = '" + name +"' and user_pass = '" + pass + "'";
		boolean flag = false;
		try{			
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){				
				flag = true;
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return flag;
	}
	
	public static String getUserNameById(int id) throws SQLException{
		Statement st = ConfigDB.conn.createStatement();
		String query = "select * from forumdb.users where user_id = " + id;
		String name = "";
		try{			
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){				
				name = rs.getString("user_name");
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return name;
	}
	
	public static UserDB getUserByName(String name) throws SQLException{
		Statement st = ConfigDB.conn.createStatement();
		String query = "select * from forumdb.users where user_name = '" + name + "'";
		UserDB temp = null;
		try{			
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){				
				String uname = rs.getString("user_name");
				String pass = rs.getString("user_pass");
				String email = rs.getString("user_email");
				Date datetime = rs.getDate("create_date");
				temp = new UserDB(uname, pass, email);
				temp.setTime(datetime);
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return temp;
	}
	
	public static int getIdByName(String name) throws SQLException{
		Statement st = ConfigDB.conn.createStatement();
		String query = "select * from forumdb.users where user_name = '" + name + "'";
		int temp = 0;
		try{			
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){				
				temp = rs.getInt("user_id");
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return temp;
	}
}
