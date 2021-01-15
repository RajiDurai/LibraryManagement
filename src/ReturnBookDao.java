import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReturnBookDao {
	public static int delete(int BookLoanId, String borrowerName, int bookCopyId){
		int status=0;
		try{
			Connection con=DB.getConnection();
			
			status=updatebook(bookCopyId);//updating quantity and issue
			
			if(status>0){
			PreparedStatement ps=con.prepareStatement("delete from Book_Loans where BookLoanId=?");
			ps.setInt(1,BookLoanId);
			//ps.setString(2,borrowerName);
			status=ps.executeUpdate();
			}
			
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int updatebook(int bookCopyId){
		int status=0;
		int NoOfCopies=0;
		try{
			Connection con=DB.getConnection();
			
			PreparedStatement ps=con.prepareStatement("select NoOfCopies from Book_Copies where BookCopyId=?");
			ps.setInt(1,bookCopyId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				NoOfCopies=rs.getInt("NoOfCopies");
			}
			
			
			PreparedStatement ps2=con.prepareStatement("update Book_Copies set NoOfCopies=? where BookCopyId=?");
			ps2.setInt(1,NoOfCopies+1);
			ps2.setInt(2,bookCopyId);
			
			status=ps2.executeUpdate();
			
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
}
