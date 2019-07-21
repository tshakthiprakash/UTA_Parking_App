package selenium;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import parkingManagement.model.*;
import selenium.functions.LoginTestFunctions;
import selenium.functions.PaymentFunctions;
import selenium.functions.RegisterUserFunctions;
import selenium.functions.SearchParkingSpotFunctions;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReserveParkingSpotWithoutOptions extends SeleniumTestBase{
	static RegisterUserFunctions registerUserFunctions;
	static LoginTestFunctions loginTestFunctions;
	static SearchParkingSpotFunctions searchParkingSpotFunctions;
	static PaymentFunctions paymentFunctions;
	static SeleniumTestBase seleniumTestBase;
	
	@BeforeClass
	public static void setUp() throws Exception {
		seleniumTestBase = new SeleniumTestBase();
	    registerUserFunctions = new RegisterUserFunctions();
	    loginTestFunctions = new LoginTestFunctions();
	    searchParkingSpotFunctions = new SearchParkingSpotFunctions();
	    paymentFunctions = new PaymentFunctions();
	    
	    setDriver();
	    
	    driver.findElement(By.xpath(prop.getProperty("Index_Register"))).click();
	    registerAndLogin();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.close();
	}

	@Test
	@FileParameters("./seleniumTestData/ReserveParkingSpotWithoutOptionsTestData.csv")
	public void aReserveParkingWithoutOptionsEndToEndTest(int testno, String parkingarea_name, String parkingtype, 
			String fromTime, String toTime, String error, String parkingAreaError, String parkingTypeError, 
			String fromTimeError, String toTimeError) throws Exception {
		
		
		ReservationErrorMsgs reservationErrorMsgs = new ReservationErrorMsgs();
		reservationErrorMsgs.setReservationFromError(fromTimeError);
		reservationErrorMsgs.setReservationToError(toTimeError);
			
		ParkingAreaErrorMsgs parkingAreaErrorMsgs = new ParkingAreaErrorMsgs();
		parkingAreaErrorMsgs.setParkingareaNameError(parkingAreaError);
		parkingAreaErrorMsgs.setParkingTypeError(parkingTypeError);
		
		
		List<Object> expectedErrorObjectList = Arrays.asList(reservationErrorMsgs, parkingAreaErrorMsgs);
				
		String[] fromAndToTime = getFromAndToTime(fromTime, toTime);
		List<Object> actualErrorObjectList = searchParkingSpotFunctions.searchParkingSpotWithoutOptions(parkingarea_name, parkingtype, fromAndToTime[0], fromAndToTime[1]);
		validateErrors(error, expectedErrorObjectList, actualErrorObjectList);
		
		if(error.equals("")) {
			verifyParkingDataBeforeSelection();
			
			driver.findElement(By.id(prop.getProperty("SearchParkingSpot_radioButton1"))).click();
			driver.findElement(By.id(prop.getProperty("SearchParkingSpot_reserveButton_btn"))).sendKeys(Keys.ENTER);
			verifyValuesBeforePayment(fromAndToTime);
			
			driver.findElement(By.id(prop.getProperty("ReserveParkingSpot_confirm_btn"))).sendKeys(Keys.ENTER);
			verifyReservationConfirmationDetails(fromAndToTime);
			
			gotoHome();
			logout();
		}
	}
	
	@Test
	@FileParameters("./seleniumTestData/ReserveParkingWithoutOptionsModifyTestData.csv")
	public void bReserveParkingWithoutOptionsModifyTest(int testno, String parkingarea_name, String parkingtype, 
			String fromTime, String toTime, String error, String parkingAreaError, String parkingTypeError, 
			String fromTimeError, String toTimeError) throws Exception {
		
		driver.findElement(By.xpath(prop.getProperty("Index_Register"))).click();
		
	    registerManagerAndLogin();
	    
	    searchUserWithValidations("brocolineJohn");
	    
	    driver.findElement(By.id(prop.getProperty("CancelReservation_radioButton_radio"))).click();
		driver.findElement(By.id(prop.getProperty("CancelReservation_modify_btn"))).click();
		
		driver.findElement(By.id(prop.getProperty("ConfirmmodifyReservation_modifyreservation_btn"))).click();
		
		ReservationErrorMsgs reservationErrorMsgs = new ReservationErrorMsgs();
		reservationErrorMsgs.setReservationFromError(fromTimeError);
		reservationErrorMsgs.setReservationToError(toTimeError);
			
		ParkingAreaErrorMsgs parkingAreaErrorMsgs = new ParkingAreaErrorMsgs();
		parkingAreaErrorMsgs.setParkingareaNameError(parkingAreaError);
		parkingAreaErrorMsgs.setParkingTypeError(parkingTypeError);
		
		List<Object> expectedErrorObjectList = Arrays.asList(reservationErrorMsgs, parkingAreaErrorMsgs);
				
		String[] fromAndToTime = getFromAndToTime(fromTime, toTime);
		List<Object> actualErrorObjectList = searchParkingSpotFunctions.searchParkingSpotWithoutOptions(parkingarea_name, parkingtype, fromAndToTime[0], fromAndToTime[1]);
		validateErrors(error, expectedErrorObjectList, actualErrorObjectList);
		
		if(error.equals("")) {
			verifyParkingDataBeforeSelection();
			
			driver.findElement(By.id(prop.getProperty("SearchParkingSpot_radioButton1"))).click();
			driver.findElement(By.id(prop.getProperty("SearchParkingSpot_reserveButton_btn"))).sendKeys(Keys.ENTER);
			verifyValuesBeforePayment(fromAndToTime);
			
			driver.findElement(By.id(prop.getProperty("ReserveParkingSpot_confirm_btn"))).sendKeys(Keys.ENTER);
			verifyReservationConfirmationDetails(fromAndToTime);
			
			gotoHome();
		}
	}
	
	@Test
	@FileParameters("./seleniumTestData/ReserveParkingWithoutOptionsDeleteTestData.csv")
	public void cReserveParkingWithoutOptionsDeleteTest(int testno, String username) throws Exception {
		
		driver.findElement(By.xpath(prop.getProperty("Manager_ModifyDeleteReservation_link"))).click();
	    searchUserWithValidations(username);
	    
	    driver.findElement(By.id(prop.getProperty("CancelReservation_radioButton_radio"))).click();
		driver.findElement(By.id(prop.getProperty("CancelReservation_modify_btn"))).click();
		
		driver.findElement(By.id(prop.getProperty("ConfirmmodifyReservation_deletereservation_btn"))).click();
			
		driver.get("http://localhost:8080/ParkingManagement/manager.jsp");
		if(testDelay.equals("delay")) Thread.sleep(2000);
		
		driver.findElement(By.xpath(prop.getProperty("Manager_Logout_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}

	private void searchUserWithValidations(String username) throws InterruptedException {
		driver.findElement(By.id(prop.getProperty("DeleteReservation_SearchByUserName_btn"))).click();
		Thread.sleep(2000);
	    //DeleteReservation_usernameError_txt
		assertEquals("Please enter the Username", driver.findElement(By.id(prop.getProperty("DeleteReservation_usernameError_txt"))).getText());
	    driver.findElement(By.id(prop.getProperty("DeleteReservation_search_username_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("DeleteReservation_search_username_txt"))).sendKeys("wrong");
		
		driver.findElement(By.id(prop.getProperty("DeleteReservation_SearchByUserName_btn"))).click();
		Thread.sleep(2000);
		assertEquals("User name is not in the system", driver.findElement(By.id(prop.getProperty("DeleteReservation_usernameError_txt"))).getText());
		driver.findElement(By.id(prop.getProperty("DeleteReservation_search_username_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("DeleteReservation_search_username_txt"))).sendKeys("userwithoutreservations");
		driver.findElement(By.id(prop.getProperty("DeleteReservation_SearchByUserName_btn"))).sendKeys(Keys.ENTER);
		
		driver.findElement(By.id(prop.getProperty("DeleteReservation_search_username_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("DeleteReservation_search_username_txt"))).sendKeys(username);
		
		driver.findElement(By.id(prop.getProperty("DeleteReservation_SearchByUserName_btn"))).click();
		Thread.sleep(2000);		
		
	}

	private void validateErrors(String error, List<Object> expectedErrorObjectList,
			List<Object> actualErrorObjectList) throws Exception {
		if(testDelay.equals("delay")) Thread.sleep(1000);
		ReservationErrorMsgs expectedReservErrors = (ReservationErrorMsgs) expectedErrorObjectList.get(0);
		ParkingAreaErrorMsgs expectedparkingErrorMsgs = (ParkingAreaErrorMsgs) expectedErrorObjectList.get(1);
		
		ReservationErrorMsgs actualReservErrors = (ReservationErrorMsgs) actualErrorObjectList.get(0);
		ParkingAreaErrorMsgs actualparkingErrorMsgs = (ParkingAreaErrorMsgs) actualErrorObjectList.get(1);
		
		assertEquals(expectedReservErrors.getReservationFromError(), actualReservErrors.getReservationFromError());
		assertEquals(expectedReservErrors.getReservationToError(), actualReservErrors.getReservationToError());
		
		assertEquals(expectedparkingErrorMsgs.getParkingareaNameError(), actualparkingErrorMsgs.getParkingareaNameError());
		assertEquals(expectedparkingErrorMsgs.getParkingTypeError(), actualparkingErrorMsgs.getParkingTypeError());
		
		if(!actualReservErrors.getErrormsg().equals(""))
			assertTrue(actualReservErrors.getErrormsg().contains(error));
		if(!actualparkingErrorMsgs.getErrormsg().equals(""))
			assertTrue(actualparkingErrorMsgs.getErrormsg().contains(error));
	}

	private void logout() throws Exception{
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_Logout_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}

	private void gotoHome() throws Exception {
		driver.findElement(By.xpath(prop.getProperty("ReservationConfirmed_Home_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}

	private void verifyParkingDataBeforeSelection() throws Exception {
		if(testDelay.equals("delay")) Thread.sleep(1000);
		assertEquals("Maverick", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_name1"))).getText());
		assertEquals("Basic", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_type1"))).getText());
		assertEquals("1", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_floor1"))).getText());
		assertTrue(Integer.parseInt(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_count1"))).getText())>0);
		assertTrue(Double.parseDouble(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_totalcost1"))).getText()) == 0.00);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}
	
	private void verifyValuesBeforePayment(String[] fromAndToTime) throws Exception {
		verifyCommonValuesOfReservation(fromAndToTime);
		assertTrue(Double.parseDouble(driver.findElement(By.id(prop.getProperty("totalCost_txt"))).getAttribute("value")) == 0.00);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}

	private void verifyReservationConfirmationDetails(String[] fromAndToTime) throws Exception {
		verifyCommonValuesOfReservation(fromAndToTime);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}

	private void verifyCommonValuesOfReservation(String[] fromAndToTime) {
		assertEquals("Maverick", driver.findElement(By.id(prop.getProperty("inputParkingName_txt"))).getAttribute("value"));
		assertEquals("Basic", driver.findElement(By.id(prop.getProperty("inputParkingType_txt"))).getAttribute("value"));
		assertEquals("1", driver.findElement(By.id(prop.getProperty("inputFloor_txt"))).getAttribute("value"));
		assertEquals(fromAndToTime[0], driver.findElement(By.id(prop.getProperty("inputFromTime_txt"))).getAttribute("value"));
		assertEquals(fromAndToTime[1], driver.findElement(By.id(prop.getProperty("inputToTime_txt"))).getAttribute("value"));
		assertTrue((driver.findElement(By.id(prop.getProperty("options_txt"))).getAttribute("value")).equals(""));
	}

	private String[] getFromAndToTime(String fromTimeTemp, String toTimeTemp) {
		String fromTime;
		String toTime;
		
		String currentTime = getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		int currentHours = Integer.parseInt(currentTimeArray[0]);
		
		if(fromTimeTemp.equals(""))
			fromTime="";
		else {
			if(fromTimeTemp.contains("-")) {
				fromTimeTemp = fromTimeTemp.substring(1);
				String[] fromTimeTempArray = fromTimeTemp.split(":");
				fromTime = (currentHours-Integer.parseInt(fromTimeTempArray[0]))+":"+fromTimeTempArray[1];
				if((currentHours-Integer.parseInt(fromTimeTempArray[0])<=9)) 
					fromTime = "0"+(currentHours-Integer.parseInt(fromTimeTempArray[0]))+":"+fromTimeTempArray[1];
			} else {
				String[] fromTimeTempArray = fromTimeTemp.split(":");
				fromTime = (currentHours+Integer.parseInt(fromTimeTempArray[0]))+":"+fromTimeTempArray[1];
				if((currentHours+Integer.parseInt(fromTimeTempArray[0]))<=9) 
					fromTime = "0"+(currentHours+Integer.parseInt(fromTimeTempArray[0]))+":"+fromTimeTempArray[1];
			}
		}
		
		if(toTimeTemp.equals(""))
			toTime="";
		else {
			if(toTimeTemp.contains("-")) {
				toTimeTemp = toTimeTemp.substring(1);
				String[] toTimeTempArray = toTimeTemp.split(":");
				toTime = (currentHours-Integer.parseInt(toTimeTempArray[0]))+":"+toTimeTempArray[1];
				if((currentHours-Integer.parseInt(toTimeTempArray[0])<=9)) 
					toTime = "0"+(currentHours-Integer.parseInt(toTimeTempArray[0]))+":"+toTimeTempArray[1];
			} else {
				String[] toTimeTempArray = toTimeTemp.split(":");
				toTime = (currentHours+Integer.parseInt(toTimeTempArray[0]))+":"+toTimeTempArray[1];
				
				if((currentHours+Integer.parseInt(toTimeTempArray[0]))<=9)
					toTime = "0"+(currentHours+Integer.parseInt(toTimeTempArray[0]))+":"+toTimeTempArray[1];
			}
		}
		
		String[] fromAndToTime = {fromTime, toTime};
		return fromAndToTime;
	}

	private String getCurrentTimeUsingDate() {
	    Date date = new Date();
	    String strDateFormat = "HH:mm:ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    return dateFormat.format(date);
	}
	
	private static void registerAndLogin() throws InterruptedException {
		User user = new User("Brocoline", "John", "brocolineJohn", "Test@123", "Test@123", "1001518112", "Student/Faculty",
				"1234567890", "Brocoline@gmail.com", "603 causley ave", "Arlington", "Texas", "76010", "8112",
				"12345678", "Basic");
		registerUserFunctions.registerUserSuccess(user);
		
		assertEquals("Registration Successful",driver.findElement(By.id("successMsg")).getText());
		driver.findElement(By.id("login_link")).click();
		
		loginTestFunctions.loginSuccessFunction("brocolineJohn", "Test@123");
		
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_search_link"))).click();
		
	}
	
	private static void registerManagerAndLogin() throws InterruptedException {
		User user = new User("Brocoline", "Manager", "brocolineManager", "Test@123", "Test@123", "1001518112", "Manager",
				"1234567890", "Brocoline@gmail.com", "603 causley ave", "Arlington", "Texas", "76010", "8112",
				"12345678", "Basic");
		registerUserFunctions.registerUserSuccess(user);
		
		assertEquals("Registration Successful",driver.findElement(By.id("successMsg")).getText());
		driver.findElement(By.id("login_link")).click();
		
		loginTestFunctions.loginSuccessFunction("brocolineManager", "Test@123");
		
		driver.findElement(By.xpath(prop.getProperty("Manager_ModifyDeleteReservation_link"))).click();
		
	}
}
