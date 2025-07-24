package com.framework.testcases;

import com.framework.util.DriverFactory;
import com.framework.util.ScreenshotUtil;
import com.framework.LoginPage;
import com.framework.listeners.ScreenshotListener;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Listeners(ScreenshotListener.class)
public class LoginTestEdge {

    private WebDriver driver;
    private Properties props;
    private final String testName = "testLoginEdge";

    @BeforeMethod
    public void setup() {
        try {
            props = new Properties();
            InputStream input = new FileInputStream("src/test/resources/config/config.properties");
            props.load(input);

            String browser = props.getProperty("browser", "edge"); // default to edge
            driver = DriverFactory.getDriver(browser);
        } catch (Exception e) {
            throw new RuntimeException("❌ Setup failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void testLoginEdge() {
        try {
            String url = props.getProperty("base.url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String otp = props.getProperty("otp");

            driver.get(url);

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);
            loginPage.enterOtp(otp);

            Thread.sleep(3000); // optional: for visual check only
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, testName);
            e.printStackTrace();
            throw new RuntimeException("❌ Test Failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver(); // thread-safe
    }
}