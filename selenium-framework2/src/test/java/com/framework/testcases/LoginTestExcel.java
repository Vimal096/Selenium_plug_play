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
import com.framework.util.ExcelUtil;
import com.framework.util.ScreenshotUtil;

@Listeners(ScreenshotListener.class)
public class LoginTestExcel {

    public WebDriver driver;
    Properties props;
    String testName = "testLogin";

    @BeforeMethod
    public void setup() {
        try {
            props = new Properties();
            InputStream input = new FileInputStream("src/test/resources/config/config.properties");
            props.load(input);
            String browser = props.getProperty("browser", "edge");
            driver = DriverFactory.getDriver(browser);
            driver.manage().window().maximize();
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, testName);
            throw new RuntimeException("Setup failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void testLogin() {
        try {
            String url = props.getProperty("base.url");
            driver.get(url);

            // 🔐 Fetch credentials from Excel
            String username = ExcelUtil.getCellValue("LoginData", 1, 0); // Row 1, Col 0
            String password = ExcelUtil.getCellValue("LoginData", 1, 1); // Row 1, Col 1
            String otp = ExcelUtil.getCellValue("LoginData", 1, 2);      // Row 1, Col 2

            // ✅ Perform login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);
            loginPage.enterOtp(otp);

            Thread.sleep(3000); // Optional: For visual verification

        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, testName);
            e.printStackTrace();
            throw new RuntimeException("Test Failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
