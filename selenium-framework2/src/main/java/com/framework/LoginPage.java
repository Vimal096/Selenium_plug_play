package com.framework;
import com.framework.util.Locators;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) throws InterruptedException {
    	driver.findElement(Locators.USERNAME_INPUT).sendKeys(username);
    	Thread.sleep(1500);
    	driver.findElement(Locators.PASSWRD_INPUT).sendKeys(password);
    	Thread.sleep(1500);
        driver.findElement(Locators.LOGINBTN_INPUT).click();
        
    }

    public void enterOtp(String otp) throws InterruptedException {
    	driver.findElement(Locators.OTP_INPUT).sendKeys(otp);
    	Thread.sleep(1500);
    	driver.findElement(Locators.OTPBTN_INPUT).click();
    }
    
}
