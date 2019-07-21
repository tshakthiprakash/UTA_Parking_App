package parkingManagement.data;

import parkingManagement.util.SQLConnection;
import parkingManagement.model.User;
import java.sql.*;

public class LoginUserDao {
	static SQLConnection sqlconnection = SQLConnection.getInstance();
	Connection conn = SQLConnection.getDBConnection();
	Statement stmt = null;
	public User searchUser(String username)
	{
			User user = new User();
			user.setUsername("");
			user.setPassword("");
			user.setRole("");
		try {
			stmt = conn.createStatement();
			String queryString = "select * from `users` where username = '"+username+"'";
			ResultSet rs = stmt.executeQuery(queryString);
				while(rs.next()){
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("user_role"));
					user.setPermit_type(rs.getString("permit_type"));
					user.setUserStatus(rs.getString("user_status"));
				}
			
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
			};
		}
		return user;
}
}

	
