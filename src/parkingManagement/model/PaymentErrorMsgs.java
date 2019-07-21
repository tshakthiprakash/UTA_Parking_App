package parkingManagement.model;

public class PaymentErrorMsgs {

	String payerFirstnameError;
	String payerLastnameError;
	String billingAddressError;
	String cardTypeError;
	String cardNumberError;
	String expiryMonthError;
	String expiryYearError;
	String cvvError;
	String errorMsg;
	
	public PaymentErrorMsgs () {
		this.errorMsg="";
		this.payerFirstnameError="";
		this.payerLastnameError="";
		this.billingAddressError="";
		this.cardTypeError="";
		this.cardNumberError="";
		this.expiryMonthError="";
		this.expiryYearError="";
		this.cvvError="";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg() {
		if (!payerFirstnameError.equals("") || !payerLastnameError.equals("") || !billingAddressError.equals("") || 
				!cardTypeError.equals("") || !cardNumberError.equals("") || !expiryMonthError.equals("") || 
				!expiryYearError.equals("") || !cvvError.equals(""))
			errorMsg="Please correct the following errors";
	}
	
	public String getPayerFirstnameError() {
		return payerFirstnameError;
	}
	public void setPayerFirstnameError(String payerFirstnameError) {
		this.payerFirstnameError = payerFirstnameError;
	}
	public String getPayerLastnameError() {
		return payerLastnameError;
	}
	public void setPayerLastnameError(String payerLastnameError) {
		this.payerLastnameError = payerLastnameError;
	}
	public String getBillingAddressError() {
		return billingAddressError;
	}
	public void setBillingAddressError(String billingAddressError) {
		this.billingAddressError = billingAddressError;
	}
	public String getCardTypeError() {
		return cardTypeError;
	}
	public void setCardTypeError(String cardTypeError) {
		this.cardTypeError = cardTypeError;
	}
	public String getCardNumberError() {
		return cardNumberError;
	}
	public void setCardNumberError(String cardNumberError) {
		this.cardNumberError = cardNumberError;
	}
	public String getExpiryMonthError() {
		return expiryMonthError;
	}
	public void setExpiryMonthError(String expiryMonthError) {
		this.expiryMonthError = expiryMonthError;
	}
	public String getExpiryYearError() {
		return expiryYearError;
	}
	public void setExpiryYearError(String expiryYearError) {
		this.expiryYearError = expiryYearError;
	}
	public String getCvvError() {
		return cvvError;
	}
	public void setCvvError(String cvvError) {
		this.cvvError = cvvError;
	}
}
