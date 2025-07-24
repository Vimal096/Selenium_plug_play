package com.framework.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    // ✅ Thread-safe WebDriver for parallel tests
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    break;

                case "firefox":
                    try {
                        WebDriverManager.firefoxdriver().setup();
                    } catch (Exception e) {
                        System.setProperty("webdriver.gecko.driver", "C:\\DRIVERS\\geckodriver.exe");
                    }
                    driver.set(new FirefoxDriver());
                    break;

                case "edge":
                    try {
                        WebDriverManager.edgedriver().setup();
                    } catch (Exception e) {
                        System.setProperty("webdriver.edge.driver", "C:\\DRIVERS\\msedgedriver.exe");
                    }
                    driver.set(new EdgeDriver());
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    // ✅ Method to quit driver safely
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
