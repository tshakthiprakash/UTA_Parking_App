package parkingManagement.data;
import parkingManagement.util.SQLConnection;
import parkingManagement.model.User;
import java.sql.*;


public class ProfileDao {
	static SQLConnection sqlconnection = SQLConnection.getInstance();
	Connection conn = SQLConnection.getDBConnection();
	Statement stmt = null;
	public User fetchUserProfile(String username)
	{
			User user = new User("","","","","","","","","","","","","","","","");
			
		try {
			stmt = conn.createStatement();
			String queryString = "select * from `users` where username = '"+username+"'";
			ResultSet rs = stmt.executeQuery(queryString);
				while(rs.next()){
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("user_role"));
					user.setFirstname(rs.getString("firstname"));
					user.setLastname(rs.getString("lastname"));
					user.setEmail(rs.getString("email"));
					user.setUta_id(rs.getString("utaid"));
					user.setPhone(rs.getString("phone"));
					user.setCar_plate_num(rs.getString("number_plate"));
					user.setCity(rs.getString("city"));
					user.setPermit_id(rs.getString("permit_id"));
					user.setPermit_type(rs.getString("permit_type"));
					user.setState(rs.getString("state"));
					user.setStreet_add(rs.getString("address"));
					user.setZip_code(rs.getString("zip"));
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return user;
}
public void updateUser(User user)
{ 	
try {
	stmt = conn.createStatement();
	String queryString = "update `users` set `firstname` ='"+user.getFirstname()+"',"
			+ "`lastname` ='"+user.getLastname()+"',"
			+ "`email` = '"+user.getEmail()+"',"
			+ "`phone`='"+user.getPhone()+"',"
			+ "`city`='"+user.getCity()+"',"
			+ "`state`='"+user.getState()+"',"
			+ "`permit_id`='"+user.getPermit_id()+"',"
			+ "`permit_type`='"+user.getPermit_type()+"',"
			+ "`address`='"+user.getStreet_add()+"',"
			+ "`utaid`='"+user.getUta_id()+"',"
			+ "`number_plate`='"+user.getCar_plate_num()+"',"
			+ "`password`='"+user.getPassword()+"',"
			+ "`zip`='"+user.getZip_code()+"'"
			+ " where `username`='"+user.getUsername()+"'";
	stmt.execute(queryString);
	
} catch (SQLException e) {
	e.printStackTrace();
}
finally {
	try {
		if(conn!=null)
			conn.commit();
			conn.close();
		if(stmt!=null)
			stmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	};
}

}

public User fetchUserViolations(String username)
{
		User violations = new User();
		
	try {
		stmt = conn.createStatement();
		String queryString = "select username,noshows,overdue from `users` where username = '"+username+"'";
		ResultSet rs = stmt.executeQuery(queryString);
			while(rs.next()){
				violations.setUsername(rs.getString("username"));
				violations.setNoshows(rs.getInt("noshows"));
				violations.setOverdue(rs.getInt("overdue"));
			}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return violations;
}

}
