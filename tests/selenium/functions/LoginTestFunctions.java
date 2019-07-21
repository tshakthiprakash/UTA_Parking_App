package selenium.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class LoginTestFunctions extends SeleniumFunctionsBase {

	public void loginSuccessFunction(String username, String password) {
		driver.findElement(By.id(prop.getProperty("Index_Username_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Index_Username_txt"))).sendKeys(username);
		driver.findElement(By.id(prop.getProperty("Index_Password_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Index_Password_txt"))).sendKeys(password);
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.findElement(By.id(prop.getProperty("Index_login_button"))).sendKeys(Keys.ENTER);
	}

	public String loginErrorFunction(String username, String password) {
		
		driver.findElement(By.id(prop.getProperty("Index_Username_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Index_Username_txt"))).sendKeys(username);
		driver.findElement(By.id(prop.getProperty("Index_Password_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Index_Password_txt"))).sendKeys(password);
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("Index_login_button"))).sendKeys(Keys.ENTER);
		String passwordError = driver.findElement(By.id(prop.getProperty("Index_Error_password"))).getAttribute("value");
		if(passwordError!=null && !passwordError.trim().equals(""))
			return passwordError;
		return driver.findElement(By.id(prop.getProperty("Index_Error_text"))).getAttribute("value");
	}
}
