package parkingManagement.model;
import parkingManagement.model.UnavailableSpotErrorMsgs;
import parkingManagement.data.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class UnavailableSpot {
 private String parkingName;
 private String spot_no;
 private String type;
 private String from;
 private String to;
 
 public UnavailableSpot()
 {}
 
public UnavailableSpot(String parkingName, String spot_no, String type) {
	super();
	this.parkingName = parkingName;
	this.spot_no = spot_no;
	this.type = type;
}
public String getParkingName() {
	return parkingName;
}
public void setParkingName(String parkingName) {
	this.parkingName = parkingName;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getSpot_no() {
	return spot_no;
}
public void setSpot_no(String spot_no) {
	this.spot_no = spot_no;
}

public String getFrom(){
	return from;
}
public void setFrom(String from){
	this.from = from;
}
public String getTo(){
	return to;
}
public void setTo(String to){
	this.to = to;
}

public void  ValidateSpot(UnavailableSpot unavail,UnavailableSpotErrorMsgs spotNoError){
	spotNoError.setspotNumErrMsg(validateSpotNo(unavail));
}

public String validateSpotNo(UnavailableSpot unavail){
	ParkingspotDao parkDao = new ParkingspotDao();
	String spot_no = unavail.getSpot_no();
	if(spot_no.length()==0){
		return "Enter spot number";
	}
	
	else if(isTextAnInteger(spot_no) == false){
		return "Spot number should be an integer.";
	}
	else if(parkDao.checkParkspotunavail(unavail)){
		return "Spot is Unavailable already";
	}
	else
	{
		return "";
	}
}


public String validateSpotnofordetails(String spot_no)
{
	if(spot_no.length()==0){
		return "Enter spot number";
	}
	
	else if(isTextAnInteger(spot_no) == false){
		return "Spot number should be an integer.";
	}
	else
	{
		return "";
	}	
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

public void ValidateAvailSpot(String from, String to, UnavailableSpotErrorMsgs errorMsgs) {
	errorMsgs.setfromErrMsg(validateFrom(from));
	errorMsgs.settoErrMsg(validateTo(from, to));
	errorMsgs.setErrorMsg();
}

public String validateFromAndToTime(int fromHours, int fromMinutes, int toHours, int toMinutes) {
	
	if (toHours<fromHours) {
		return "End time cannot be earlier than start time";
	}
	else if(toHours==fromHours && toMinutes==fromMinutes){
		return "End time cannot be same as start time";
	}
	return "";
}

private String validateTo(String from, String to) {
	
	if (to.equals("")) 
		return "Please enter end time";
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
	int fromHours,fromMinutes;
	try {
		fromHours = Integer.parseInt(fromTimeArray[0]);
		fromMinutes = Integer.parseInt(fromTimeArray[1]);
	}catch (NumberFormatException e){
		return "";
	}
	
	if(toHours>23){
		return "Please enter end time in format HH:mm. Valid values for HH are 00 to 23";
	}
	if(toMinutes>=60){
		return "Please enter end time in the format HH:mm. Valid values for mm are 00 to 59";
	}
		
	if (toHours<currentHours || (toHours==currentHours && toMinutes<currentMinutes))
		return "End time cannot be earlier than current time";
	String compare = validateFromAndToTime(fromHours, fromMinutes, toHours, toMinutes);
	if(!compare.equals(""))
		return compare;
	
	return "";
}
private String validateFrom(String from) {
	
	if (from.equals("")) 
		return "Please enter start time";
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
		return "Please enter start time in format HH:mm. Valid values for HH are 00 to 23";
	}
	if(fromMinutes>=60){
		return "Please enter start time in format HH:mm. Valid values for mm are 00 to 59";
	}
	if (fromHours<currentHours)
		return "Start time cannot be earlier than current time";
	else if (fromHours==currentHours) {
		if (fromMinutes<currentMinutes)
			return "Start time cannot be earlier than current time";
	}
	return "";
}

public String getCurrentTimeUsingDate() {
    Date date = new Date();
    String strDateFormat = "HH:mm:ss";
    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
    return dateFormat.format(date);
}
}
