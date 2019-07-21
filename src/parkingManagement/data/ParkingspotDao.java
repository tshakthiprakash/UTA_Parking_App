package parkingManagement.data;

import java.sql.Connection;

import parkingManagement.model.UnavailableSpot;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import parkingManagement.model.ParkingArea;
import parkingManagement.model.*;
import parkingManagement.util.*;

public class ParkingspotDao {
	
	static SQLConnection sqlconnection = SQLConnection.getInstance();

	public ArrayList<ParkingArea> getParkingAreaList(ParkingArea parkingArea) {
		ArrayList<ParkingArea> parkingList = new ArrayList<ParkingArea>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = null;
		queryString = "select * from parkingarea where parkingarea_name='" +parkingArea.getParkingarea_name()+ "' and"
				+ " parkingtype = '" +parkingArea.getParkingtype() + "' ";
		
		System.out.println("query is : "+queryString);
		ResultSet parkingResultSet = stmt.executeQuery(queryString);
			
		while (parkingResultSet.next()) {
			ParkingArea parkingAreaFromdb = new ParkingArea(); 
			parkingAreaFromdb.setParkingarea_id(parkingResultSet.getInt("parkingarea_id"));
			parkingAreaFromdb.setParkingarea_name(parkingResultSet.getString("parkingarea_name"));
			parkingAreaFromdb.setParkingtype(parkingResultSet.getString("parkingtype"));
			parkingAreaFromdb.setCapacity(parkingResultSet.getString("capacity"));
			parkingAreaFromdb.setFloor(parkingResultSet.getString("floor")); 
			parkingList.add(parkingAreaFromdb);	
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return parkingList;

	}
	public ArrayList<String> getparkingareaname()
	{
		ArrayList<String> parkingAreaName = new ArrayList<String>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "select distinct parkingarea_name from `parkingarea`";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
			String name = rs.getString("parkingarea_name");
			parkingAreaName.add(name);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
		
		return parkingAreaName;
		
	}
	
	public int number_avail_spot(String parkingareaname,String fromTime, String toTime, String permit_type){
		int count=0;
		int capacity=0;
		String parkingarea_id="";
		Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String today = dateFormat.format(calendar.getTime()).toString();
	    Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "select * from `parkingarea` where `parkingarea_name`='"+parkingareaname+"' and `parkingtype`='"+permit_type+"'";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
			parkingarea_id = rs.getString("parkingarea_id");
			capacity = Integer.parseInt(rs.getString("capacity")); 
		}
		String queryString2 = "select * from `reservation` where `parkingarea_id`='"+parkingarea_id+"' and `reservation_date`='"+today+"' and `from_time` >='"+fromTime+"' and `to_time`<='"+toTime+"'";
		ResultSet re = stmt.executeQuery(queryString2);
		String query2 = "select * from `unavailablespots` where `parking_id` ="+parkingarea_id;
		ResultSet rf = stmt.executeQuery(query2);
		while(rf.next()){
			count=count+1;
		}
		System.out.println(count);
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
		System.out.println(capacity);
		count = capacity-count;
		return count;
		
	}
	
	public void addParkingArea(ParkingArea newpark){
		 Statement stmt = null;
		 String capacity = newpark.getCapacity();
		 String floor = newpark.getFloor();
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String queryString = "INSERT INTO `parkingarea` (`parkingarea_name`, `floor`, `capacity`, `parkingtype`) VALUES ('"
					+ ""+newpark.getParkingarea_name()+"','"+floor+"', '"+capacity+"', '"+newpark.getParkingtype()+"')";
			stmt.execute(queryString);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	public ArrayList<ParkingArea> fetch_parking_details(String parkingname)
	{
		
		ArrayList<ParkingArea> parkarealist = new ArrayList<ParkingArea>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "select * from `parkingarea` where `parkingarea_name`='"+parkingname+"'";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
			ParkingArea parkarea = new ParkingArea();
			parkarea.setParkingarea_name(parkingname);
			parkarea.setFloor(rs.getString("floor"));
			parkarea.setParkingtype(rs.getString("parkingtype"));
			parkarea.setCapacity(rs.getString("capacity"));
			parkarealist.add(parkarea);
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
		
		
		return parkarealist; 
	}
	 public boolean finduniqueparkname(String parkname, String type, String floor){
		 boolean flag = false;
				Statement stmt = null;
				Connection conn = SQLConnection.getDBConnection();  
			try {
				stmt = conn.createStatement();
				String queryString = "select * from `parkingarea` where `parkingarea_name`='"+parkname+"'and `floor`='"+floor+"' and `parkingtype`='"+type+"'";
				ResultSet rs = stmt.executeQuery(queryString);
				while(rs.next())
				{
					flag= true;
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null)
						conn.close();
					if(stmt!=null)
						stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				};
			}
		 return flag;
	 }
	 public boolean finduniqueparknameforchangename(String parkname){
		 boolean flag = false;
				Statement stmt = null;
				Connection conn = SQLConnection.getDBConnection();  
			try {
				stmt = conn.createStatement();
				String queryString = "select * from `parkingarea` where `parkingarea_name`='"+parkname+"'";
				ResultSet rs = stmt.executeQuery(queryString);
				while(rs.next())
				{
					flag= true;
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!=null)
						conn.close();
					if(stmt!=null)
						stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				};
			}
		 return flag;
	 }
	
	public void updateParkarea(ParkingArea parkarea){
		Statement stmt = null;
		System.out.println("updateDao");
		String floor = parkarea.getFloor();
		String capacity = parkarea.getCapacity();
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "UPDATE `parkingarea` SET `capacity` = "+capacity+" WHERE `parkingarea_name`='"
				+ ""+parkarea.getParkingarea_name()+"' and `parkingtype`='"+parkarea.getParkingtype()+"' and `floor` ="+floor;
		stmt.execute(queryString);
		conn.commit();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
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
	public void updateName(String oldname, String newname)
	{
		Statement stmt = null;
		Statement stmt2 = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		stmt2 = conn.createStatement(); 
		String query = "select * from `parkingarea` where `parkingarea_name` ='"+oldname +"'";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
		String queryString = "UPDATE `parkingarea` SET `parkingarea_name` ='"+newname+"' WHERE `parkingarea_id`='"+rs.getString("parkingarea_id")+"'";
		stmt2.execute(queryString);
		conn.commit();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
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
	
	public void setParkspotunavail(UnavailableSpot unavailspot)
	{
		
		Statement stmt = null;
		String parkingarea_id="";
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "select * from `parkingarea` where `parkingarea_name`='"+unavailspot.getParkingName()+"' and `parkingtype`='"+unavailspot.getType()+"'";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
			parkingarea_id = rs.getString("parkingarea_id"); 
		}
		String query2 = "INSERT INTO `unavailablespots`(`parking_id`,`spot_no`) VALUES ('"+parkingarea_id+"',"+unavailspot.getSpot_no()+")";
		stmt.execute(query2);
		conn.commit();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
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
	
	public boolean checkParkspotunavail(UnavailableSpot unavailspot)
	{
		boolean flag=false;
		Statement stmt = null;
		Statement stmt2 = null;
		String parkingarea_id="";
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		stmt2 = conn.createStatement();
		String queryString = "select * from `parkingarea` where `parkingarea_name`='"+unavailspot.getParkingName()+"' and `parkingtype`='"+unavailspot.getType()+"'";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
			parkingarea_id = rs.getString("parkingarea_id"); 
			String query2 = "select * from `unavailablespots` where `parking_id`="+rs.getString("parkingarea_id")+" and `spot_no`="+unavailspot.getSpot_no();
			ResultSet re = stmt2.executeQuery(query2);
			while(re.next())
			{
				flag=true;
				break;
			}
			if(flag)
				break;
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}	
	return flag;	
	}
 
	public Map<Integer, Integer> getUnAvailableParkingsCountList(ArrayList<Integer> parkingAreaIdList) {
		
		Map<Integer, Integer> unAvailParkingscountMap = new HashMap<Integer, Integer>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		
	try {
		stmt = conn.createStatement();
		String queryString = null;
		
		ResultSet unAvailParkingsCount = null;
		for(int id : parkingAreaIdList) {
			queryString = "select count(*) AS count from unavailablespots where (parking_id=" + id + ")";
			System.out.println("Query is : "+queryString);
			unAvailParkingsCount = stmt.executeQuery(queryString);
			if(unAvailParkingsCount.next())
				unAvailParkingscountMap.put(id, unAvailParkingsCount.getInt("count"));
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return unAvailParkingscountMap;

	}
	
	public int fetch_floor_details(String parkname,String type){
		int floor_no =0;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "select * from `parkingarea` where `parkingarea_name`='"+parkname+"' and `parkingtype`='"+type+"'";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
		 floor_no = rs.getInt("floor");
		 break;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	System.out.println(floor_no);
		return floor_no;
	}
	public ArrayList<Reservation> fetch_reservation_details(String parkname,String type,int spotno){
		ArrayList<Reservation> reser_list = new ArrayList<Reservation>();
		Statement stmt = null;
		Statement stmt2 = null;
		Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String today = dateFormat.format(calendar.getTime()).toString();
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		stmt2 = conn.createStatement();
		String queryString = "select * from `parkingarea` where `parkingarea_name`='"+parkname+"' and `parkingtype`='"+type+"'";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
		 String query2 ="select * from `reservation` where `parkingarea_id`="+rs.getInt("parkingarea_id")+" and `parkingslot_no`="+spotno+" and `reservation_date`='"+today+"'";
		 ResultSet re = stmt2.executeQuery(query2);
		 while(re.next()){
		 Reservation resevobj = new Reservation();
		 resevobj.setUsername(re.getString("username"));
		 resevobj.setFrom_time(re.getTime("from_time"));
		 resevobj.setTo_time(re.getTime("to_time"));
		 reser_list.add(resevobj);
		 }
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
		return reser_list;
		
	}
	
	public int check_spot_avail(String parkname,String type, int spotno)
	{
		int flag=0;
		Statement stmt = null;
		Statement stmt2 = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		stmt2 = conn.createStatement();
		String queryString = "select * from `parkingarea` where `parkingarea_name`='"+parkname+"' and `parkingtype`='"+type+"'";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()){
			String query2 = "select * from `unavailablespots` where `parking_id`="+rs.getString("parkingarea_id")+" and `spot_no`="+spotno;
			ResultSet re = stmt2.executeQuery(query2);
			while(re.next())
			{
				flag=1;
			}
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
		return flag;
	}
}
