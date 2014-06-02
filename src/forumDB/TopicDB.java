package forumDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TopicDB {
	private String topicName;
	private String topicCont = "";
	private Date topicDate;
	private int topicCat;
	private int topicUser;
	private int id;
	static Connection conn = ConfigDB.conn;
	
	public TopicDB(){
		GregorianCalendar current = new GregorianCalendar();
		topicDate = new Date(current.getTimeInMillis());
	}
	
	public TopicDB(String name){
		GregorianCalendar current = new GregorianCalendar();
		topicDate = new Date(current.getTimeInMillis());
		topicName = name;
	}
	
	public TopicDB(String name, int c, int u){
		GregorianCalendar current = new GregorianCalendar();
		topicDate = new Date(current.getTimeInMillis());
		topicName = name;
		topicCat = c;
		topicUser = u;
	}

	public String getTopicName() { return topicName; }
	public void setTopicName(String topicName) { this.topicName = topicName; }
	public String getTopicCont() { return topicCont; }
	public void setTopicCont(String topicCont) { this.topicCont = topicCont; }
	public int getTopicCat() { return topicCat; }
	public void setTopicCat(int topicCat) { this.topicCat = topicCat; }
	public int getTopicUser() { return topicUser; }
	public void setTopicUser(int topicUser) { this.topicUser = topicUser; }	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public Date getTopicDate() { return topicDate; }
	public void setTopicDate(Date topicDate) { this.topicDate = topicDate; }

	public void updateAll() throws SQLException{
		Statement st = conn.createStatement();
		String query = "insert into forumdb.topic (topic_subject, topic_content, topic_date, topic_cat, topic_by) values ('"+topicName+"','"+topicCont+"','"+topicDate+"','"+topicCat+"','"+ topicUser +"');";
		try{
			st.executeUpdate(query);
			System.out.println("Successful to insert a topic !");
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static void deleteTopic(String name) throws SQLException{
		Statement st = conn.createStatement();
		String query = "delete from forumdb.topic where topic_subject = '" + name + "'";
		try{
			st.executeUpdate(query);
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static int getNumTopic(int id) throws SQLException{
		Statement st = conn.createStatement();
		String query = "select count(*) as numtop from forumdb.topic where topic_cat = " + id;
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
	
	public static ArrayList<TopicDB> getTopList(int tid) throws SQLException{
		ArrayList<TopicDB> list = new ArrayList<TopicDB>();
		Statement st = conn.createStatement();
		String query = "select * from forumdb.topic where topic_cat = " + tid;
		ResultSet rs = null;
		TopicDB temp = null;		
		try{
			rs = st.executeQuery(query);
			while(rs.next()){
				String topname = rs.getString("topic_subject");
				String cont = rs.getString("topic_content");
				int id = rs.getInt("topic_id");
				int topCat = rs.getInt("topic_cat");
				int topBy = rs.getInt("topic_by");
				temp = new TopicDB(topname, topCat, topBy);
				temp.setTopicCont(cont);	
				temp.setId(id);
				list.add(temp);
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}		
		
		return list;
	}
	
	public static String getTopicTitle(int id) throws SQLException{
		Statement st = conn.createStatement();
		String query = "select * from forumdb.topic where topic_id = " + id;
		String title = "";
		ResultSet rs = null;
		try{
			rs = st.executeQuery(query);
			while(rs.next()){
				title = rs.getString("topic_subject");
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return title;
	}
	
	public static String getTopicDesc(int id) throws SQLException{
		Statement st = conn.createStatement();
		String query = "select * from forumdb.topic where topic_id = " + id;
		String desc = "";
		ResultSet rs = null;
		try{
			rs = st.executeQuery(query);
			while(rs.next()){
				desc = rs.getString("topic_content");
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return desc;
	}
	
	public static int getTopicCatId(int id) throws SQLException{
		Statement st = conn.createStatement();
		String query = "select * from forumdb.topic where topic_id = " + id;
		int tId = 0;
		ResultSet rs = null;
		try{
			rs = st.executeQuery(query);
			while(rs.next()){
				tId = rs.getInt("topic_cat");
			}			
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return tId;
	}
}
