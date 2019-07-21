package selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;

import selenium.functions.SeleniumFunctionsBase;

public class SeleniumTestBase extends SeleniumFunctionsBase{
	
	protected static String baseUrl;
	
	public SeleniumTestBase () {
		appProperties = new Properties();
		prop = new Properties();
				
		try {
			appProperties.load(new FileInputStream("./Configuration/configuration.properties"));
			testDelay = appProperties.getProperty("test_delay");
		    prop.load(new FileInputStream(appProperties.getProperty("sharedUiMap")));
		    //setDriver();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setDriver() {
		System.setProperty(appProperties.getProperty("webBrowser"), appProperties.getProperty("webDriverLocation"));
		System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    setBaseUrl(appProperties.getProperty("appUrl"));
	    driver.get(getBaseUrl());
	}

	public static String getBaseUrl() {
		return baseUrl;
	}

	public static void setBaseUrl(String baseUrl1) {
		baseUrl = baseUrl1;
	}
}
