package parkingManagement.model;

import java.io.Serializable;

import parkingManagement.data.ParkingspotDao;

public class ParkingArea implements Serializable {
	
	
	public ParkingArea() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkingArea(int parkingarea_id, String parkingarea_name, String parkingtype, String capacity, String floor) {
		this.parkingarea_id = parkingarea_id;
		this.parkingarea_name = parkingarea_name;
		this.parkingtype = parkingtype;
		this.capacity = capacity;
		this.floor = floor;
	}

	private static final long serialVersionUID = 1L;
	int parkingarea_id;
	String parkingarea_name;
	String parkingtype;
	String capacity;
	String floor;
	
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

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public void ValidateSearchParkingSpot(ParkingAreaErrorMsgs errorMsgs) {
		errorMsgs.setParkingareaNameError(validateParkingArea(getParkingarea_name()));
		errorMsgs.setParkingTypeError(validateParkingType(getParkingtype()));
		
		errorMsgs.setErrormsg();
	}
	
	public String validateParkingArea(String parkingArea) {
		if (parkingArea.equals("Select")) 
			return "Please select a parking area";
		return "";
	}
	
	public String validateParkingType(String parkingType) {
		
		if (parkingType.equals("Select")) 
			return "Please select a parking type";
		return "";
	}
	
	public void validateNewParkingArea(ParkingArea addpark,ParkingAreaErrorMsgs addparkerror, String capacity, String floor)
	 {
		 addparkerror.setParkingareaNameError(validateParkingName(addpark.getParkingarea_name(),
				 addpark.getParkingtype(), floor));
		 addparkerror.setCapacityError(validateCapacity(capacity));
		 addparkerror.setFloorError(validateFloor(floor));
		 addparkerror.setErrormsg();
	}
	 
	 
	public String validateParkingName(String parkname, String type, String Floor){
		boolean hasChar = false;
		boolean hasNumber = false;
		ParkingspotDao check = new ParkingspotDao();
		String result="";
		char[] array=parkname.toCharArray();
		if (parkname.equals(""))
			result= "Parking Area Name should not be empty";
		else if (check.finduniqueparkname(parkname, type, Floor))
					result="Parking Area Name alredy found";
		else if(!stringSize(parkname,3,16))
			result ="Parking Area name should be between 3 and 16 characters in length";
		char[] characters = {'~', '!', '@', '#','$','%','^','&','*','(',')','_','-','+','=','{','}','[',']',':',';','"','<','>','?','/','\\'};
		for(int i=0;i<characters.length;i++) {
			char a = characters[i];
			for(char b: array) {
				if (a == b){
					hasChar = true;
				}
				if(Character.isDigit(b)) {
					hasNumber = true;
				}
			}
		}
		if(hasChar || hasNumber) {
			result = "Parking area name cannot contain special characters or numeric characters";
		}
		return result;
	 }
	public String validateParkingNameforChangename(String parkname,ParkingAreaErrorMsgs addparkerror){
		boolean hasChar = false;
		boolean hasNumber = false;
		ParkingspotDao check = new ParkingspotDao();
		String result="";
		char[] array=parkname.toCharArray();
		if (parkname.equals(""))
			result= "Parking Area Name should not be empty";
		else if (check.finduniqueparknameforchangename(parkname))
					result="Parking Area Name alredy found";
		else if(!stringSize(parkname,3,16))
			result ="Parking Area name should be between 3 and 16 characters in length";
		char[] characters = {'~', '!', '@', '#','$','%','^','&','*','(',')','_','-','+','=','{','}','[',']',':',';','"','<','>','?','/','\\'};
		for(int i=0;i<characters.length;i++) {
			char a = characters[i];
			for(char b: array) {
				if (a == b){
					hasChar = true;
				}
				if(Character.isDigit(b)) {
					hasNumber = true;
				}
			}
		}
		if(hasChar || hasNumber) {
			result = "Parking area name cannot contain special characters or numeric characters";
		}
		//if(!(result.equals("")))
		addparkerror.setParkingareaNameError(result);
		return result;
	}
	 private String validateFloor(String Floor) {
			String result="";
			if(isTextAnInteger(Floor) == false) {
				result = "Please Enter a valid Floor number";
			}
			
			return result;
		}
	 
	 public String validateCapacity(String capacity) {
			String result="";
			if(isTextAnInteger(capacity) == false) {
				result = "Please Enter a valid Capacity";
			}
			
			return result;
		}
	 private boolean stringSize(String string, int min, int max) {
			return string.length()>=min && string.length()<=max;
		}
	 private boolean isTextAnInteger (String string) {
	        boolean result;
			try
	        {
	            Long.parseLong(string);
	            result=true;
	        } 
	        catch (NumberFormatException e) 
	        {
	            result=false;
	        }
			return result;
		}
}
