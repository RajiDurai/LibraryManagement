import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	public static Connection getConnection(){
		Connection con=null;
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager
	                   .getConnection("jdbc:mysql://localhost:57858/Library?"
	                 	+ " verifyServerCertificate=false&useSSL=true&"
	                   + "user=msandbox&password=Raji@2018&"
	             	+ "serverTimezone=UTC"); 
		}catch(Exception e){System.out.println(e);}
		return con;
	}

}
