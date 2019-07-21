package selenium.functions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import parkingManagement.model.*;

public class MyProfileFunctions extends SeleniumFunctionsBase {
	
	public UserErrorMsgs MyProfileErrorFunction(int testno, User user) throws InterruptedException
	{
		  driver.findElement(By.id(prop.getProperty("Myprofile_Password_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_Password_txt"))).sendKeys(user.getPassword());
		  driver.findElement(By.id(prop.getProperty("Myprofile_Firstname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_Firstname_txt"))).sendKeys(user.getFirstname());
		  driver.findElement(By.id(prop.getProperty("Myprofile_Lastname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_Lastname_txt"))).sendKeys(user.getLastname());
		  driver.findElement(By.id(prop.getProperty("Myprofile_UtaId_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_UtaId_txt"))).sendKeys(user.getUta_id());
		  driver.findElement(By.id(prop.getProperty("Myprofile_EmailId_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_EmailId_txt"))).sendKeys(user.getEmail());
		  driver.findElement(By.id(prop.getProperty("Myprofile_Phone_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_Phone_num"))).sendKeys(user.getPhone());
		  driver.findElement(By.id(prop.getProperty("Myprofile_Saddress_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_Saddress_txt"))).sendKeys(user.getStreet_add());
		  driver.findElement(By.id(prop.getProperty("Myprofile_City_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_City_txt"))).sendKeys(user.getCity());
		  driver.findElement(By.id(prop.getProperty("Myprofile_State_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_State_txt"))).sendKeys(user.getState());
		  driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).sendKeys(user.getZip_code());
		  driver.findElement(By.id(prop.getProperty("Myprofile_PlateNumber_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_PlateNumber_num"))).sendKeys(user.getCar_plate_num());
		  driver.findElement(By.id(prop.getProperty("Myprofile_PermitId_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Myprofile_PermitId_num"))).sendKeys(user.getPermit_id());
		  if(testDelay.equals("delay")) Thread.sleep(2000);
		  driver.findElement(By.id(prop.getProperty("Myprofile_update_btn"))).click();
		  UserErrorMsgs actualUserErrorMsgs = new UserErrorMsgs();
		  actualUserErrorMsgs.setFirstnameError(driver.findElement(By.id(prop.getProperty("Myprofile_FirstnameError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setLastnameError(driver.findElement(By.id(prop.getProperty("Myprofile_LastnameError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setPasswordError(driver.findElement(By.id(prop.getProperty("Myprofile_PasswordError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setUtaIdError(driver.findElement(By.id(prop.getProperty("Myprofile_UtaIdError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setEmailError(driver.findElement(By.id(prop.getProperty("Myprofile_EmailError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setPhoneError(driver.findElement(By.id(prop.getProperty("Myprofile_PhoneError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setStreetAddrError(driver.findElement(By.id(prop.getProperty("Myprofile_StreetAddrError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setCityError(driver.findElement(By.id(prop.getProperty("Myprofile_CityError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setStateError(driver.findElement(By.id(prop.getProperty("Myprofile_StateError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setCarNmbrError(driver.findElement(By.id(prop.getProperty("Myprofile_CarNmbrError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setPermitIdError(driver.findElement(By.id(prop.getProperty("Myprofile_PermitIdError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setZipCodeError(driver.findElement(By.id(prop.getProperty("Myprofile_ZipCodeError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setErrorMsg();
	
		  return actualUserErrorMsgs;
		
		
	}
	public void MyProfileFunction(String password,String updatedEmail,String updatedCarNum,String UpdatedZip) throws Exception
	{
		Thread.sleep(5000);
		driver.findElement(By.id(prop.getProperty("Myprofile_Password_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Myprofile_Password_txt"))).sendKeys(password);
		assertEquals("76010", driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).getAttribute("value").toString());
		driver.findElement(By.id(prop.getProperty("Myprofile_EmailId_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Myprofile_EmailId_txt"))).sendKeys(updatedEmail);
		driver.findElement(By.id(prop.getProperty("Myprofile_PlateNumber_num"))).clear();
		driver.findElement(By.id(prop.getProperty("Myprofile_PlateNumber_num"))).sendKeys(updatedCarNum);
		driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).clear();
		driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).sendKeys(UpdatedZip);
		Thread.sleep(5000);
		driver.findElement(By.id(prop.getProperty("Myprofile_update_btn"))).click();
		Thread.sleep(5000);
		assertEquals(UpdatedZip, driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).getAttribute("value").toString());
		assertEquals(updatedEmail, driver.findElement(By.id(prop.getProperty("Myprofile_EmailId_txt"))).getAttribute("value").toString());
		driver.findElement(By.xpath(prop.getProperty("Myprofile_Logout_link"))).click();
	}

}
