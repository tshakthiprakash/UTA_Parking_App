package parkingManagement.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
@RunWith(JUnitParamsRunner.class)

public class PaymentDetailsTest {
	@FileParameters("./junitTestData/PaymentTestData.csv")
	@Test
	public void validateUsertest(String tcno, String payerFirstname, String payerLastname, 
			String billingAddress, String cardType, String cardNumber, String expiryYear,
			String expiryMonth, String cvv, String FirstnameError, String LastnameError, 
			String AddressError, String CardTypeError, String CardNumberError, 
			String ExpiryYearError, String ExpiryMonthError, String CvvError, String ErrMsg) {
	
		PaymentDetails paymentDetails = new PaymentDetails(payerFirstname, payerLastname, billingAddress, 
				cardType, cardNumber, expiryMonth, expiryYear, cvv);
		PaymentErrorMsgs paymErr = new PaymentErrorMsgs();
		paymentDetails.validateUser(paymentDetails, paymErr);
		assertEquals(FirstnameError, paymErr.getPayerFirstnameError());
		assertEquals(LastnameError, paymErr.getPayerLastnameError());
		assertEquals(AddressError, paymErr.getBillingAddressError());
		assertEquals(CardTypeError, paymErr.getCardTypeError());
		assertEquals(CardNumberError, paymErr.getCardNumberError());
		assertEquals(ExpiryYearError, paymErr.getExpiryYearError());
		assertEquals(ExpiryMonthError, paymErr.getExpiryMonthError());
		assertEquals(CvvError, paymErr.getCvvError());
		assertEquals(ErrMsg,paymErr.getErrorMsg());
		
	}

}
