package selenium;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import parkingManagement.model.ReservationErrorMsgs;
import parkingManagement.model.User;
import parkingManagement.model.UserErrorMsgs;
import selenium.functions.AdminFunctions;
import selenium.functions.LoginTestFunctions;
import selenium.functions.RegisterUserFunctions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminSearchUserValidationsTest extends SeleniumTestBase{
	static RegisterUserFunctions registerUserFunctions;
	static LoginTestFunctions loginTestFunctions;
	static AdminFunctions adminFunctions;
	static SeleniumTestBase seleniumTestBase;
	  
	@BeforeClass
	  public static void setUp() throws Exception {
			seleniumTestBase = new SeleniumTestBase();
		    registerUserFunctions = new RegisterUserFunctions();
		    loginTestFunctions = new LoginTestFunctions();
		    adminFunctions = new AdminFunctions();
		    setDriver();
		    
		    loginTestFunctions.loginSuccessFunction("adminone", "test@123");
			if(testDelay.equals("delay"))  Thread.sleep(2000);
			
			driver.findElement(By.xpath(prop.getProperty("Admin_SearchUser_link"))).click();
	  }

	@AfterClass
		public static void tearDown() throws Exception {
			goToHome();
			logout();
			driver.close();
		}
	  
	  @Test
	  @FileParameters("./seleniumTestData/SearchUserTestData.csv")
	  public void searchUserValidationsForAdminTest(int testcasenumber, String lastname,String lastnameerrormsg) throws Exception
	  {
		  if(testDelay.equals("delay"))  Thread.sleep(2000);
		  UserErrorMsgs expectedLastnameError = new UserErrorMsgs();
		  expectedLastnameError.setLastnameError(lastnameerrormsg);
		  UserErrorMsgs actualLastnameError = adminFunctions.searchUserFunction(lastname);
		  assertEquals(expectedLastnameError.getLastnameError(),actualLastnameError.getLastnameError()); 
	  }
	  
	  private static void goToHome() throws Exception {	
			driver.findElement(By.xpath(prop.getProperty("Search_user_Home_link"))).sendKeys(Keys.ENTER);
			if(testDelay.equals("delay")) Thread.sleep(2000);
		}
	
		private static void logout() throws Exception{	
			driver.findElement(By.xpath(prop.getProperty("Admin_Logout_link"))).click();
			if(testDelay.equals("delay")) Thread.sleep(2000);
		}
}
