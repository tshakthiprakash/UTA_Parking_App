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
import selenium.functions.ViewSpotDetailsFunctions;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ViewSpotDetailsTest extends SeleniumTestBase {
	static RegisterUserFunctions registerUserFunctions;
	static LoginTestFunctions loginTestFunctions;
	static SeleniumTestBase seleniumTestBase;
	static ViewSpotDetailsFunctions viewspotdetailsfunction;
	
	@BeforeClass
	public static void setUp() throws Exception {
		seleniumTestBase = new SeleniumTestBase();
	    registerUserFunctions = new RegisterUserFunctions();
	    loginTestFunctions = new LoginTestFunctions();
	    viewspotdetailsfunction = new ViewSpotDetailsFunctions();
	    setDriver();
	    driver.findElement(By.xpath(prop.getProperty("Index_Register"))).click();
	    User user = new User("spotmanager", "SpotManager", "SpotManager", "Test@123", "Test@123", "1001518112", "Manager",
				"1234567890", "spotmanager@gmail.com", "603 causley ave", "Arlington", "Texas", "76010", "8112",
				"12345678", "Basic");
		registerUserFunctions.registerUserSuccess(user);
		assertEquals("Registration Successful",driver.findElement(By.id("successMsg")).getText());
		driver.findElement(By.id("login_link")).click();
		loginTestFunctions.loginSuccessFunction("spotmanager", "Test@123");
		driver.findElement(By.xpath(prop.getProperty("Manager_Spotdetails_link"))).click();
	}
	
	@AfterClass
	public static void tearDownAfterTest() throws Exception {
		driver.close();
	}
	
	@Test
	@FileParameters("./seleniumTestData/ViewspotdeatilsErrorTestData.csv")
	public void ViewspotdetailsErrorTest(int testno,String parkName,String parktype,String spotno,String ErrMsg){
		String actualErrMsg = viewspotdetailsfunction.ViewSpotDetailsError(parkName, parktype, spotno);
		assertEquals(ErrMsg,actualErrMsg);
	}
	
	@Test
	@FileParameters("./seleniumTestData/ViewspotdeatilsErrorUnvailableTestData.csv")
	public void ViewspotdetailsErrorUnvailableTest(int testno,String parkName,String parktype,String spotno,String ErrMsg){
		String actualErrMsg = viewspotdetailsfunction.ViewSpotDetailsErrorUnvailable(parkName, parktype, spotno);
		assertEquals(ErrMsg,actualErrMsg);
	}
	
	@Test
	@FileParameters("./seleniumTestData/ViewspotdeatilsErrorNoReservationTestData.csv")
	public void ViewspotdetailsErrorNoReservationTest(int testno,String parkName,String parktype,String spotno,String ErrMsg){
		String actualErrMsg = viewspotdetailsfunction.ViewSpotDetailsErrorNoReservationTest(parkName, parktype, spotno);
		assertEquals(ErrMsg,actualErrMsg);
	}
	
	@Test
	@FileParameters("./seleniumTestData/ViewspotdeatilsErrorReservationTestData.csv")
	public void ViewspotdetailsErrorReservationTest(int testno,String parkName,String parktype,String spotno,String username){
		viewspotdetailsfunction.ViewSpotDetailsErrorReservationTest(parkName, parktype, spotno,username);
		//assertEquals(username,actualErrMsg);
	}
}