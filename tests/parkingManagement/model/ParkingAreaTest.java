package parkingManagement.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ParkingAreaTest {
	
	@Test
	@FileParameters("./junitTestData/ParkingAreaTestData.csv")
	public void ValidateSearchParkingSpotTest(int testno, String parkingarea_name, String parkingtype, 
			String error, String parkingAreaError, String parkingTypeError, String description) {
		
		ParkingArea parkingArea = new ParkingArea();
		parkingArea.setParkingarea_name(parkingarea_name);
		parkingArea.setParkingtype(parkingtype);
		ParkingAreaErrorMsgs errorMsgs = new ParkingAreaErrorMsgs();
		parkingArea.ValidateSearchParkingSpot(errorMsgs);
		assertEquals(parkingAreaError,errorMsgs.getParkingareaNameError());
		assertEquals(error,errorMsgs.getErrormsg());
		assertEquals(parkingTypeError,errorMsgs.getParkingTypeError());
	}
		
	@Test
	@FileParameters("./junitTestData/AddParkingAreaTestData.csv")
	public void validateAddParkingAreaTest(int testno,String parkingAreaname,String Floor,String Capacity,
			String type,String newname,String parkingAreaErr,String FloorErr,String CapacityErr,String errMsg) {
		ParkingArea parkingArea = new ParkingArea();
		ParkingAreaErrorMsgs addparkErr = new ParkingAreaErrorMsgs();
		parkingArea.setParkingarea_name(parkingAreaname);
		parkingArea.setParkingtype(type);
		parkingArea.validateNewParkingArea(parkingArea, addparkErr, Capacity, Floor);
		assertEquals(parkingAreaErr,addparkErr.getParkingareaNameError());
		assertEquals(FloorErr,addparkErr.getFloorError());
		assertEquals(CapacityErr,addparkErr.getCapacityError());
		assertEquals(errMsg,addparkErr.getErrormsg());
		assertEquals(parkingAreaErr, parkingArea.validateParkingNameforChangename(parkingAreaname,addparkErr));
	}
}
