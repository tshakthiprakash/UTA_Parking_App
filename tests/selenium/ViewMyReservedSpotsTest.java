package selenium;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import selenium.functions.LoginTestFunctions;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ViewMyReservedSpotsTest extends SeleniumTestBase{
	static LoginTestFunctions loginTestFunctions;
	static SeleniumTestBase seleniumTestBase;
	
	@BeforeClass
	public static void setUp() throws Exception {
		seleniumTestBase = new SeleniumTestBase();
	    loginTestFunctions = new LoginTestFunctions();
	    
	    setDriver();
	    
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.close();
	}

	@Test
	@FileParameters("./seleniumTestData/ViewReservedSpotsTestData.csv")
	public void aReserveParkingToManage(int testno, String username, String password, String errorMsg) throws Exception {
		
		loginTestFunctions.loginSuccessFunction(username, password);
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_viewReservation_link"))).click();
		if(!(errorMsg).equals("")) {
			assertEquals(errorMsg, driver.findElement(By.id(prop.getProperty("ViewMyReservedSpots_error"))).getAttribute("value"));
		} else {		
			validateReservation();
		}
		if(testDelay.equals("delay")) Thread.sleep(2000);
		driver.get("http://localhost:8080/ParkingManagement/student_faculty.jsp");
		logout();
	}

	private void validateReservation() {
		assertEquals("Maverick", driver.findElement(By.id(prop.getProperty("ViewMyReservedSpots_parkingName_txt"))).getText());
		assertEquals("Basic", driver.findElement(By.id(prop.getProperty("ViewMyReservedSpots_type_txt"))).getText());
		assertEquals("1", driver.findElement(By.id(prop.getProperty("ViewMyReservedSpots_floor_txt"))).getText());
	}

	private void logout() throws Exception{
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_Logout_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}

}
