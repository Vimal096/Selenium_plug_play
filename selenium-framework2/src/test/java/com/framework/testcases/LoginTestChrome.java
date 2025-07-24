package com.framework.testcases;

import com.framework.util.DriverFactory;
import com.framework.util.ScreenshotUtil;
import com.framework.LoginPage;
import com.framework.listeners.ExtentTestListener;
import com.framework.listeners.ScreenshotListener;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Listeners({ScreenshotListener.class, ExtentTestListener.class})
public class LoginTestChrome {

    private WebDriver driver;
    private Properties props;
    private final String testName = "testLoginChrome";

    @BeforeMethod
    public void setup() {
        try (InputStream input = new FileInputStream("src/test/resources/config/config.properties")) {
            props = new Properties();
            props.load(input);
            String browser = props.getProperty("browser", "chrome");
            driver = DriverFactory.getDriver(browser);
        } catch (Exception e) {
            throw new RuntimeException("❌ Setup failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void testLoginChrome() {
        try {
            String url = props.getProperty("base.url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String otp = props.getProperty("otp");

            driver.get(url);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);
            loginPage.enterOtp(otp);

            Thread.sleep(3000); // Optional visual confirmation

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, testName);
            System.err.println("Screenshot saved at: " + screenshotPath);
            e.printStackTrace();
            throw new RuntimeException("Test Failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver(); // Proper thread-safe cleanup
    }
}
