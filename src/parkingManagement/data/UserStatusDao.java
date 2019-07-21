package parkingManagement.data;

import parkingManagement.util.SQLConnection;
import parkingManagement.model.User;
import java.sql.*;
import java.util.*;

public class UserStatusDao {
	static SQLConnection sqlconnection = SQLConnection.getInstance();
	Connection conn = SQLConnection.getDBConnection();
	Statement stmt = null;
	
	
	public String getUserStatus(String username)
	{
			String userStatus = "";
		try {
			stmt = conn.createStatement();
			String queryString = "select user_status from `users` where username = '"+username+"'";
			ResultSet rs = stmt.executeQuery(queryString);
				while(rs.next()){
				userStatus = rs.getString("user_status");
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
			};
		}
		return userStatus;
} 
	
	
	public void changeUserStatus(String user_status, String username){
		try {
			stmt = conn.createStatement();
			String queryString ="Update `users` set user_status='"+user_status+"' where `username`='"+username+"'";
			stmt.execute(queryString);
			conn.commit();
				
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
			};
		}
	}
	
		public void resetViolations(String username){
			try {
				stmt = conn.createStatement();
				System.out.println("hi");
				String queryString ="Update `users` set noshows=0, overdue=0 where `username`='"+username+"'";
				stmt.execute(queryString);
				conn.commit();
					
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
				};
			}
		}


}
