package parkingManagement.model;

public class ReservationErrorMsgs {
	String reservedCountErrorMsg ="";
	String reservationErrormsg="";
	private String reservationFromError="";
	private String reservationToError="";
	private String errormsg="";
	
	
	public String getReservedCountErrorMsg() {
		return reservedCountErrorMsg;
	}
	public void setReservedCountErrorMsg(String reservedCountErrorMsg) {
		this.reservedCountErrorMsg = reservedCountErrorMsg;
	}
	public String getReservationFromError() {
		return reservationFromError;
	}

	public void setReservationFromError(String reservationFromError) {
		this.reservationFromError = reservationFromError;
	}

	public String getReservationToError() {
		return reservationToError;
	}

	public void setReservationToError(String reservationToError) {
		this.reservationToError = reservationToError;
	}
	public String getReservationErrormsg() {
		return reservationErrormsg;
	}
	public void setReservationErrormsg() {
		if(!reservedCountErrorMsg.equals(""))
			reservationErrormsg = "STOP!!";
		else
			reservationErrormsg = "";
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg() {
		if(!reservationToError.equals("") || !reservationFromError.equals(""))
			errormsg = "Please correct the following errors";
		else
			errormsg = "";
			
	}
}
