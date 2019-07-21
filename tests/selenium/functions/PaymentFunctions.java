package selenium.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import parkingManagement.model.PaymentErrorMsgs;

public class PaymentFunctions extends SeleniumFunctionsBase {

	
	public PaymentErrorMsgs makePaymentFailure(String firstname, String lastname, String address,
			String cardNumber, String type, String expMonth, String expYear, String cvv) {
					
		driver.findElement(By.id(prop.getProperty("Payment_firstname_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_firstname_txt"))).sendKeys(firstname);
		driver.findElement(By.id(prop.getProperty("Payment_lastname_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_lastname_txt"))).sendKeys(lastname);
		driver.findElement(By.id(prop.getProperty("Payment_address_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_address_txt"))).sendKeys(address);
		driver.findElement(By.id(prop.getProperty("Payment_cardnum_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_cardnum_txt"))).sendKeys(cardNumber);
		if(!type.equals("select"))
			new Select(driver.findElement(By.id(prop.getProperty("Payment_type_select")))).selectByVisibleText(type);
		if(!expMonth.equals("select"))
			new Select(driver.findElement(By.id(prop.getProperty("Payment_ExpMonth_select")))).selectByVisibleText(expMonth);
		if(!expYear.equals("select"))
			new Select(driver.findElement(By.id(prop.getProperty("Payment_ExpYear_select")))).selectByVisibleText(expYear);
		driver.findElement(By.id(prop.getProperty("Payment_cvv_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_cvv_txt"))).sendKeys(cvv);
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		driver.findElement(By.id(prop.getProperty("Payment_pay_btn"))).sendKeys(Keys.ENTER);
		PaymentErrorMsgs paymentErrorMsgs = new PaymentErrorMsgs();
		
		paymentErrorMsgs.setPayerFirstnameError(driver.findElement(By.id(prop.getProperty("Payment_firstNameError_txt"))).getAttribute("value").toString());
		paymentErrorMsgs.setPayerLastnameError(driver.findElement(By.id(prop.getProperty("Payment_lastNameError_txt"))).getAttribute("value").toString());
		paymentErrorMsgs.setBillingAddressError(driver.findElement(By.id(prop.getProperty("Payment_addressErr_txt"))).getAttribute("value").toString());
		paymentErrorMsgs.setCardTypeError(driver.findElement(By.id(prop.getProperty("Payment_cardtypeErr_txt"))).getAttribute("value").toString());
		paymentErrorMsgs.setCardNumberError(driver.findElement(By.id(prop.getProperty("Payment_cardnumErr_txt"))).getAttribute("value").toString());
		paymentErrorMsgs.setExpiryMonthError(driver.findElement(By.id(prop.getProperty("Payment_expiryMonthErr_txt"))).getAttribute("value").toString());
		paymentErrorMsgs.setCvvError(driver.findElement(By.id(prop.getProperty("Payment_cvvErr_txt"))).getAttribute("value").toString());
		paymentErrorMsgs.setErrorMsg();
		return paymentErrorMsgs;
	}
	
	public void makeSuccessPayment(String firstname, String lastname, String address,
			String cardNumber, String type, String expMonth, String expYear, String cvv) {
					
		driver.findElement(By.id(prop.getProperty("Payment_firstname_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_firstname_txt"))).sendKeys(firstname);
		driver.findElement(By.id(prop.getProperty("Payment_lastname_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_lastname_txt"))).sendKeys(lastname);
		driver.findElement(By.id(prop.getProperty("Payment_address_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_address_txt"))).sendKeys(address);
		driver.findElement(By.id(prop.getProperty("Payment_cardnum_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_cardnum_txt"))).sendKeys(cardNumber);
		new Select(driver.findElement(By.id(prop.getProperty("Payment_type_select")))).selectByVisibleText(type);
		new Select(driver.findElement(By.id(prop.getProperty("Payment_ExpMonth_select")))).selectByVisibleText(expMonth);
		new Select(driver.findElement(By.id(prop.getProperty("Payment_ExpYear_select")))).selectByVisibleText(expYear);
		driver.findElement(By.id(prop.getProperty("Payment_cvv_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_cvv_txt"))).sendKeys(cvv);
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		driver.findElement(By.id(prop.getProperty("Payment_pay_btn"))).sendKeys(Keys.ENTER);
	}
}
