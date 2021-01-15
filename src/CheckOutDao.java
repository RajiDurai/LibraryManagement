import java.sql.*;
public class CheckOutDao {
	
public static boolean checkBook(int bookId){
	boolean status=false;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from Book_Copies where BookId=?");
		ps.setInt(1,bookId);
	    ResultSet rs=ps.executeQuery();
		status=rs.next();
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}

public static int save(int bookId,int branchId,String cardNo){
	int status=0;
	try{
		Connection con=DB.getConnection();
		
		status=updatebook(bookId);//updating NoOfCopies and issue
		
		if(status>0){
		PreparedStatement ps=con.prepareStatement("insert into Book_Loans(BookId,BranchId,CardNo) values(?,?,?)");
		ps.setInt(1,bookId);
		ps.setInt(2,branchId);
		ps.setString(3,cardNo);
		//ps.setString(4,borrowerName);
		status=ps.executeUpdate();
		System.out.println("upd"+status);
		}
		
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static int updatebook(int bookId){
	int status=0;
	int NoOfCopies=0;
	try{
		Connection con=DB.getConnection();
		
		PreparedStatement ps=con.prepareStatement("select NoOfCopies from Book_Copies where BookId=?");
		ps.setInt(1,bookId);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			NoOfCopies=rs.getInt("NoOfCopies");
			
		}
		
		if(NoOfCopies>0){
		PreparedStatement ps2=con.prepareStatement("update Book_Copies set NoOfCopies=? where BookId=?");
		ps2.setInt(1,NoOfCopies-1);
		ps2.setInt(2,bookId);
		
		status=ps2.executeUpdate();
		System.out.println(status);
		}
		con.close();
	}catch(Exception e){System.out.println(e);}
	System.out.println(status);
	return status;
}
}
