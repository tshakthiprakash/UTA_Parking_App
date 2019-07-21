package selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import selenium.SeleniumTestBase;
import selenium.functions.addParkingSpotFunction;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.runners.MethodSorters;


@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddParkingParameterizedTest extends SeleniumTestBase {
	private StringBuffer verificationErrors = new StringBuffer();
	static addParkingSpotFunction addParkingSpot;
	static SeleniumTestBase seleniumTestBase;
	//public static String sAppURL, sSharedUIMapPath,username,password;
	  
	 @BeforeClass
		public static void setUpBeforeClass() throws Exception {
		 addParkingSpot = new  addParkingSpotFunction();
		 seleniumTestBase = new SeleniumTestBase();
		 setDriver();
	 }
	
	@AfterClass
	public static void tearDownAfterTest() throws Exception {
		driver.close();
	}
	@Test
	public void aLogin() {
		addParkingSpot.Login(driver, "johnsmith", "Test@123");
	}
  @Test
  @FileParameters("./seleniumTestData/AddParkingAreaTest.csv")
  public void addPArkingTest(int testCaseNo, String name, String floor, String capacity, String type, String nameError, String floorError, String capError) throws Exception {
	
	
	addParkingSpot.testAddParkingSpot(driver, name, floor, capacity, type);
	assertEquals(nameError,driver.findElement(By.id(prop.getProperty("Parkingarea_parknameerror_txt"))).getAttribute("value"));
	assertEquals(floorError,driver.findElement(By.id(prop.getProperty("Parkingarea_floorerror_txt"))).getAttribute("value"));
	assertEquals(capError,driver.findElement(By.id(prop.getProperty("Parkingarea_caperror_txt"))).getAttribute("value"));

  }
  
  @Test
  @FileParameters("./seleniumTestData/modifyParkingTest.csv")
  public void modifyParkingTest(int testCaseNo, String capacity, String error) throws Exception {

	  addParkingSpot.testModifyParkingSpot(driver, "NewTestParking", capacity);
	assertEquals(error,driver.findElement(By.id(prop.getProperty("Parkingarea_updateeerror_txt"))).getAttribute("value"));

  }

  @Test
  @FileParameters("./seleniumTestData/changeNameTest.csv")
  public void nameChangeTest(int testCaseNo, String name, String error) throws Exception {
	  
	  addParkingSpot.testChangeParkingSpot(driver, "NewTestParking", name);
	  Alert alertPop = driver.switchTo().alert();
	  assertEquals("Confirmation required",alertPop.getText()); 
	  alertPop.accept();
	  
	assertEquals(error,driver.findElement(By.id(prop.getProperty("Parkingarea_changenameerror_txt"))).getAttribute("value"));		 
	if(testDelay.equals("delay")) 
		Thread.sleep(1000);	
  }
  @Test
  @FileParameters("./seleniumTestData/changeNameTestSuccess.csv")
  public void nameChangeTestSuccess(int testCaseNo, String name, String error) throws Exception {
	  driver.findElement(By.xpath(prop.getProperty("Parkingarea_Logout_link"))).click();
	  addParkingSpot.Login(driver, "johnsmith", "Test@123");
	  addParkingSpot.testChangeParkingSpotSuccess(driver, "NewTestParking", name);
	  Alert alertPop = driver.switchTo().alert();
	  assertEquals("Confirmation required",alertPop.getText()); 
	  alertPop.accept();
	  
	assertEquals(error,driver.findElement(By.id(prop.getProperty("Parkingarea_changenameerror_txt"))).getAttribute("value"));		 
	if(testDelay.equals("delay")) 
		Thread.sleep(2000);
  }
  
}
