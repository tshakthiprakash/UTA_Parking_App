package parkingManagement.data;

import parkingManagement.util.SQLConnection;
import parkingManagement.model.User;
import java.sql.*;

public class RegisterUserDao {
	
	static SQLConnection sqlconnection = SQLConnection.getInstance();
	
	public static boolean verifyUniqueUsername(String username)
	{
		Connection conn = null;
		Statement stmt = null;
		conn = SQLConnection.getDBConnection();
		boolean userPresent = false;
		try {
			stmt = conn.createStatement();
			String queryString = "select * from `users` where username ='"+username+"'";
			ResultSet rs = stmt.executeQuery(queryString);
			while (rs.next()) {
				String k = (String) rs.getObject(2);
					userPresent= true;
					break;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {				
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("userPresent is : "+userPresent);
		return userPresent;		
	}

	public void registerUser(User User){
		
		Connection conn = null;
		Statement stmt = null;
		
		conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String queryString = "INSERT INTO `parkingdb`.`users` (`username`,`password`,`firstname`,`lastname`,`email`,`phone`,`utaid`,`number_plate`,`permit_id`,`permit_type`,`address`,`city`,`state`,`user_role`,`zip`) VALUES('"+User.getUsername()+"','"+User.getPassword()+"','"+User.getFirstname()+"','"+User.getLastname()+"','"+User.getEmail()+"','"+User.getPhone()+"','"+User.getUta_id()+"','"+User.getCar_plate_num()+"','"+User.getPermit_id()+"','"+User.getPermit_type()+"','"+User.getStreet_add()+"','"+User.getCity()+"','"+User.getState()+"','"+User.getRole()+"','"+User.getZip_code()+"')";
			stmt.executeUpdate(queryString);
			conn.commit();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null)
					conn.close();
				if(stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
}
