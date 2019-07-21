package selenium;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import parkingManagement.model.User;
import parkingManagement.model.UserErrorMsgs;
import selenium.functions.AdminFunctions;
import selenium.functions.LoginTestFunctions;
import selenium.functions.RegisterUserFunctions;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminSearchUserChangeRoleTest extends SeleniumTestBase {
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
    
		loginTestFunctions.loginSuccessFunction("adminone", "test@123");
		if(testDelay.equals("delay"))  Thread.sleep(2000);
		
		//Click on search user  
		driver.findElement(By.xpath(prop.getProperty("Admin_SearchUser_link"))).click();
		if(testDelay.equals("delay"))  Thread.sleep(2000);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.close();
	}
	
	@Test
	@FileParameters("./seleniumTestData/AdminSearchUserSuccessTestData.csv")
	public void asearchUserAdminTest(int testcasenumber, String lastname, String userlist, String userdetail) throws Exception {
			  
			//Search for a user  
			adminFunctions.searchUserSuccessFunction(lastname);
	  		driver.findElement(By.linkText(userlist)).click();
			if(testDelay.equals("delay"))  Thread.sleep(2000);
			
			//Verify that details are displayed for the right user 
			assertEquals(userdetail,driver.findElement(By.id(prop.getProperty("SearchSpecificUser_userName_txt"))).getText());
	}
	
	@Test
	@FileParameters("./seleniumTestData/ChangeUserRoleTestData.csv")
	public void bchangeUserRoleTest(int testcasenum, String roleBeforeChange, String roleAfterChange) throws Exception
	{
			//Verify that the Role is Student/Faculty before changing
			verifyRoleBeforeChange(roleBeforeChange);
		
			//Change Role of the user  
			adminFunctions.changeUserRoleSuccessFunction();
			
//			//Verify that the Role is Manager now and has been changed successfully
			verifyRoleAfterChange(roleAfterChange);
//			
			
			//Change Role of the user  
			adminFunctions.changeUserRoleToStudentSuccessFunction();
//			//Navigate to home
			goToHome();
//			
//			//Logout   
			logout();
		 	    
	}
	
	private void verifyRoleBeforeChange(String roleBeforeChange) throws Exception
	{
		assertEquals(roleBeforeChange,driver.findElement(By.id(prop.getProperty("SearchSpecificUser_role_txt"))).getText());
	}
	
	private void verifyRoleAfterChange(String roleAfterChange) throws Exception
	{
		assertEquals(roleAfterChange,driver.findElement(By.id(prop.getProperty("SearchSpecificUser_role_txt"))).getText());
	}
	
	private void goToHome() throws Exception {	
		driver.findElement(By.xpath(prop.getProperty("SearchSpecificUser_Home_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}
	
	private void logout() throws Exception{	
		driver.findElement(By.xpath(prop.getProperty("Admin_Logout_link"))).click();
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}
	
}




