package selenium;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import selenium.functions.LoginTestFunctions;
import selenium.functions.ViewAvailSpotFunctions;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ViewAvailSpotTest extends SeleniumTestBase{
	
	static LoginTestFunctions loginTestFunctions;
	static SeleniumTestBase seleniumTestBase;
	static ViewAvailSpotFunctions viewNumAvailSpotsFunctions;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

  
  @Test
  @FileParameters("seleniumTestData/ViewAvailSpotsFailTestData.csv")
  public void test1Fail(String tcno, String ParkingAreaName, String FromTime, String ToTime, String PermitType, String FromTimeError, String ToTimeError) throws Exception {
   try{ 
	
	viewNumAvailSpotsFunctions.viewNumAvailSpotsFunction(ParkingAreaName,FromTime, ToTime,PermitType,FromTimeError,ToTimeError);
    } catch(Exception e){
	   e.printStackTrace();
   }
    
  }
  
  
  @Test
  @FileParameters("seleniumTestData/ViewAvailSpotsSuccessTestData.csv")
  public void test1Success(String tcno, String ParkingAreaName, String FromTime, String ToTime, String PermitType, String FromTimeError, String ToTimeError) throws Exception {
   try{ 
	
	viewNumAvailSpotsFunctions.viewNumAvailSpotsFunction(ParkingAreaName,FromTime, ToTime,PermitType,FromTimeError,ToTimeError);
    

	driver.findElement(By.id(prop.getProperty("ViewAvailSpot_makeSpotUnavail_btn"))).click();
	
    
   } catch(Exception e){
	   e.printStackTrace();
   }
   
    
  }
  
  @Test
  @FileParameters("seleniumTestData/MakeSpotUnavailableFailTestData.csv")
  public void test2Fail(String tcno, String ParkingAreaName, String PermitType, String spotNo,String spotNoErr){
	  try{
	  viewNumAvailSpotsFunctions.makeSpotUnavailableFunction(ParkingAreaName, PermitType, spotNo);
	  
	  String spotErr = driver.findElement(By.id(prop.getProperty("ViewAvailSpot_errMsg_txt"))).getAttribute("value").toString();
	  
	  assertEquals(spotNoErr,spotErr);
	  } catch(Exception e){
		  e.printStackTrace();
	  }
  }
  
  @Test
  @FileParameters("seleniumTestData/MakeSpotUnavailableSuccessTestData.csv")
  public void test2Success(String tcno, String ParkingAreaName, String PermitType, String spotNo,String spotNoErr){
	  try{
	  viewNumAvailSpotsFunctions.makeSpotUnavailableFunction(ParkingAreaName, PermitType, spotNo);
	  
	  String spotErr = driver.findElement(By.id(prop.getProperty("ViewAvailSpot_errMsg_txt"))).getAttribute("value").toString();
	  
	  assertEquals(spotNoErr,spotErr);
	  
	  driver.findElement(By.xpath(prop.getProperty("ViewAvailSpot_UnavailList_btn"))).click();
	  driver.findElement(By.xpath(".//*[@id='makeUnavailable_"+ParkingAreaName+"_"+PermitType+"_"+spotNo+"']")).click();
	  
	  } catch(Exception e){
		  e.printStackTrace();
	  }
	  moveToTest3();
  }
  
  @Test
  @FileParameters("seleniumTestData/ViewSpotDetailsFailTestData.csv")
  public void test3Fail(String tcno,String ParkingAreaName, String PermitType, String spotNo,String spotNoErr){
	  try{
		  viewNumAvailSpotsFunctions.viewSpotDetailsFunction(ParkingAreaName, PermitType, spotNo);
		  
		  String spotErr = driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spotErr_txt"))).getAttribute("value").toString();
		  
		  assertEquals(spotNoErr,spotErr);
	  } catch(Exception e){
		  e.printStackTrace();
	  }
  }
  
  @Test
  @FileParameters("seleniumTestData/ViewSpotDetailsSuccessTestData.csv")
  public void test3Success(String tcno,String ParkingAreaName, String PermitType, String spotNo,String spotNoErr){
	  try{
		  viewNumAvailSpotsFunctions.viewSpotDetailsFunction(ParkingAreaName, PermitType, spotNo);
		  
		  String spotErr = driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spotErr_txt"))).getAttribute("value").toString();
		  assertEquals(spotNoErr,spotErr);
		  
		  logout();
	  } catch(Exception e){
		  e.printStackTrace();
	  }
	  
  }
  
  private void moveToTest3(){
	  driver.findElement(By.xpath(prop.getProperty("ViewAvailSpot_Home_link"))).click();
	  driver.findElement(By.xpath(prop.getProperty("Manager_Spotdetails_link"))).click();
	  
  }
  
  
  
  private void logout(){
	  driver.findElement(By.xpath(prop.getProperty("ViewAvailSpot_Home_link"))).click();
	  driver.findElement(By.xpath(prop.getProperty("Manager_Logout_link"))).click();
	  //driver.close();
	  driver.quit();
	  
	    
  }
  
  private void clickMakeUnavailable(){
	  driver.findElement(By.id(prop.getProperty("ViewAvailSpot_makeSpotUnavail_btn"))).click();
  }
  @BeforeClass
  public static void setUp() throws Exception {
	  loginTestFunctions = new LoginTestFunctions();
	  viewNumAvailSpotsFunctions = new ViewAvailSpotFunctions();
	  seleniumTestBase = new SeleniumTestBase();
	  setDriver();
	  loginTestFunctions.loginSuccessFunction("johnsmith", "Test@123");
	  driver.findElement(By.xpath(prop.getProperty("Manager_Numberavailable_link"))).click();
   }

  @AfterClass
  public static void tearDownAfterTest() throws Exception {
	  //driver.quit();
  }

  
  

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
