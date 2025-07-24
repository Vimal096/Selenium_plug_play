package com.framework.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent = createInstance();
	
	public static ExtentReports createInstance() {
		String reportPath = "./reports/TestReport.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		return extent;
	}
	public static ExtentReports getInstance() {

		return extent;
	}
}
