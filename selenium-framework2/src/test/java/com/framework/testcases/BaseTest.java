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

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
@Listeners(ScreenshotListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected Properties props;

    @BeforeClass
    public void setUp() {
        try (InputStream input = new FileInputStream("src/test/resources/config/config.properties")) {
            props = new Properties();
            props.load(input);

            String browser = props.getProperty("browser", "chrome");
            driver = DriverFactory.getDriver(browser);

            driver.manage().window().maximize();
        } catch (Exception e) {
            throw new RuntimeException("Setup failed: " + e.getMessage(), e);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}