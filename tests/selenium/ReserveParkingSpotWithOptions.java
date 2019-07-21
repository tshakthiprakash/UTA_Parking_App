package selenium;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import parkingManagement.model.*;
import selenium.functions.LoginTestFunctions;
import selenium.functions.PaymentFunctions;
import selenium.functions.RegisterUserFunctions;
import selenium.functions.SearchParkingSpotFunctions;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReserveParkingSpotWithOptions extends SeleniumTestBase{
	static RegisterUserFunctions registerUserFunctions;
	static LoginTestFunctions loginTestFunctions;
	static SearchParkingSpotFunctions searchParkingSpotFunctions;
	static PaymentFunctions paymentFunctions;
	static SeleniumTestBase seleniumTestBase;
	static String[] fromAndToTime;
	 
	@BeforeClass
	public static void setUp() throws Exception {
		seleniumTestBase = new SeleniumTestBase();
	    registerUserFunctions = new RegisterUserFunctions();
	    loginTestFunctions = new LoginTestFunctions();
	    searchParkingSpotFunctions = new SearchParkingSpotFunctions();
	    paymentFunctions = new PaymentFunctions();
	    
	    setDriver();
	    
	    driver.findElement(By.xpath(prop.getProperty("Index_Register"))).click();
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		driver.close();
	}

	@Test
	@FileParameters("./seleniumTestData/ReserveParkingSpotWithOptionsTestData.csv")
	public void areserveParkingWithOptionsEndToEndTest(int testno, String parkingarea_name, String parkingtype, 
			String fromTime, String toTime, String error, String parkingAreaError, String parkingTypeError, 
			String fromTimeError, String toTimeError) throws Exception {

		User user = new User("Brocoline", "Tom", "brocoline", "Test@123", "Test@123", "1001518112", "Student/Faculty",
				"1234567890", "Brocoline@gmail.com", "603 causley ave", "Arlington", "Texas", "76010", "8112",
				"12345678", "Basic");
		registerUserFunctions.registerUserSuccess(user);
		
		assertEquals("Registration Successful",driver.findElement(By.id("successMsg")).getText());
		driver.findElement(By.id("login_link")).click();
		
		loginTestFunctions.loginSuccessFunction("brocoline", "Test@123");
		
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_search_link"))).click();
		
		fromAndToTime = getFromAndToTime(fromTime, toTime);
		
		searchParkingSpotFunctions.searchParkingSpotWithOptions("Davis", parkingtype, fromAndToTime[0], fromAndToTime[1]);

		gotoHome();
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_search_link"))).click();
		
		searchParkingSpotFunctions.searchParkingSpotWithOptions(parkingarea_name, parkingtype, fromAndToTime[0], fromAndToTime[1]);
		verifyParkingDataBeforeSelection();
		
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_radioButton1"))).click();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_reserveButton_btn"))).sendKeys(Keys.ENTER);
		verifyValuesBeforePayment(fromAndToTime);
		
		driver.findElement(By.id(prop.getProperty("ReserveParkingSpot_makePayment_btn"))).sendKeys(Keys.ENTER);
	}
	
	@Test
	@FileParameters("./seleniumTestData/ReserveParkingSpotPaymentTestData.csv")
	public void bmakePaymentValidations(String tcno, String payerFirstname, String payerLastname, 
			String billingAddress, String cardType, String cardNumber, String expiryYear,
			String expiryMonth, String cvv, String FirstnameError, String LastnameError, 
			String AddressError, String CardTypeError, String CardNumberError, 
			String ExpiryYearError, String ExpiryMonthError, String CvvError, String ErrMsg) throws Exception {
		
		PaymentErrorMsgs paymErr = paymentFunctions.makePaymentFailure(payerFirstname, payerLastname, billingAddress, cardNumber,
				cardType, expiryMonth, expiryYear, cvv);
		
		assertEquals(FirstnameError, paymErr.getPayerFirstnameError());
		assertEquals(LastnameError, paymErr.getPayerLastnameError());
		assertEquals(AddressError, paymErr.getBillingAddressError());
		assertEquals(CardTypeError, paymErr.getCardTypeError());
		assertEquals(CardNumberError, paymErr.getCardNumberError());
		assertEquals(ExpiryYearError, paymErr.getExpiryYearError());
		assertEquals(ExpiryMonthError, paymErr.getExpiryMonthError());
		assertEquals(CvvError, paymErr.getCvvError());
		assertEquals(ErrMsg,paymErr.getErrorMsg());
		
	}

	@Test
	@FileParameters("./seleniumTestData/ReserveParkingSpotPaymentSuccessTestData.csv")
	public void cmakePaymentSuccess(String tcno, String payerFirstname, String payerLastname, 
			String billingAddress, String cardType, String cardNumber, String expiryYear,
			String expiryMonth, String cvv, String FirstnameError, String LastnameError, 
			String AddressError, String CardTypeError, String CardNumberError, 
			String ExpiryYearError, String ExpiryMonthError, String CvvError, String ErrMsg) throws Exception {
		
		paymentFunctions.makeSuccessPayment(payerFirstname, payerLastname, billingAddress, cardNumber,
				cardType, expiryMonth, expiryYear, cvv);
		
		verifyReservationConfirmationDetails(fromAndToTime);
	
		gotoHome();
		logout();
	}

	@Test
	@FileParameters("./seleniumTestData/ReserveParkingWithOptionsModifyTestData.csv")
	public void dReserveParkingWithOptionsModifyTest(int testno, String username) throws Exception {
		
		loginTestFunctions.loginSuccessFunction("manager", "Test@123");
		
		driver.findElement(By.xpath(prop.getProperty("Manager_ModifyDeleteReservation_link"))).click();
		
		driver.findElement(By.id(prop.getProperty("DeleteReservation_search_username_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("DeleteReservation_search_username_txt"))).sendKeys(username);
		
		driver.findElement(By.id(prop.getProperty("DeleteReservation_SearchByUserName_btn"))).click();
		Thread.sleep(2000);	
	    
	    driver.findElement(By.id(prop.getProperty("CancelReservation_radioButton_radio"))).click();
		driver.findElement(By.id(prop.getProperty("CancelReservation_modify_btn"))).click();
		
		driver.findElement(By.id(prop.getProperty("ConfirmmodifyReservation_modifyreservation_btn"))).click();
				
		String[] fromAndToTime = getFromAndToTime("2", "3");
		searchParkingSpotFunctions.searchParkingSpotWithoutOptions("Davis", "Basic", fromAndToTime[0], fromAndToTime[1]);
		
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_radioButton1"))).click();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_reserveButton_btn"))).sendKeys(Keys.ENTER);
		
		driver.findElement(By.id(prop.getProperty("ReserveParkingSpot_confirm_btn"))).sendKeys(Keys.ENTER);
		
		gotoHome();
	}
	
	@Test
	@FileParameters("./seleniumTestData/ReserveParkingWithOptionsDeleteTestData.csv")
	public void eReserveParkingWithOptionsDeleteTest(int testno, String username) throws Exception {
		
		driver.findElement(By.xpath(prop.getProperty("Manager_ModifyDeleteReservation_link"))).click();
		
		driver.findElement(By.id(prop.getProperty("DeleteReservation_search_username_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("DeleteReservation_search_username_txt"))).sendKeys(username);
		
		driver.findElement(By.id(prop.getProperty("DeleteReservation_SearchByUserName_btn"))).click();
		Thread.sleep(2000);	
	    
	    driver.findElement(By.id(prop.getProperty("CancelReservation_radioButton_radio"))).click();
		driver.findElement(By.id(prop.getProperty("CancelReservation_modify_btn"))).click();
		
		driver.findElement(By.id(prop.getProperty("ConfirmmodifyReservation_deletereservation_btn"))).click();
			
		//driver.findElement(By.id(prop.getProperty("DeleteReservation_Home_link"))).sendKeys(Keys.ENTER);
		driver.get("http://localhost:8080/ParkingManagement/manager.jsp");
		if(testDelay.equals("delay")) Thread.sleep(2000);
		
		driver.findElement(By.xpath(prop.getProperty("Manager_Logout_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(2000);
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
		Thread.sleep(7000);
		assertEquals("Maverick", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_name1"))).getText());
		assertEquals("Basic", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_type1"))).getText());
		assertEquals("1", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_floor1"))).getText());
		assertTrue(Integer.parseInt(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_count1"))).getText())>0);
		assertTrue(Double.parseDouble(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_totalcost1"))).getText()) >= 22.55);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}
	
	private void verifyValuesBeforePayment(String[] fromAndToTime) throws Exception {
		verifyCommonValuesOfReservation(fromAndToTime);
		assertTrue(Double.parseDouble(driver.findElement(By.id(prop.getProperty("totalCost_txt"))).getAttribute("value")) >= 22.55);
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
		assertTrue((driver.findElement(By.id(prop.getProperty("options_txt"))).getAttribute("value")).contains("Cart, Camera, History"));
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
			fromTime = (currentHours+Integer.parseInt(fromTimeTemp))+":00";
			if((currentHours+Integer.parseInt(fromTimeTemp))<=9) 
				fromTime = "0"+(currentHours+Integer.parseInt(fromTimeTemp))+":00";
		}
		
		if(toTimeTemp.equals(""))
			toTime="";
		else {
			toTime = (currentHours+Integer.parseInt(toTimeTemp))+":00";
			
			if((currentHours+Integer.parseInt(toTimeTemp))<=9)
				toTime = "0"+(currentHours+Integer.parseInt(toTimeTemp))+":00";
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
}
