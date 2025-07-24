package com.framework.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.util.ExtentManager;
import com.framework.util.ScreenshotUtil;

public class ExtentTestListener implements ITestListener{

	private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
	private static ExtentReports extent = ExtentManager.getInstance();
	@Override
	public void onTestStart(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
		String testName = methodName + " @ " + timestamp;
		ExtentTest test = extent.createTest(testName);
		testThread.set(test);
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		testThread.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		testThread.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
		Object testInstance = result.getInstance();
		
		try { 
			WebDriver driver = (WebDriver) result.getTestClass()
					.getRealClass()
					.getDeclaredField("driver")
					.get(testInstance);
					
			String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
			testThread.get().addScreenCaptureFromPath(screenshotPath);
		} catch(Exception e) {
			testThread.get().log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
		}
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		testThread.get().log(Status.SKIP, "Test Skipped");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
