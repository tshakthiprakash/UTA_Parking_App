package selenium;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import parkingManagement.model.*;
import selenium.functions.LoginTestFunctions;
import selenium.functions.PaymentFunctions;
import selenium.functions.RegisterUserFunctions;
import selenium.functions.SearchParkingSpotFunctions;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterUserValidationsTest extends SeleniumTestBase{
	
	static RegisterUserFunctions registerUserFunctions;
	static LoginTestFunctions loginTestFunctions;
	static SearchParkingSpotFunctions searchParkingSpotFunctions;
	static PaymentFunctions paymentFunctions;
	static SeleniumTestBase seleniumTestBase;
	 
	@BeforeClass
	public static void setUp() throws Exception {
	    registerUserFunctions = new RegisterUserFunctions();
	    loginTestFunctions = new LoginTestFunctions();
	    searchParkingSpotFunctions = new SearchParkingSpotFunctions();
	    paymentFunctions = new PaymentFunctions();
	    seleniumTestBase = new SeleniumTestBase();
	    
	    setDriver();
	    
	    driver.findElement(By.xpath(prop.getProperty("Index_Register"))).click();
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		driver.close();
	}
	
	@Test
	@FileParameters("./seleniumTestData/UserTestData.csv")
	public void registerUserErrorValidationTest(int testno, String username, String password, String confirmPassword, String firstname,
			String lastname, String email, String phone, String utaid, String plate_number, String permit_id,
			String permit_type, String street_add, String city, String state, String role, String zip, String errMsg,
			String usernameErr, String firstnameErr, String lastnameErr, String passwordErr, String confirmPwdError,
			String utaidErr, String emailErr, String phoneErr, String zipErr, String num_plateErr, String permitIdErr,
			String streetAddrError, String cityErr, String stateErr) throws Exception {
		
		User user = new User(firstname, lastname, username, password, confirmPassword, utaid, role,
							phone, email, street_add, city, state, zip, plate_number,
							permit_id, permit_type);
		UserErrorMsgs expectedUserErrorMsgs = new UserErrorMsgs(usernameErr,errMsg,firstnameErr,lastnameErr,passwordErr,utaidErr,emailErr,phoneErr,zipErr,num_plateErr,permitIdErr,confirmPwdError,streetAddrError, cityErr, stateErr);
		
		UserErrorMsgs actualUserErrorMsgs = registerUserFunctions.registerUserError(testno, user);
		validateUserErrorMsgs(expectedUserErrorMsgs, actualUserErrorMsgs);		
	}
	
	@Test
	@FileParameters("./seleniumTestData/UserTestDataSuccess.csv")
	public void registerUserSuccessTest(int testno, String username, String password, String confirmPassword, String firstname,
			String lastname, String email, String phone, String utaid, String plate_number, String permit_id,
			String permit_type, String street_add, String city, String state, String role, String zip, String errMsg,
			String usernameErr, String firstnameErr, String lastnameErr, String passwordErr, String confirmPwdError,
			String utaidErr, String emailErr, String phoneErr, String zipErr, String num_plateErr, String permitIdErr,
			String streetAddrError, String cityErr, String stateErr) throws Exception {
		
		User user = new User(firstname, lastname, username, password, confirmPassword, utaid, role,
							phone, email, street_add, city, state, zip, plate_number,
							permit_id, permit_type);
		
		registerUserFunctions.registerUserSuccess(user);
		
		assertEquals("Registration Successful",driver.findElement(By.id("successMsg")).getText());
		driver.findElement(By.id("login_link")).click();
	}
	
	private void validateUserErrorMsgs(UserErrorMsgs expectedUserErrorMsgs, UserErrorMsgs actualUserErrorMsgs) {
		assertEquals(expectedUserErrorMsgs.getFirstnameError(), actualUserErrorMsgs.getFirstnameError());
		assertEquals(expectedUserErrorMsgs.getLastnameError(), actualUserErrorMsgs.getLastnameError());
		assertEquals(expectedUserErrorMsgs.getUsernameError(), actualUserErrorMsgs.getUsernameError());
		assertEquals(expectedUserErrorMsgs.getPasswordError(), actualUserErrorMsgs.getPasswordError());
		assertEquals(expectedUserErrorMsgs.getUtaIdError(), actualUserErrorMsgs.getUtaIdError());
		assertEquals(expectedUserErrorMsgs.getEmailError(), actualUserErrorMsgs.getEmailError());
		assertEquals(expectedUserErrorMsgs.getPhoneError(), actualUserErrorMsgs.getPhoneError());
		assertEquals(expectedUserErrorMsgs.getZipCodeError(), actualUserErrorMsgs.getZipCodeError());
		assertEquals(expectedUserErrorMsgs.getCarNmbrError(), actualUserErrorMsgs.getCarNmbrError());
		assertEquals(expectedUserErrorMsgs.getPermitIdError(), actualUserErrorMsgs.getPermitIdError());
		assertEquals(expectedUserErrorMsgs.getConfirmPwdError(), actualUserErrorMsgs.getConfirmPwdError());
		assertEquals(expectedUserErrorMsgs.getStreetAddrError(), actualUserErrorMsgs.getStreetAddrError());
		assertEquals(expectedUserErrorMsgs.getCityError(), actualUserErrorMsgs.getCityError());
		assertEquals(expectedUserErrorMsgs.getStateError(), actualUserErrorMsgs.getStateError());
		assertEquals(expectedUserErrorMsgs.getErrorMsg(), actualUserErrorMsgs.getErrorMsg());
	}
}