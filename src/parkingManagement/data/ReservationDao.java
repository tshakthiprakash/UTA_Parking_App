package parkingManagement.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parkingManagement.model.Reservation;
import parkingManagement.util.SQLConnection;

public class ReservationDao {
	
	static SQLConnection sqlconnection = SQLConnection.getInstance();
	private final String SQL_RESERVE_PARKING_SLOT= "INSERT INTO reservation (parkingarea_id, username, cart, camera, history, from_time, to_time, parkingslot_no, reservation_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public Map<Integer, Integer> getParkingAreaCountList(ArrayList<Integer> parkingAreaIdList, Time from, Time to, Date today) {
		
		Map<Integer, Integer> reservationcountMap = new HashMap<Integer, Integer>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		
	try {
		stmt = conn.createStatement();
		String queryString = null;
		
		ResultSet reservationCount = null;
		for(int id : parkingAreaIdList) {
			queryString = "select count(*) AS count from reservation where (parkingarea_id=" + id + ") and (from_time='" + from + "' or (from_time<'" +from+ "'and from_time<'" +to+ "'and to_time>'" +to+ "') or (from_time<'" +from+ "' and to_time='" +to+ "') or (from_time>'" +from+ "'and to_time>'" +to+ "' and from_time<'" +to+ "') or (from_time<'" +from+ "' and to_time>'"+ from +"' and to_time<'" +to+ "')) and (reservation_date='"+today+"')";
			System.out.println("Query is : "+queryString);
			reservationCount = stmt.executeQuery(queryString);
			while(reservationCount.next())
				reservationcountMap.put(id, reservationCount.getInt("count"));
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
	return reservationcountMap;

	}

	public void reserveParkingSlot(Reservation reservation) {
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
			String queryString = "";
			int lastParkingSlotNo = 0;
			int slotnoForReservation = 0;
			int[] list = new int[100];
	
			try {
				stmt = conn.createStatement();
				queryString = "select spot_no from unavailablespots where (parking_id=" + reservation.getParkingarea_id() + ") ORDER BY spot_no ASC";
				System.out.println("Query is : "+queryString);
				ResultSet rs = stmt.executeQuery(queryString);
				int i=0;
				while(rs.next()) 
					list[i++] = rs.getInt("spot_no");
				queryString = "select MAX(parkingslot_no) AS lastParkingSlotNo from reservation where (parkingArea_id=" + reservation.getParkingarea_id() + ") and (reservation_date='"+reservation.getReservation_date()+"')";
				System.out.println("Query is : "+queryString);
				rs = stmt.executeQuery(queryString);
				while(rs.next())
					lastParkingSlotNo = rs.getInt("lastParkingSlotNo");
				
				slotnoForReservation = lastParkingSlotNo+1;
				
			} catch (Exception e) {
				System.out.println("Exception is "+e);
			}
			try (PreparedStatement pstmt = conn.prepareStatement(SQL_RESERVE_PARKING_SLOT)) {
				
	            pstmt.setInt(1, reservation.getParkingarea_id());
	            pstmt.setString(2, reservation.getUsername());
	            pstmt.setBoolean(3, reservation.isCart());
	            pstmt.setBoolean(4, reservation.isCamera());
	            pstmt.setBoolean(5, reservation.isHistory());
	            pstmt.setTime(6, reservation.getFrom_time());
	            pstmt.setTime(7, reservation.getTo_time() );
	            pstmt.setInt(8, slotnoForReservation);
	            pstmt.setDate(9, reservation.getReservation_date());
	            System.out.println("The query is: "+pstmt.toString());
	            pstmt.executeUpdate();
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

	

	public int getReservationCount(String username) {
		int count = 0;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		java.util.Date utilDate = new java.util.Date();
		Date today = new Date(utilDate.getTime());
		
		try {
			stmt = conn.createStatement();
			String queryString = null;
			
			ResultSet reservationCount = null;
			queryString = "select count(*) AS count from reservation where (username='" + username + "') and (reservation_date='"+today+"')";
			System.out.println("Query is : "+queryString);
			reservationCount = stmt.executeQuery(queryString);
			while(reservationCount.next())
				count = reservationCount.getInt("count");
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
	return count;
	}
}
