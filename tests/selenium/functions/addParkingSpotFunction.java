package selenium.functions;

import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class addParkingSpotFunction extends SeleniumFunctionsBase {

	
	 public void Login (WebDriver driver, String sUserName, String sPassword ) {
			
			// Provide user name.
			driver.findElement(By.id(prop.getProperty("Index_Username_txt"))).clear();
			driver.findElement(By.id(prop.getProperty("Index_Username_txt"))).sendKeys(sUserName);;

			 // Provide Password.
			driver.findElement(By.id(prop.getProperty("Index_Password_txt"))).clear();
			driver.findElement(By.id(prop.getProperty("Index_Password_txt"))).sendKeys(sPassword);

			 // Click on Login button.
			driver.findElement(By.id(prop.getProperty("Index_login_button"))).click();
			driver.findElement(By.xpath(prop.getProperty("Manager_AddModifyParkingArea_link"))).click();

		  }	
	 
	
	 public void testAddParkingSpot(WebDriver driver, String name, String floor, String capacity, String type) {
		 
		 	//Click on Add/Modify parking area
			driver.findElement(By.id(prop.getProperty("Parkingarea_AddParkingAreabtn_btn"))).click();
			driver.findElement(By.id(prop.getProperty("Parkingarea_park_name_txt"))).clear();
		    driver.findElement(By.id(prop.getProperty("Parkingarea_park_name_txt"))).sendKeys(name);
		    driver.findElement(By.id(prop.getProperty("Parkingarea_park_floor_txt"))).clear();
		    driver.findElement(By.id(prop.getProperty("Parkingarea_park_floor_txt"))).sendKeys(floor);
		    driver.findElement(By.id(prop.getProperty("Parkingarea_park_cap_txt"))).clear();
		    driver.findElement(By.id(prop.getProperty("Parkingarea_park_cap_txt"))).sendKeys(capacity);
		    if (type.length()!=0)
		  		new Select(driver.findElement(By.id(prop.getProperty("Parkingarea_park_type_select")))).selectByVisibleText(type);
			driver.findElement(By.id(prop.getProperty("Parkingarea_Add_btn"))).click(); 
			
	 }
	 public void testModifyParkingSpot(WebDriver driver, String name, String capacity) {
		 
		 	//Click on Add/Modify parking area
			driver.findElement(By.id(prop.getProperty("Parkingarea_modifyParkingbtn_btn"))).click();
			
		    if (name.length()!=0)
		  		new Select(driver.findElement(By.id(prop.getProperty("Parkingarea_parkingareaname_select")))).selectByVisibleText(name);
			driver.findElement(By.id(prop.getProperty("Parkingarea_Search_btn"))).click(); 
			driver.findElement(By.id(prop.getProperty("Parkingarea_edit_btn"))).click(); 
			driver.findElement(By.xpath(prop.getProperty("Parkingarea_parkCap_txt"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Parkingarea_parkCap_txt"))).sendKeys(capacity);
		    driver.findElement(By.id(prop.getProperty("Parkingarea_update_btn"))).click(); 
			
	 }
	 public void testChangeParkingSpot(WebDriver driver, String oldName, String newName) {
		 
		 	//Click on Add/Modify parking area
			driver.findElement(By.id(prop.getProperty("Parkingarea_changeNameBtn_btn"))).click();
			if (oldName.length()!=0)
		  		new Select(driver.findElement(By.id(prop.getProperty("Parkingarea_oldname_select")))).selectByVisibleText(oldName);
			driver.findElement(By.id(prop.getProperty("Parkingarea_newname_txt"))).clear();
		    driver.findElement(By.id(prop.getProperty("Parkingarea_newname_txt"))).sendKeys(newName);
		    driver.findElement(By.id(prop.getProperty("Parkingarea_change_btn"))).click();
			
	 }
	 public void testChangeParkingSpotSuccess(WebDriver driver, String oldName, String newName) {
		 
		 	//Click on Add/Modify parking area
			driver.findElement(By.id(prop.getProperty("Parkingarea_changeNameBtn_btn"))).click();
			if (oldName.length()!=0)
		  		new Select(driver.findElement(By.id(prop.getProperty("Parkingarea_oldname_select")))).selectByVisibleText(oldName);
			driver.findElement(By.id(prop.getProperty("Parkingarea_newname_txt"))).clear();
		    driver.findElement(By.id(prop.getProperty("Parkingarea_newname_txt"))).sendKeys(newName);
		    driver.findElement(By.id(prop.getProperty("Parkingarea_change_btn"))).click();
			
	 }

}
