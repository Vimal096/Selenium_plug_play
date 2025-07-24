package com.framework.testcases;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.framework.SFDashboard;
import com.framework.listeners.ExtentTestListener;
import com.framework.listeners.ScreenshotListener;
import com.framework.util.DriverFactory;

@Listeners({ScreenshotListener.class, ExtentTestListener.class})
public class SFDashboardTest {

    public WebDriver driver;
    Properties props;

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
            throw new RuntimeException("Setup failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void SFDashboard() throws InterruptedException {
        String url = props.getProperty("base.url2");
        driver.get(url);

        SFDashboard sfDashboard = new SFDashboard(driver);
        Thread.sleep(3000); // temporary wait
        sfDashboard.leadDashboard();
        Thread.sleep(3000); // visual confirmation
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
