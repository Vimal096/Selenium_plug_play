package com.framework.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
public class Locators {
	public static final By 
	USERNAME_INPUT = By.id("varUserName");
	
	public static final By 
	PASSWRD_INPUT = By.id("varPassword");
	
	public static final By 
	OTP_INPUT = By.id("varOtp");
	
	public static final By 
	LOGINBTN_INPUT = By.id("loginid");
	
	public static final By 
	OTPBTN_INPUT = By.id("validateButton");
	
	public static final By 
	EYEMAIL_INPUT = By.id("i0116");
	
	public static final By 
	EYPASSWORD_INPUT = By.id("passwordInput");
	
	public static final By 
	DASHBOARD_INPUT = By.id("06b154fe_ea86_4b0c_9144_15ac5521a5f3-5241-contentTitle");
	
	public static final By
	EYLOGIN_INPUT = By.xpath("//div[@data-test-id=\'vedant.sachdeva@in.ey.com\']");
	
	public static final By
	CLOSE_INPUT = By.xpath("//*[@id=\'appHeight\']/app-feedback-dashboard/div/div[2]/app-dropdowns/div[5]/div/div/div/div[3]/button");
	
	public static final By
	FISCALYR_INPUT = By.xpath("//select[contains(@class, 'fiscalYear')][1]");
	
	public static final By
	CYCLE_INPUT = By.xpath("//select[contains(@class, 'fiscalYearCycle')][1]");
	
	public static final By
	BOTTOMPAGE_INPUT = By.xpath("//label[@class=\'feedbackCount\'][1]");
}
