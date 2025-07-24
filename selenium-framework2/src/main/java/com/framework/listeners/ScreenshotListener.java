package com.framework.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.framework.util.ScreenshotUtil;
import org.openqa.selenium.WebDriver;

public class ScreenshotListener implements ITestListener{
	
	@Override
	public void onTestFailure(ITestResult result) {
		Object testClass = result.getInstance();
		try {
			WebDriver driver = (WebDriver) result.getTestClass()
					.getRealClass()
					.getDeclaredField("driver")
					.get(testClass);
			
			ScreenshotUtil.takeScreenshot(driver, result.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
