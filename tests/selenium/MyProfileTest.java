package selenium;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import parkingManagement.model.*;
import selenium.SeleniumTestBase;
import selenium.functions.LoginTestFunctions;
import selenium.functions.RegisterUserFunctions;
import selenium.functions.MyProfileFunctions;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyProfileTest extends SeleniumTestBase {
	static RegisterUserFunctions registerUserFunctions;
	static LoginTestFunctions loginTestFunctions;
	static MyProfileFunctions myprofilefunctions;
	static SeleniumTestBase seleniumTestBase;
	
	@BeforeClass
	public static void setUp() throws Exception {
		seleniumTestBase = new SeleniumTestBase();
	    registerUserFunctions = new RegisterUserFunctions();
	    loginTestFunctions = new LoginTestFunctions();
	    myprofilefunctions = new MyProfileFunctions();
	    setDriver();
	    
	    driver.findElement(By.xpath(prop.getProperty("Index_Register"))).click();
	    User user = new User("profileuser", "ProfileUser", "ProfileUser", "Test@123", "Test@123", "1001518112", "Student/Faculty",
				"1234567890", "ProfileUser@gmail.com", "603 causley ave", "Arlington", "Texas", "76010", "8112",
				"12345678", "Basic");
		registerUserFunctions.registerUserSuccess(user);
		assertEquals("Registration Successful",driver.findElement(By.id("successMsg")).getText());
		driver.findElement(By.id("login_link")).click();
		loginTestFunctions.loginSuccessFunction("profileuser", "Test@123");
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_myProfile_link"))).click();
		driver.findElement(By.id(prop.getProperty("Myprofile_edit_btn"))).click();
	}
	
	@AfterClass
	public static void tearDownAfterTest() throws Exception {
		driver.close();
	}
	
	@Test
	@FileParameters("./seleniumTestData/MyProfileErrorTestData.csv")
	public void MyProfileErrorTest(int testno,String username, String password, String confirmPassword, String firstname,
			String lastname, String email, String phone, String utaid, String plate_number, String permit_id,
			String permit_type, String street_add, String city, String state, String role, String zip, String errMsg,
			String usernameErr, String firstnameErr, String lastnameErr, String passwordErr, String confirmPwdError,
			String utaidErr, String emailErr, String phoneErr, String zipErr, String num_plateErr, String permitIdErr,
			String streetAddrError, String cityErr, String stateErr) throws Exception {
		
		User ErrorUser = new User(firstname, lastname, username, password, confirmPassword, utaid, role,
				phone, email, street_add, city, state, zip, plate_number,
				permit_id, permit_type);
		UserErrorMsgs actualUserErrorMsgs = myprofilefunctions.MyProfileErrorFunction(testno, ErrorUser);
		UserErrorMsgs expectedUserErrorMsgs = new UserErrorMsgs(usernameErr,errMsg,firstnameErr,lastnameErr,passwordErr,utaidErr,emailErr,phoneErr,zipErr,num_plateErr,permitIdErr,confirmPwdError,streetAddrError, cityErr, stateErr);
		validateUserErrorMsgs(expectedUserErrorMsgs, actualUserErrorMsgs);
		}
	
	@Test
	@FileParameters("./seleniumTestData/MyProfileTestData.csv")
	public void MyProfileandUpdateTest(int testno,String password,String updatedEmail,String updatedCarNum,String UpdatedZip) throws Exception {
			myprofilefunctions.MyProfileFunction(password,updatedEmail,updatedCarNum,UpdatedZip);
		}
	
	
	private void validateUserErrorMsgs(UserErrorMsgs expectedUserErrorMsgs, UserErrorMsgs actualUserErrorMsgs) {
		assertEquals(expectedUserErrorMsgs.getFirstnameError(), actualUserErrorMsgs.getFirstnameError());
		assertEquals(expectedUserErrorMsgs.getLastnameError(), actualUserErrorMsgs.getLastnameError());
		assertEquals(expectedUserErrorMsgs.getPasswordError(), actualUserErrorMsgs.getPasswordError());
		assertEquals(expectedUserErrorMsgs.getUtaIdError(), actualUserErrorMsgs.getUtaIdError());
		assertEquals(expectedUserErrorMsgs.getEmailError(), actualUserErrorMsgs.getEmailError());
		assertEquals(expectedUserErrorMsgs.getPhoneError(), actualUserErrorMsgs.getPhoneError());
		assertEquals(expectedUserErrorMsgs.getZipCodeError(), actualUserErrorMsgs.getZipCodeError());
		assertEquals(expectedUserErrorMsgs.getCarNmbrError(), actualUserErrorMsgs.getCarNmbrError());
		assertEquals(expectedUserErrorMsgs.getPermitIdError(), actualUserErrorMsgs.getPermitIdError());
		assertEquals(expectedUserErrorMsgs.getStreetAddrError(), actualUserErrorMsgs.getStreetAddrError());
		assertEquals(expectedUserErrorMsgs.getCityError(), actualUserErrorMsgs.getCityError());
		assertEquals(expectedUserErrorMsgs.getStateError(), actualUserErrorMsgs.getStateError());
		assertEquals(expectedUserErrorMsgs.getErrorMsg(), actualUserErrorMsgs.getErrorMsg());
	}
}