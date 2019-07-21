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
import selenium.functions.AdminFunctions;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminChangeUserStatusTest extends SeleniumTestBase {
	static RegisterUserFunctions registerUserFunctions;
	static LoginTestFunctions loginTestFunctions;
	static SeleniumTestBase seleniumTestBase;
	static AdminFunctions adminFunctions;

	@BeforeClass
	public static void setUp() throws Exception {
		seleniumTestBase = new SeleniumTestBase();
		registerUserFunctions = new RegisterUserFunctions();
		loginTestFunctions = new LoginTestFunctions();
		adminFunctions = new AdminFunctions();
    
		setDriver();
		login();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		goToHome();
		logout();
		driver.close();
	}
	
	@Test
	@FileParameters("./seleniumTestData/RevokeUserTestData.csv")
	public void arevokeUserTest(int testno, String username, String success_message, String username_error) throws Exception
	{
		UserErrorMsgs expectedUsernameErrorMsg = new UserErrorMsgs();
		expectedUsernameErrorMsg.setUsernameError(username_error);
		
		UserErrorMsgs actualUsernameErrorMsg = adminFunctions.revokeUser(username);
		
		assertEquals(expectedUsernameErrorMsg.getUsernameError(), actualUsernameErrorMsg.getUsernameError() );
		assertEquals(success_message,driver.findElement(By.id(prop.getProperty("RevokeUser_successMsg_txt"))).getText());
		
	}
	
	@Test
	@FileParameters("./seleniumTestData/ActivateUserTestData.csv")
	public void bactivateUserTest(int testno, String username, String success_message, String username_error) throws Exception
	{
		goToHome();
		driver.findElement(By.xpath(prop.getProperty("Admin_ActivateUser_link"))).click();
		
		UserErrorMsgs expectedUsernameErrorMsg = new UserErrorMsgs();
		expectedUsernameErrorMsg.setUsernameError(username_error);
		
		UserErrorMsgs actualUsernameErrorMsg = adminFunctions.activateUser(username);
		
		assertEquals(expectedUsernameErrorMsg.getUsernameError(), actualUsernameErrorMsg.getUsernameError() );
		assertEquals(success_message,driver.findElement(By.id(prop.getProperty("ActivateUser_successmsg_txt"))).getText());
		
	}	
	private static void login() throws InterruptedException {
		loginTestFunctions.loginSuccessFunction("adminone", "test@123");
		driver.findElement(By.xpath(prop.getProperty("Admin_RevokeUser_link"))).click();
	}
	
	private static void goToHome()
	{
		driver.findElement(By.xpath(prop.getProperty("RevokeUser_Home_link"))).click();
	}
	
	private static void logout()
	{
		driver.findElement(By.xpath(prop.getProperty("Admin_Logout_link"))).click();
	}
}
