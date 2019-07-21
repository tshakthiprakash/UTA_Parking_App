package parkingManagement.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UnavailableSpotTest {
	@Test
	@FileParameters("./junitTestData/MakeSpotUnavailableTestData.csv")
	public void validateMakeSpotUnavailable(int testno, String parkingName, String parkingType, String spotNum,
			String spotNumError, String comments)
	{
		UnavailableSpotErrorMsgs errorMsgs = new UnavailableSpotErrorMsgs();
		UnavailableSpot unSpotTest1 = new UnavailableSpot(parkingName, spotNum, parkingType);
		unSpotTest1.ValidateSpot(unSpotTest1,errorMsgs);
		assertEquals(spotNumError,errorMsgs.getspotNumErrMsg());
	}
	
	@Test
	@FileParameters("./junitTestData/SpecificSpotDetailsTestData.csv")
	public void validateSpotNumforSpecificSpotDetails(int testno, String parkingName, String parkingType, String spotNum,
			String spotNumError, String comments)
	{
		UnavailableSpot unSpotTest3 = new UnavailableSpot(parkingName, spotNum, parkingType);
		assertEquals(spotNumError,unSpotTest3.validateSpotnofordetails(spotNum));
	}
	
	
	@Test
	@FileParameters("./junitTestData/ViewAvailableSpotsTestData.csv")
	public void ValidateViewAvailableSpotsTest(int testno, String parkingarea_name, String parkingtype, 
			String from, String to, String currentTime, String error, 
			String fromError, String toError, String comments) {
		
		UnavailableSpot mock = EasyMock.createMockBuilder(UnavailableSpot.class)
		        .withConstructor()
		        .addMockedMethod("getCurrentTimeUsingDate")
		        .createMock();
		EasyMock.expect(mock.getCurrentTimeUsingDate()).andStubReturn(currentTime);
		EasyMock.replay(mock);
		UnavailableSpotErrorMsgs availspotserrMsgs = new UnavailableSpotErrorMsgs();
		
		mock.ValidateAvailSpot(from, to, availspotserrMsgs);
		assertEquals(error,availspotserrMsgs.getErrorMsg());
		assertEquals(fromError,availspotserrMsgs.getfromErrMsg());
		assertEquals(toError,availspotserrMsgs.gettoErrMsg());
	}
	
	@Test
	public void getCurrentTimeUsingDateTest() {
		
		UnavailableSpot unspot = new UnavailableSpot();
		String currentTime = unspot.getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		
		Date date = new Date();
	    String strDateFormat = "HH:mm:ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String testTime = dateFormat.format(date);
	    
	    String[] testTimeArray = testTime.split(":");
	    
		assertEquals(Integer.parseInt(testTimeArray[0]),Integer.parseInt(currentTimeArray[0]));
	}
}