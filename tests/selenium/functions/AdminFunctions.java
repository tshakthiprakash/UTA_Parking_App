package selenium.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import parkingManagement.model.ReservationErrorMsgs;
import parkingManagement.model.UserErrorMsgs;

public class AdminFunctions extends SeleniumFunctionsBase{

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
	
	public void changeUserRoleSuccessFunction(){
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editRole_btn"))).click();
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new Select(driver.findElement(By.id(prop.getProperty("SearchSpecificUser_selectUserRole")))).selectByVisibleText("Manager");
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_changeUserRole_btn"))).sendKeys(Keys.ENTER);
	}
	
	public void changeUserRoleToStudentSuccessFunction(){
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editRole_btn"))).click();
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new Select(driver.findElement(By.id(prop.getProperty("SearchSpecificUser_selectUserRole")))).selectByVisibleText("Student/Faculty");
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_changeUserRole_btn"))).sendKeys(Keys.ENTER);
	}
	
	public UserErrorMsgs revokeUser(String username){
		driver.findElement(By.id(prop.getProperty("RevokeUser_userName_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("RevokeUser_userName_txt"))).sendKeys(username);
		driver.findElement(By.id(prop.getProperty("RevokeUser_revoke_btn"))).sendKeys(Keys.ENTER);
		
		UserErrorMsgs actualUsernameErrorMsg = new UserErrorMsgs();
		actualUsernameErrorMsg.setUsernameError(driver.findElement(By.id(prop.getProperty("RevokeUser_error_txt"))).getText());
		
		return actualUsernameErrorMsg;
	}
	
	public UserErrorMsgs activateUser(String username){
		driver.findElement(By.id(prop.getProperty("ActivateUser_search_username_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("ActivateUser_search_username_txt"))).sendKeys(username);
		driver.findElement(By.id(prop.getProperty("ActivateUser_Activate_btn"))).sendKeys(Keys.ENTER);
		
		UserErrorMsgs actualUsernameErrorMsg = new UserErrorMsgs();
		actualUsernameErrorMsg.setUsernameError(driver.findElement(By.id(prop.getProperty("ActivateUser_errordata_txt"))).getText());
	
		return actualUsernameErrorMsg;
	}


}
