package parkingManagement.model;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import parkingManagement.data.ReservationDao;

import java.util.Date;

public class Reservation {
	
	int reservation_id;
	int parkingarea_id;
	String username;
	java.sql.Date reservation_date;	
	Time from_time;
	Time to_time;
	int parkingslot_no;
	
	@Override
	public String toString() {
		return "Reservation [reservation_id=" + reservation_id + ", parkingarea_id=" + parkingarea_id + ", username="
				+ username + ", reservation_date=" + reservation_date + ", from_time=" + from_time + ", to_time=" + to_time
				+ ", parkingslot_no=" + parkingslot_no + ", cart=" + cart + ", camera=" + camera + ", history="
				+ history + "]";
	}
	boolean cart;
	boolean camera;
	boolean history;
	
	public Reservation(){}
	
	public Reservation(int reservation_id, int parkingarea_id, String username, java.sql.Date reservation_date, Time from_time,
			Time to_time, int parkingslot_no, boolean cart, boolean camera, boolean history) {
		super();
		this.reservation_id = reservation_id;
		this.parkingarea_id = parkingarea_id;
		this.username = username;
		this.reservation_date = reservation_date;
		this.from_time = from_time;
		this.to_time = to_time;
		this.parkingslot_no = parkingslot_no;
		this.cart = cart;
		this.camera = camera;
		this.history = history;
	}
	
	public java.sql.Date getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(java.sql.Date reservation_date) {
		this.reservation_date = reservation_date;
	}
	public void setFrom_time(Time from_time) {
		this.from_time = from_time;
	}
	public void setTo_time(Time to_time) {
		this.to_time = to_time;
	}
	public Time getFrom_time() {
		return from_time;
	}
	public Time getTo_time() {
		return to_time;
	}	
	public boolean isCart() {
		return cart;
	}
	public void setCart(boolean cart) {
		this.cart = cart;
	}
	public boolean isCamera() {
		return camera;
	}
	public void setCamera(boolean camera) {
		this.camera = camera;
	}
	public boolean isHistory() {
		return history;
	}
	public void setHistory(boolean history) {
		this.history = history;
	}
	public int getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}
	public int getParkingarea_id() {
		return parkingarea_id;
	}
	public void setParkingarea_id(int parkingarea_id) {
		this.parkingarea_id = parkingarea_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getParkingslot_no() {
		return parkingslot_no;
	}
	public void setParkingslot_no(int parkingslot_no) {
		this.parkingslot_no = parkingslot_no;
	}

	public void validateReservedCount(ReservationErrorMsgs errorMsgs,
			String username) {
		errorMsgs.setReservedCountErrorMsg(validate(username));
		errorMsgs.setReservationErrormsg();
	}
	public String validate(String username) {
		ReservationDao reservationDao = new ReservationDao();
		int reservationsCount = reservationDao.getReservationCount(username);
		if(reservationsCount>=3)
			return "You cannot have more than three slots reserved. Delete one of your reserved slots to reserve a new slot.";
		return "";
	}
	
	public void ValidateSearchParkingSpotTimings(ReservationErrorMsgs errorMsgs,
			String from, String to) {
		errorMsgs.setReservationFromError(validateReservationFrom(from));
		errorMsgs.setReservationToError(validateReservationTo(from, to));
		
		errorMsgs.setErrormsg();
	}
	
	public String validateFromAndToTime(int fromHours, int toHours, int toMinutes) {
		
		if (toHours<fromHours) {
			return "End time cannot be earlier than from_time time. Please correct it";
		} else if (toHours==fromHours && toMinutes==0) {
				return "Reservation cannot be made for less than 15 minutes. Please correct it";
		} else if((toHours-fromHours == 3 && toMinutes!=0) || toHours-fromHours > 3) {
				return "Reservation cannot be made for more than 3 hours. Please correct it";
		}
		return "";
	}
	
	private String validateReservationTo(String from, String to) {
		
		if (to.equals("")) 
			return "Please enter reservation end time";
		if (from.equals("")) 
			return "";
		String currentTime = getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		int currentHours = Integer.parseInt(currentTimeArray[0]);
		int currentMinutes = Integer.parseInt(currentTimeArray[1]);
		
		int toHours;
		int toMinutes;
	
		String[] toArray = to.split(":");
		if(!(toArray[0].length()==2))
			return "Please enter time in the format HH:mm";
		if(!(toArray[1].length()==2))
			return "Please enter time in the format HH:mm";
		try {
			toHours = Integer.parseInt(toArray[0]);
			toMinutes = Integer.parseInt(toArray[1]);
		}catch (NumberFormatException e){
			return "Please enter time in format HH:mm";
		}
		
		String[] fromTimeArray = from.split(":");
		if(!(fromTimeArray[0].length()==2))
				return "";
		int fromHours;
		try {
			fromHours = Integer.parseInt(fromTimeArray[0]);
		}catch (NumberFormatException e){
			return "";
		}
		
		if(toHours>23){
			return "Please enter time in format HH:mm. HH from_time 00 to_time 23";
		} else if(toHours == 23 && toMinutes > 45)
			return "Please enter time in format HH:mm. Valid values for mm are 00 or 15 or 30 or 45";
		if(!(toMinutes == 0 || toMinutes==15 || toMinutes==30 || toMinutes== 45)){
			return "Please enter time in the format HH:mm. Valid values for mm are 00 or 15 or 30 or 45";
		}
		if (toHours<currentHours || (toHours==currentHours && toMinutes<currentMinutes))
			return "End time cannot be earlier than current time. Please correct it";
		String compare = validateFromAndToTime(fromHours, toHours, toMinutes);
		if(!compare.equals(""))
			return compare;
		
		return "";
	}
	private String validateReservationFrom(String from) {
		
		if (from.equals("")) 
			return "Please enter reservation start time";
		String currentTime = getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		int currentHours = Integer.parseInt(currentTimeArray[0]);
		int currentMinutes = Integer.parseInt(currentTimeArray[1]);
		
		String[] fromArray = from.split(":");
		
		if(!(fromArray[0].length()==2))
			return "Please enter time in the format HH:mm";
		if(!(fromArray[1].length()==2))
			return "Please enter time in the format HH:mm";
		int fromHours;
		int fromMinutes;
		
		try {
			fromHours = Integer.parseInt(fromArray[0]);
			fromMinutes = Integer.parseInt(fromArray[1]);
		}catch (NumberFormatException e){
			return "Please enter time in format HH:mm";
		}
		
		if(fromHours>23){
			return "Please enter start time in format HH:00. HH from_time 00 to_time 23";
		}
		if(fromMinutes != 0){
			return "Please enter start time in format HH:00. HH from_time 00 to_time 23";
		}
		if (fromHours<currentHours)
			return "Start time cannot be earlier than current time. Please correct it";
		else if (fromHours==currentHours) {
			if (fromMinutes<currentMinutes)
				return "Start time cannot be earlier than current time. Please correct it";
		}
		return "";
	}
	
	String getCurrentTimeUsingDate() {
	    Date date = new Date();
	    String strDateFormat = "HH:mm:ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    return dateFormat.format(date);
	}
}
