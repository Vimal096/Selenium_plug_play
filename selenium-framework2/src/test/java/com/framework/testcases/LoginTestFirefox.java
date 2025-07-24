package com.framework.testcases;

import com.framework.util.DriverFactory;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.openqa.selenium.WebDriver;
import com.framework.util.ScreenshotUtil;
import com.framework.LoginPage;
import com.framework.listeners.ScreenshotListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
@Listeners(ScreenshotListener.class)
public class LoginTestFirefox {
	public WebDriver driver;
	Properties props;
	String testName = "testLoginFirefox";
    @BeforeMethod
    public void setup() {
    	try {
    	
    		InputStream input = new FileInputStream("src/test/resources/config/config.properties");
    		props = new Properties();
                props.load(input);
                String browser = props.getProperty("browser", "firefox");
                driver = DriverFactory.getDriver(browser);
                driver.manage().window().maximize();
    	} catch (Exception e) {
    		throw new RuntimeException("Setup failed: " + e.getMessage(), e);
    	}
    }
    @Test
    public void testLoginFirefox() {
    	try {
    	       String url = props.getProperty("base.url");
    	        String username = props.getProperty("username");
    	        String password = props.getProperty("password");
    	        String otp = props.getProperty("otp");
    	        
    	        driver.get(url);
    	        LoginPage loginPage = new LoginPage(driver);
    	        loginPage.login(username, password);
    	        loginPage.enterOtp(otp);
    	        Thread.sleep(3000); // for visual confirmation
    } catch (Exception e) {
    	ScreenshotUtil.takeScreenshot(driver, testName);
    	e.printStackTrace();
    	throw new RuntimeException("Test Failed: " + e.getMessage());
    }
   }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver(); // thread-safe
    }
}

