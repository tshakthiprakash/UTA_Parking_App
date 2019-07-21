package selenium.functions;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import parkingManagement.model.ParkingAreaErrorMsgs;
import parkingManagement.model.ReservationErrorMsgs;

public class SearchParkingSpotFunctions extends SeleniumFunctionsBase{

	
	public void searchParkingSpotWithOptions(String parkingAreaName, 
			String ParkingType, String fromTime, String toTime) {
		new Select(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_parkingName_option"))))
			.selectByVisibleText(parkingAreaName);
		new Select(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_type_option"))))
				.selectByVisibleText(ParkingType);
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_fromTime_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_fromTime_txt"))).sendKeys(fromTime);
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_toTime_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_toTime_txt"))).sendKeys(toTime);
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_selectedcart_checkbox"))).click();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_selectedcamera_checkbox"))).click();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_selectedhistory_checkbox"))).click();
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_search_btn"))).sendKeys(Keys.ENTER);
	}
	
	public List<Object> searchParkingSpotWithoutOptions(String parkingAreaName, 
			String ParkingType, String fromTime, String toTime) {
		
		new Select(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_parkingName_option"))))
		.selectByVisibleText(parkingAreaName);
		new Select(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_type_option"))))
				.selectByVisibleText(ParkingType);
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_fromTime_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_fromTime_txt"))).sendKeys(fromTime);
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_toTime_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_toTime_txt"))).sendKeys(toTime);

		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_search_btn"))).sendKeys(Keys.ENTER);
		//validate error messages
		ReservationErrorMsgs reservationErrorMsgs = new ReservationErrorMsgs();
		reservationErrorMsgs.setReservationFromError(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_fromErr_txt"))).getAttribute("value"));
		reservationErrorMsgs.setReservationToError(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_toErr_txt"))).getAttribute("value"));
		reservationErrorMsgs.setErrormsg();		
		ParkingAreaErrorMsgs parkingAreaErrorMsgs = new ParkingAreaErrorMsgs();
		parkingAreaErrorMsgs.setParkingTypeError(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_typeErr_option"))).getAttribute("value"));
		parkingAreaErrorMsgs.setParkingareaNameError(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_parkingErr_txt"))).getAttribute("value"));
		parkingAreaErrorMsgs.setErrormsg();	
		
		List<Object> arrayListErrorObjects = (List<Object>) Arrays.asList(reservationErrorMsgs, parkingAreaErrorMsgs);
		return arrayListErrorObjects;
	}
}