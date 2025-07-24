package com.framework.testcases;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.framework.LoginPage;
import com.framework.listeners.ScreenshotListener;
import com.framework.util.DriverFactory;
import com.framework.util.ScreenshotUtil;

@Listeners(ScreenshotListener.class)
public class DashboardCGHS {

	public WebDriver driver;
	Properties props;
	String testName = "testLogin";
    @BeforeMethod
    public void setup() {
    	try {
    		props = new Properties();
    		InputStream input = new FileInputStream("src/test/resources/config/config.properties");
                props.load(input);
                String browser = props.getProperty("browser", "chrome");
                driver = DriverFactory.getDriver(browser);
                driver.manage().window().maximize();
    	} catch (Exception e) {
    	    ScreenshotUtil.takeScreenshot(driver, "testLoginChrome");
    	    throw new RuntimeException(e);
    	}
    }
    @Test
    public void testLogin() {
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
    	    ScreenshotUtil.takeScreenshot(driver, "testLoginChrome");
    	    throw new RuntimeException(e);
    	}
   }
    @AfterMethod
    public void tearDown() {
    	if (driver!=null) {
    		driver.quit();
    	}
    }
   }


