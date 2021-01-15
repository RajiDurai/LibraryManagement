import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDao {
public static int save(String author,String name,String title,int copies, int branchid){
	int status=0,status1=0;	
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("insert into Books(AuthorName,Title,PublisherName) select ?,?,?  where not exists (SELECT 1 FROM Books WHERE Title =?)");
		System.out.println(ps);
		//ps.setInt(1,1);
		ps.setString(3,name);
		ps.setString(1,author);
		ps.setString(2,title);
		ps.setString(4,title);
		status=ps.executeUpdate();
		System.out.println(status);
		ps.close();		
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static int saveBookCopies(String author,String name,String title,int copies, int branchid){
	int status=0;	
	try{
		Connection con=DB.getConnection();		
		PreparedStatement ps2=con.prepareStatement("SELECT MAX(BookId) AS id FROM Books");
		ResultSet rs = ps2.executeQuery();
		rs.first();
		int lastid = rs.getInt("id");
		System.out.println(lastid);
		PreparedStatement ps1=con.prepareStatement("insert into Book_Copies(NoOfCopies,BookId,BranchId) values(?,?,?)");
		//ps1.setInt(1,1);
		ps1.setInt(1,copies);
		ps1.setInt(2,lastid);
		ps1.setInt(3,branchid);
		status=ps1.executeUpdate();
		ps1.close();
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
}
