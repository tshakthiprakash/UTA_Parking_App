package parkingManagement.model;

import static org.junit.Assert.*;
import parkingManagement.model.User;
import parkingManagement.model.UserErrorMsgs;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
@RunWith(JUnitParamsRunner.class)

public class UserTest {
	@Test
	@FileParameters("./junitTestData/UserTestData.csv")
	public void validateUserTest(int testno, String username, String password, String confirmPass, String firstname,
			String lastname, String email, String phone, String utaid, String num_plate, String permit_id,
			String permit_type, String address, String city, String state, String user_role, String zip, String errMsg,
			String usernameErr, String firstnameErr, String lastnameErr, String passwordErr, String cpasswordErr,
			String utaidErr, String emailErr, String phoneErr, String zipErr, String num_plateErr, String permitIdErr,
			String addErr, String cityErr, String stateErr) {
			UserErrorMsgs regerrMsg  = new UserErrorMsgs();
			User userTest = new User(firstname,lastname,username,password,confirmPass,utaid,user_role,phone,email,address,city,state,zip,num_plate,permit_id,permit_type);
			userTest.validateUser(userTest, regerrMsg,"");
			assertEquals(firstnameErr,regerrMsg.getFirstnameError());
			assertEquals(lastnameErr,regerrMsg.getLastnameError());
			assertEquals(passwordErr,regerrMsg.getPasswordError());
			assertEquals(cpasswordErr,regerrMsg.getConfirmPwdError());
			assertEquals(usernameErr,regerrMsg.getUsernameError());
			assertEquals(phoneErr,regerrMsg.getPhoneError());
			assertEquals(emailErr,regerrMsg.getEmailError());
			assertEquals(cityErr,regerrMsg.getCityError());
			assertEquals(num_plateErr,regerrMsg.getCarNmbrError());
			assertEquals(permitIdErr,regerrMsg.getPermitIdError());
			assertEquals(stateErr,regerrMsg.getStateError());
			assertEquals(zipErr,regerrMsg.getZipCodeError());
			assertEquals(utaidErr,regerrMsg.getUtaIdError());
			assertEquals(addErr,regerrMsg.getStreetAddrError());
			assertEquals(errMsg,regerrMsg.getErrorMsg());
			userTest.validateUser(userTest, regerrMsg,"fortest");
	}
	@Test
	@FileParameters("./junitTestData/UserLoginTestData.csv")
	public void UserLoginTest(int testno, String username,String password,String errMsg){
		User user = new User();
		UserErrorMsgs userErrorMsgs = new UserErrorMsgs();
		user.setUsername(username);
		user.setPassword(password);
		user.validateLoginUser(user, userErrorMsgs);
		assertEquals(errMsg,userErrorMsgs.getLoginErrMsg());
	}
	@Test
	@FileParameters("./junitTestData/UserLoginPasswordTestData.csv")
	public void UserLoginPasswordTest(int testno, String username,String password,String errMsg){
		User user = new User();
		UserErrorMsgs userErrorMsgs = new UserErrorMsgs();
		user.setUsername(username);
		user.setPassword(password);
		user.validateLoginPassword(user, userErrorMsgs);
		assertEquals(errMsg,userErrorMsgs.getLoginErrMsg());
	}
	@Test
	@FileParameters("./junitTestData/SearchUserTestData.csv")
	public void SearchUserTest(int testno, String lastname, String errMsg, String comments){
		User user = new User();
		UserErrorMsgs userErrorMsgs = new UserErrorMsgs();
		user.validateSearchUserLastName(lastname, userErrorMsgs);
		assertEquals(errMsg,userErrorMsgs.getLastnameError());
	}
	
}
