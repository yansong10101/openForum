package forumDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PostDB {
	private String postCont;
	private int postTop;
	private int postUser;
	private Date postDate;
	static Connection conn = ConfigDB.conn;
	
	public PostDB(){
		GregorianCalendar current = new GregorianCalendar();
		postDate = new Date(current.getTimeInMillis());
	}
	
	public PostDB(String content, int t, int u){
		GregorianCalendar current = new GregorianCalendar();
		postDate = new Date(current.getTimeInMillis());
		postCont = content;
		postTop = t;
		postUser = u;
	}

	public String getPostCont() { return postCont; }
	public void setPostCont(String postCont) { this.postCont = postCont; }
	public int getPostTop() { return postTop; }
	public void setPostTop(int postTop) { this.postTop = postTop; }
	public int getPostUser() { return postUser; }
	public void setPostUser(int postUser) { this.postUser = postUser; }	
	public Date getPostDate() { return postDate; }
	public void setPostDate(Date postDate) { this.postDate = postDate; }

	public void updateAll() throws SQLException{
		Statement st = conn.createStatement();
		String query = "insert into forumdb.posts (post_content, post_date, post_topic, post_by) values ('"+postCont+"','"+postDate+"','"+postTop+"','"+ postUser +"');";
		try{
			st.executeUpdate(query);
			System.out.println("Successful to insert a post !");
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static void deleteTopic(int id) throws SQLException{
		Statement st = conn.createStatement();
		String query = "delete from forumdb.posts where post_id = " + id;
		try{
			st.executeUpdate(query);
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static ArrayList<PostDB> getPostList(int pid) throws SQLException{
		ArrayList<PostDB> list = new ArrayList<PostDB>();
		Statement st = conn.createStatement();
		String query = "select * from forumdb.posts where post_topic = " + pid;
		ResultSet rs = null;
		PostDB temp = null;		
		try{
			rs = st.executeQuery(query);
			while(rs.next()){
				String postCon = rs.getString("post_content");
				Date postDate = rs.getDate("post_date");
				int topId = rs.getInt("post_topic");
				int postBy = rs.getInt("post_by");
				temp = new PostDB(postCon, topId, postBy);
				temp.setPostDate(postDate);
				list.add(temp);
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}		
		
		return list;
	}
	
	public static int getNumPost(int id) throws SQLException{
		Statement st = conn.createStatement();
		String query = "select count(*) as numtop from forumdb.posts where post_topic = " + id;
		int num = 0;
		ResultSet rs = null;
		try{
			rs = st.executeQuery(query);
			while(rs.next()){
				num = rs.getInt("numtop");
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return num;
	}
	
	public static int getPostTopicId(int id) throws SQLException{
		Statement st = conn.createStatement();
		String query = "select * from forumdb.posts where post_id = " + id;
		int tId = 0;
		ResultSet rs = null;
		try{
			rs = st.executeQuery(query);
			tId = rs.getInt("post_topic");
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return tId;
	}
}
