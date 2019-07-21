package selenium.functions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import parkingManagement.model.*;

public class ViewSpotDetailsFunctions extends SeleniumFunctionsBase {
	
	public String ViewSpotDetailsError(String Parkingarename,String parkingType,String spotno)
	{
		String Errmsg="";
		new Select(driver.findElement(By.id(prop.getProperty("ViewSpotDetails_parkingAreaName_option"))))
		.selectByVisibleText(Parkingarename);
		new Select(driver.findElement(By.id(prop.getProperty("ViewSpotDetails_type_option"))))
		.selectByVisibleText(parkingType);
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spot_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spot_txt"))).sendKeys(spotno);
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_search_btn"))).click();
		Errmsg = driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spotErr_txt"))).getAttribute("value").toString();
		return Errmsg;
		
	}
	public String ViewSpotDetailsErrorUnvailable(String Parkingarename,String parkingType,String spotno)
	{
		String Errmsg="";
		new Select(driver.findElement(By.id(prop.getProperty("ViewSpotDetails_parkingAreaName_option"))))
		.selectByVisibleText(Parkingarename);
		new Select(driver.findElement(By.id(prop.getProperty("ViewSpotDetails_type_option"))))
		.selectByVisibleText(parkingType);
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spot_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spot_txt"))).sendKeys(spotno);
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_search_btn"))).click();
		Errmsg = driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spotunavailable_txt"))).getText();
		return Errmsg;
		
	}
	
	public String ViewSpotDetailsErrorNoReservationTest(String Parkingarename,String parkingType,String spotno)
	{
		String Errmsg="";
		new Select(driver.findElement(By.id(prop.getProperty("ViewSpotDetails_parkingAreaName_option"))))
		.selectByVisibleText(Parkingarename);
		new Select(driver.findElement(By.id(prop.getProperty("ViewSpotDetails_type_option"))))
		.selectByVisibleText(parkingType);
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spot_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spot_txt"))).sendKeys(spotno);
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_search_btn"))).click();
		Errmsg = driver.findElement(By.id(prop.getProperty("ViewSpotDetails_noReservationErr_txt"))).getText();
		return Errmsg;
	}
	
	public String ViewSpotDetailsErrorReservationTest(String Parkingarename,String parkingType,String spotno,String username)
	{
		String Errmsg="";
		new Select(driver.findElement(By.id(prop.getProperty("ViewSpotDetails_parkingAreaName_option"))))
		.selectByVisibleText(Parkingarename);
		new Select(driver.findElement(By.id(prop.getProperty("ViewSpotDetails_type_option"))))
		.selectByVisibleText(parkingType);
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spot_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_spot_txt"))).sendKeys(spotno);
		driver.findElement(By.id(prop.getProperty("ViewSpotDetails_search_btn"))).click();
		return Errmsg;
		
	}
}
