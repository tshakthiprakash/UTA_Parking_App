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
public class ReservationTest {
	
	@Test
	@FileParameters("./junitTestData/ReservationTestData.csv")
	public void ValidateSearchParkingSpotTest(int testno, String from, String to, String currentTime, 
			String error, String reservationFromError, String reservationToError, String description) {
		
		Reservation mock = EasyMock.createMockBuilder(Reservation.class)
		        .withConstructor()
		        .addMockedMethod("getCurrentTimeUsingDate")
		        .createMock();
		EasyMock.expect(mock.getCurrentTimeUsingDate()).andStubReturn(currentTime);
		EasyMock.replay(mock);
		ReservationErrorMsgs errorMsgs = new ReservationErrorMsgs();
		mock.ValidateSearchParkingSpotTimings(errorMsgs, from, to);
		assertEquals(error,errorMsgs.getErrormsg());
		assertEquals(reservationFromError,errorMsgs.getReservationFromError());
		assertEquals(reservationToError,errorMsgs.getReservationToError());
	}
		
	@Test
	public void getCurrentTimeUsingDateTest() {
		
		Reservation reservation = new Reservation();
		String currentTime = reservation.getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		
		Date date = new Date();
	    String strDateFormat = "HH:mm:ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String testTime = dateFormat.format(date);
	    
	    String[] testTimeArray = testTime.split(":");
	    
		assertEquals(Integer.parseInt(testTimeArray[0]),Integer.parseInt(currentTimeArray[0]));
	}
	
	@Test
	@FileParameters("./junitTestData/ReserveTestData.csv")
	public void validateReservationTest(String tcno, String username, String error, String resError	)
		{
		Reservation reservation = new Reservation();
		ReservationErrorMsgs ResErr = new ReservationErrorMsgs();
		reservation.validateReservedCount(ResErr,username);
		assertEquals(error, ResErr.getReservedCountErrorMsg());	
		assertEquals(resError, ResErr.getReservationErrormsg());	
	}
}
