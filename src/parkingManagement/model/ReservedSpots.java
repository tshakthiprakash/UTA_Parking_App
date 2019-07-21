package parkingManagement.model;

import java.sql.Time;
import java.sql.Date;


public class ReservedSpots {
	
	int reservation_id;
	int parkingarea_id;
	String username;
	Date reservation_date;	
	Time from_time;
	Time to_time;
	int parkingslot_no;
	String cart;
	String camera;
	String history;
	String parkingarea_name;
	String parkingtype;
	int capacity;
	int floor;
	String reservation_status;
	String payment_confirmation;
	public ReservedSpots(int reservation_id, int parkingarea_id, String username, Date reservation_date, Time from_time,
			Time to_time, int parkingslot_no, String cart, String camera, String history, String parkingarea_name,
			String parkingtype, int capacity, int floor, String reservation_status, String payment_confirmation) {
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
		this.parkingarea_name = parkingarea_name;
		this.parkingtype = parkingtype;
		this.capacity = capacity;
		this.floor = floor;
		this.reservation_status = reservation_status;
		this.payment_confirmation = payment_confirmation;
	}
	
	public ReservedSpots(){}
	
	public Date getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(Date reservation_date) {
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
	public String getCart() {
		return cart;
	}
	public void setCart(String cart) {
		this.cart = cart;
	}
	public String getCamera() {
		return camera;
	}
	public void setCamera(String camera) {
		this.camera = camera;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public int getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
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
	
	public int getParkingarea_id() {
		return parkingarea_id;
	}

	public void setParkingarea_id(int parkingarea_id) {
		this.parkingarea_id = parkingarea_id;
	}

	public String getParkingarea_name() {
		return parkingarea_name;
	}

	public void setParkingarea_name(String parkingarea_name) {
		this.parkingarea_name = parkingarea_name;
	}

	public String getParkingtype() {
		return parkingtype;
	}

	public void setParkingtype(String parkingtype) {
		this.parkingtype = parkingtype;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public String getReservation_status() {
		return reservation_status;
	}
	public void setReservation_status(String reservation_status) {
		this.reservation_status = reservation_status;
	}
	
	public String getPayment_confirmation() {
		return payment_confirmation;
	}
	public void setPayment_confirmation(String payment_confirmation) {
		this.payment_confirmation = payment_confirmation;
	}

}
