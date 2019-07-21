package selenium.functions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class ViewAvailSpotFunctions extends SeleniumFunctionsBase {
	public void viewNumAvailSpotsFunction(String ParkingAreaName,String fromtime,String totime,String PermitType,String FromTimeError,String ToTimeError){
		
		new Select(driver.findElement(By.id(prop.getProperty("ViewAvailSpot_selectName1_option")))).selectByVisibleText(ParkingAreaName);
	    driver.findElement(By.id(prop.getProperty("ViewAvailSpot_fromTime_txt"))).clear();
	    driver.findElement(By.id(prop.getProperty("ViewAvailSpot_fromTime_txt"))).sendKeys(fromtime);
	    driver.findElement(By.id(prop.getProperty("ViewAvailSpot_toTime_txt"))).clear();
	    driver.findElement(By.id(prop.getProperty("ViewAvailSpot_toTime_txt"))).sendKeys(totime);
	    new Select(driver.findElement(By.id(prop.getProperty("ViewAvailSpot_selectType1_option")))).selectByVisibleText(PermitType);
	    driver.findElement(By.id(prop.getProperty("ViewAvailSpot_search_btn"))).click();
	    
	    String fromErr = driver.findElement(By.id(prop.getProperty("ViewAvailSpot_resFromErr_txt"))).getAttribute("value").toString();
	    String toErr = driver.findElement(By.id(prop.getProperty("ViewAvailSpot_resToErr_txt"))).getAttribute("value").toString();
	    
	    assertEquals(FromTimeError,fromErr);
	    assertEquals(ToTimeError,toErr);	    
	}
	
	public void makeSpotUnavailableFunction(String ParkingAreaName,String PermitType,String spotNo){
		new Select(driver.findElement(By.id(prop.getProperty("ViewAvailSpot_selectName_txt")))).selectByVisibleText(ParkingAreaName);
		new Select(driver.findElement(By.id(prop.getProperty("ViewAvailSpot_selectType_txt")))).selectByVisibleText(PermitType);
		driver.findElement(By.id(prop.getProperty("ViewAvailSpot_spot_txt"))).sendKeys(spotNo);
		driver.findElement(By.id(prop.getProperty("ViewAvailSpot_submit_btn"))).click();
	}
	
	
	public void viewSpotDetailsFunction(String ParkingAreaName,String PermitType,String spotNo){
		new Select(driver.findElement(By.id(prop.getProperty("ViewSpotDetails_parkingAreaName_option")))).selectByVisibleText(ParkingAreaName);
		new Select(driver.findElement(By.id(prop.getProperty("ViewSpotDetails_type_option")))).selectByVisibleText(PermitType);
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spot_txt"))).sendKeys(spotNo);
		
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_search_btn"))).click();
	}

}
