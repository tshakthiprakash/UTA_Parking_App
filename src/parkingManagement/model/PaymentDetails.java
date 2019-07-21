package parkingManagement.model;

public class PaymentDetails {
	
	String payerFirstname;
	String payerLastname;
	String billingAddress;
	String cardType;
	String cardNumber;
	String expiryMonth;
	String expiryYear;
	String cvv;
	

	public PaymentDetails(String payerFirstname, String payerLastname, String billingAddress, String cardType,
			String cardNumber, String expiryMonth, String expiryYear, String cvv) {
		super();
		this.payerFirstname = payerFirstname;
		this.payerLastname = payerLastname;
		this.billingAddress = billingAddress;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.cvv = cvv;
	}
	public String getPayerFirstname() {
		return payerFirstname;
	}
	public void setPayerFirstname(String payerFirstname) {
		this.payerFirstname = payerFirstname;
	}
	public String getPayerLastname() {
		return payerLastname;
	}
	public void setPayerLastname(String payerLastname) {
		this.payerLastname = payerLastname;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public void validateUser (PaymentDetails payment, PaymentErrorMsgs errorMsgs) {
		errorMsgs.setPayerFirstnameError(validatePayerFirstname(payment.getPayerFirstname()));
		errorMsgs.setPayerLastnameError(validatePayerLastname(payment.getPayerLastname()));
		errorMsgs.setBillingAddressError(validateBillingAddress(payment.getBillingAddress()));
		errorMsgs.setCardTypeError(validateCardType(payment.getCardType()));
		errorMsgs.setCardNumberError(validateCardNumber(payment.getCardNumber()));
		errorMsgs.setExpiryMonthError(validateExpiryMonth(payment.getExpiryMonth()));
		errorMsgs.setExpiryYearError(validateExpiryYear(payment.getExpiryYear()));
		errorMsgs.setCvvError(validatecvv(payment.getCvv()));
		errorMsgs.setErrorMsg();
	}
	private String validatecvv(String cvvNo) {
		String result="";
		if(!isTextAnInteger(cvvNo)) {
			result = "cvv should be a 3-digit number";
		}
		if(cvvNo.length() != 3) {
			result="cvv should be a 3-digit number";
		}
		return result;
	}
	private String validateExpiryYear(String expYear) {
		
		if (expYear.equals("select")) 
			return "Please select a Expiry year";
		return "";
	}
	private String validateExpiryMonth(String expMonth) {
		if (expMonth.equals("select")) 
			return "Please select a Expiry month";
		
		if ((expMonth.equals("1") || expMonth.equals("2")) && expiryYear.equals("2019")) 
			return "Card is expired please use another card.";
		return "";
	}
	private String validateCardNumber(String cardno) {
		String result="";
		if(!isTextAnInteger(cardno)) {
			result = "Card number should be a 16-digit number";
		}
		if(cardno.length() != 16) {
			result="Card number should be a 16-digit number";
		}
		return result;
	}
	private String validateCardType(String cardtype) {
		if (cardtype.equals("select")) 
			return "Please select a card type";
		return "";
	}
	private String validateBillingAddress(String billingaddress) {
		String result="";
		if (billingaddress.length() == 0)
			result= "Billing Address cannot be empty";
		else if(!stringSize(billingaddress,10,60))
			result = "Billing Address should be between 10 and 60 characters long";
		return result;
	}
	private String validatePayerLastname(String lastname) {
		String result="";
		String expression = "^[a-zA-Z]*";
		if (lastname.length() == 0)
			result= "Last Name cannot be empty";
		else if(!stringSize(lastname,2,30))
			result = "Lastname name should be between 2 and 30 characters long";
		else if(!(lastname.matches(expression))) 
			result = "Last name should only have alphabets";
		return result;
	}
	private String validatePayerFirstname(String firstname) {
		String result="";
		String expression = "^[a-zA-Z]*";
		if (firstname.length() == 0)
			result= "First Name cannot be empty";
		else if(!stringSize(firstname,2,30))
			result = "Firstname should be between 2 and 30 characters long";
		else if(!(firstname.matches(expression))) 
			result = "First name should only have alphabets";
		return result;
	}
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	private boolean isTextAnInteger (String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}
}
