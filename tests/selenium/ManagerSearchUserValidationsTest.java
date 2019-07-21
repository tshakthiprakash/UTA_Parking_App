package selenium;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import parkingManagement.model.UserErrorMsgs;
import selenium.functions.LoginTestFunctions;
import selenium.functions.ManagerFunctions;
import selenium.functions.RegisterUserFunctions;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerSearchUserValidationsTest extends SeleniumTestBase{
	static RegisterUserFunctions registerUserFunctions;
	static LoginTestFunctions loginTestFunctions;
	static ManagerFunctions managerFunctions;
	static SeleniumTestBase seleniumTestBase;
	  
	@BeforeClass
	  public static void setUp() throws Exception {
			seleniumTestBase = new SeleniumTestBase();
		    registerUserFunctions = new RegisterUserFunctions();
		    loginTestFunctions = new LoginTestFunctions();
		    managerFunctions = new ManagerFunctions();
		    setDriver();
		    
		    loginTestFunctions.loginSuccessFunction("managerone", "test@123");
			if(testDelay.equals("delay"))  Thread.sleep(2000);
			
			//Click on search user  
			driver.findElement(By.xpath(prop.getProperty("Manager_SearchUser_link"))).click();
			if(testDelay.equals("delay"))  Thread.sleep(2000);
			
	  }

	@AfterClass
		public static void tearDown() throws Exception {
			goToHome();
			logout();
			driver.close();
		}
	  
	  @Test
	  @FileParameters("./seleniumTestData/SearchUserTestData.csv")
	  public void searchUserValidationsTest(int testcasenumber, String lastname,String lastnameerrormsg) throws Exception
	  {
		  UserErrorMsgs expectedLastnameError = new UserErrorMsgs();
		  expectedLastnameError.setLastnameError(lastnameerrormsg);
		  UserErrorMsgs actualLastnameError = managerFunctions.searchUserFunction(lastname);
		  assertEquals(expectedLastnameError.getLastnameError(),actualLastnameError.getLastnameError()); 
	  }
	  
	  private static void goToHome() throws Exception {	
			driver.findElement(By.xpath(prop.getProperty("Search_user_Home_link"))).sendKeys(Keys.ENTER);
			if(testDelay.equals("delay")) Thread.sleep(2000);
		}
	
		private static void logout() throws Exception{	
			driver.findElement(By.xpath(prop.getProperty("Manager_Logout_link"))).click();
			if(testDelay.equals("delay")) Thread.sleep(2000);
		}
}
