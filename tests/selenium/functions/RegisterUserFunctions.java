package selenium.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import parkingManagement.model.*;

public class RegisterUserFunctions extends SeleniumFunctionsBase {
	
	public UserErrorMsgs registerUserError(int testno, User user) throws InterruptedException{
			 // entering the inputs
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).sendKeys(user.getUsername());
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).sendKeys(user.getPassword());
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).sendKeys(user.getFirstname());
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).sendKeys(user.getLastname());
		  driver.findElement(By.id(prop.getProperty("Register_Cpassword_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Cpassword_txt"))).sendKeys(user.getConfirmPassword());
		  driver.findElement(By.id(prop.getProperty("Register_UtaId_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_UtaId_txt"))).sendKeys(user.getUta_id());
		  driver.findElement(By.id(prop.getProperty("Register_EmailId_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_EmailId_txt"))).sendKeys(user.getEmail());
		  driver.findElement(By.id(prop.getProperty("Register_Phone_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Phone_num"))).sendKeys(user.getPhone());
		  driver.findElement(By.id(prop.getProperty("Register_Saddress_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Saddress_txt"))).sendKeys(user.getStreet_add());
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).sendKeys(user.getCity());
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).sendKeys(user.getState());
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).sendKeys(user.getZip_code());
		  driver.findElement(By.id(prop.getProperty("Register_PlateNumber_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_PlateNumber_num"))).sendKeys(user.getCar_plate_num());
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).sendKeys(user.getPermit_id());
		  if(testDelay.equals("delay")) Thread.sleep(2000);
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  UserErrorMsgs actualUserErrorMsgs = new UserErrorMsgs();
		  actualUserErrorMsgs.setFirstnameError(driver.findElement(By.id(prop.getProperty("Register_FirstnameError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setLastnameError(driver.findElement(By.id(prop.getProperty("Register_LastnameError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setUsernameError(driver.findElement(By.id(prop.getProperty("Register_UsernameError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setPasswordError(driver.findElement(By.id(prop.getProperty("Register_PasswordError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setConfirmPwdError(driver.findElement(By.id(prop.getProperty("Register_ConfirmPasswordError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setUtaIdError(driver.findElement(By.id(prop.getProperty("Register_UtaIdError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setEmailError(driver.findElement(By.id(prop.getProperty("Register_EmailError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setPhoneError(driver.findElement(By.id(prop.getProperty("Register_PhoneError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setStreetAddrError(driver.findElement(By.id(prop.getProperty("Register_StreetAddrError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setCityError(driver.findElement(By.id(prop.getProperty("Register_CityError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setStateError(driver.findElement(By.id(prop.getProperty("Register_StateError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setCarNmbrError(driver.findElement(By.id(prop.getProperty("Register_CarNmbrError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setPermitIdError(driver.findElement(By.id(prop.getProperty("Register_PermitIdError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setZipCodeError(driver.findElement(By.id(prop.getProperty("Register_ZipCodeError_txt"))).getAttribute("value").toString());
		  actualUserErrorMsgs.setErrorMsg();
	
		  return actualUserErrorMsgs;
	
	}
	
	public void registerUserSuccess(User user) throws InterruptedException
	{
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).sendKeys(user.getUsername());
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).sendKeys(user.getPassword());
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).sendKeys(user.getFirstname());
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).sendKeys(user.getLastname());
		  driver.findElement(By.id(prop.getProperty("Register_Cpassword_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Cpassword_txt"))).sendKeys(user.getConfirmPassword());
		  driver.findElement(By.id(prop.getProperty("Register_UtaId_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_UtaId_txt"))).sendKeys(user.getUta_id());
		  driver.findElement(By.id(prop.getProperty("Register_EmailId_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_EmailId_txt"))).sendKeys(user.getEmail());
		  driver.findElement(By.id(prop.getProperty("Register_Phone_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Phone_num"))).sendKeys(user.getPhone());
		  driver.findElement(By.id(prop.getProperty("Register_Saddress_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Saddress_txt"))).sendKeys(user.getStreet_add());
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).sendKeys(user.getCity());
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).sendKeys(user.getState());
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).sendKeys(user.getZip_code());
		  driver.findElement(By.id(prop.getProperty("Register_PlateNumber_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_PlateNumber_num"))).sendKeys(user.getCar_plate_num());
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).sendKeys(user.getPermit_id());
		  new Select(driver.findElement(By.id(prop.getProperty("Register_UserRole_txt")))).selectByVisibleText(user.getRole());
		  if(testDelay.equals("delay")) Thread.sleep(2000);
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  
	}

}
