package com.framework;
import com.framework.util.Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class SFDashboard {
	  WebDriver driver;

	    public SFDashboard(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    public void leadDashboard() throws InterruptedException {
	    	String originalWindow = driver.getWindowHandle();
	        driver.findElement(Locators.EYLOGIN_INPUT).click();
	        Thread.sleep(2000);
	        driver.findElement(Locators.DASHBOARD_INPUT).click();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(driver -> driver.getWindowHandles().size() > 1);
	        for (String windowHandle : driver.getWindowHandles()) {
	        	if (! windowHandle.equals(originalWindow))
	        	{driver.switchTo().window(windowHandle);
	        		break;
	        	}
	        }
	        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
	        wait1.until(ExpectedConditions.elementToBeClickable(Locators.CLOSE_INPUT));
	        driver.findElement(Locators.CLOSE_INPUT).click();
	       

	        Thread.sleep(1500);
	        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait2.until(ExpectedConditions.elementToBeClickable(Locators.FISCALYR_INPUT));
	        driver.findElement(Locators.FISCALYR_INPUT).click();
	        new Select(driver.findElement(Locators.FISCALYR_INPUT)).selectByVisibleText("FY25");
	        Thread.sleep(25000);
	        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(20));
	        wait3.until(ExpectedConditions.elementToBeClickable(Locators.CYCLE_INPUT));
	        Thread.sleep(15000);
	        driver.findElement(Locators.CYCLE_INPUT).click();
	        new Select(driver.findElement(Locators.CYCLE_INPUT)).selectByVisibleText("Cycle 1");
	        Thread.sleep(1500);

	        //JavascriptExecutor js = (JavascriptExecutor) driver;
	        //js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", "//label[@class=\'feedbackCount\'][1]");
	        WebElement element = driver.findElement(By.xpath("//label[@class='feedbackCount'][1]"));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(element).perform();
	    }

	    
	}


