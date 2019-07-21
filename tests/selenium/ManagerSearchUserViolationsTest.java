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
import selenium.functions.ManagerFunctions;
import selenium.functions.RegisterUserFunctions;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerSearchUserViolationsTest extends SeleniumTestBase {
	static RegisterUserFunctions registerUserFunctions;
	static LoginTestFunctions loginTestFunctions;
	static SeleniumTestBase seleniumTestBase;
	static ManagerFunctions managerFunctions;

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
		driver.close();
	}
	
	@Test
	@FileParameters("./seleniumTestData/ManagerSearchUserSuccessData.csv")
	public void asearchUserAdminTest(int testcasenumber, String lastname, String userlist, String userdetail) throws Exception {
			  
			//Search for a user  
			managerFunctions.searchUserSuccessFunction(lastname);
	  		driver.findElement(By.linkText(userlist)).click();
			if(testDelay.equals("delay"))  Thread.sleep(2000);
			
			//Verify that details are displayed for the right user 
			assertEquals(userdetail,driver.findElement(By.id(prop.getProperty("SearchSpecificUser_userName_txt"))).getText());
	}
	
	@Test
	@FileParameters("./seleniumTestData/SetNoshowTestData.csv")
	public void bsetNoshowTest(int testcasenum, String noshowBeforeChange, String noshowAfterChange) throws Exception
	{
			verifyNoshow(noshowBeforeChange);
			managerFunctions.setNoshowSuccessFunction();
			verifyNoshow(noshowAfterChange);			
	}
	
	@Test
	@FileParameters("./seleniumTestData/SetOverdueTestData.csv")
	public void csetOverdueTest(int testcasenum, String overdueBeforeChange, String overdueAfterChange) throws Exception
	{
			verifyOverdue(overdueBeforeChange);
			managerFunctions.setOverdueSuccessFunction();
			verifyOverdue(overdueAfterChange);			
	}
	
	@Test
	@FileParameters("./seleniumTestData/EditViolationsTestData.csv")
	public void deditViolationTest(int testcasenum, String noShowsBeforeChange, String overdueBeforeChange,String noshows, String overdue, String noShowsAfterChange, String overdueAfterChange) throws Exception
	{ 
			verifyNoshow(noShowsBeforeChange);	
			verifyOverdue(overdueBeforeChange);
			managerFunctions.editViolationsSuccessFunction(noshows, overdue);
			verifyNoshow(noShowsAfterChange);	
			verifyOverdue(overdueAfterChange);
			goToHome();
			logout();
	}
	
	@Test
	@FileParameters("./seleniumTestData/ViewMyViolationsTestData.csv")
	public void eeditViolationTest(int testnumber, String noShowsAfterChange, String overdueAfterChange) throws Exception
	{ 
			loginTestFunctions.loginSuccessFunction("userthree", "test@123");
			driver.findElement(By.xpath(prop.getProperty("StudentFaculty_noshowsviolations_link"))).sendKeys(Keys.ENTER);
			checkViol(noShowsAfterChange,overdueAfterChange);
			driver.findElement(By.xpath(prop.getProperty("ViewViolations_Back_link"))).sendKeys(Keys.ENTER);
			driver.findElement(By.xpath(prop.getProperty("StudentFaculty_Logout_link"))).click();
	}
	
	private void verifyNoshow(String noshow) throws Exception
	{
		assertEquals(noshow,driver.findElement(By.id(prop.getProperty("SearchSpecificUser_noShows_txt"))).getText());
	}
	
	private void verifyOverdue(String overdue) throws Exception
	{
		assertEquals(overdue,driver.findElement(By.id(prop.getProperty("SearchSpecificUser_overdue_txt"))).getText());
	}
	
	private void checkViol(String noshow, String overdue) throws Exception
	{
		assertEquals(noshow,driver.findElement(By.id(prop.getProperty("ViewViolations_noshows_txt"))).getText());
		assertEquals(overdue,driver.findElement(By.id(prop.getProperty("ViewViolations_overdue_txt"))).getText());
	}
	
	private void goToHome() throws Exception {	
		driver.findElement(By.xpath(prop.getProperty("SearchSpecificUser_Home_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}
	
	private void logout() throws Exception{	
		driver.findElement(By.xpath(prop.getProperty("Manager_Logout_link"))).click();
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}
	
}




