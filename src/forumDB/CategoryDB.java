package forumDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryDB {
	private String catName;
	private String catDesc;
	private int catId;
	static Connection conn = ConfigDB.conn;
	
	public CategoryDB(String name, String desc){
		catName = name;
		catDesc = desc;
	}
	
	public String getCatName() { return catName; }
	public void setCatName(String catName) { this.catName = catName; }
	public String getCatDesc() { return catDesc; }
	public void setCatDesc(String catDesc) { this.catDesc = catDesc; }	
	public int getCatId() { return catId; }
	public void setCatId(int catId) { this.catId = catId; }

	public void updateAll() throws SQLException{
		Statement st = conn.createStatement();
		String query = "insert into forumdb.category (cat_name, cat_description) values ('"+catName+"','"+catDesc+"');";
		try{
			st.executeUpdate(query);
			System.out.println("Successful to insert a category !");
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static void deleteCat(String name) throws SQLException{
		Statement st = conn.createStatement();
		String query = "delete from forumdb.category where cat_name = '" + name + "'";
		try{
			st.executeUpdate(query);
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static ArrayList<CategoryDB> getCatList() throws SQLException{
		ArrayList<CategoryDB> list = new ArrayList<CategoryDB>();
		Statement st = conn.createStatement();
		String query = "select * from forumdb.category";
		ResultSet rs = null;
		CategoryDB temp = null;		
		try{
			rs = st.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("cat_name");
				String des = rs.getString("cat_description");
				int id = rs.getInt("cat_id");
				temp = new CategoryDB(name, des);
				temp.setCatId(id);
				list.add(temp);
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}		
		
		return list;
	}
}
