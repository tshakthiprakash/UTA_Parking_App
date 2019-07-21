package selenium.functions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import parkingManagement.model.UserErrorMsgs;

public class ManagerFunctions extends SeleniumFunctionsBase{

	public void searchUserSuccessFunction(String lastname) {
		driver.findElement(By.id(prop.getProperty("Search_lastname_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Search_lastname_txt"))).sendKeys(lastname);
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("Search_search_btn"))).sendKeys(Keys.ENTER);
	}
	
	public UserErrorMsgs searchUserFunction(String lastname) throws Exception {
		driver.findElement(By.id(prop.getProperty("Search_lastname_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Search_lastname_txt"))).sendKeys(lastname);
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			driver.findElement(By.id(prop.getProperty("Search_search_btn"))).sendKeys(Keys.ENTER);
		
//		Thread.sleep(50000);
		UserErrorMsgs userErrorMsgs = new UserErrorMsgs();
		userErrorMsgs.setLastnameError(driver.findElement(By.id(prop.getProperty("Search_lastnameError_txt"))).getText());
		System.out.println("error is : " +driver.findElement(By.id(prop.getProperty("Search_lastnameError_txt"))).getText());
		return userErrorMsgs;
		
	}
	
	public void setNoshowSuccessFunction(){
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_setNoShow_btn"))).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alertPopUp = driver.switchTo().alert();
	    assertEquals("Confirmation required",alertPopUp.getText());
	    alertPopUp.accept();
	}
	
	public void setOverdueSuccessFunction(){
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_setOverdue_btn"))).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alertPopUp = driver.switchTo().alert();
	    assertEquals("Confirmation required",alertPopUp.getText());
	    alertPopUp.accept();
	}
	
	public void editViolationsSuccessFunction(String noshow, String overdue) {
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_btn"))).sendKeys(Keys.ENTER);
		
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_noShow_txt"))).clear();
	    driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_noShow_txt"))).sendKeys(noshow);
	    
	    driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_overdue_txt"))).clear();
	    driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_overdue_txt"))).sendKeys(overdue);	
	    if(testDelay.equals("delay")) {
		    try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolationsSubmit_btn"))).sendKeys(Keys.ENTER);
	}
}
