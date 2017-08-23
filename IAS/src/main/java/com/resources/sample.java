package com.resources;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class sample extends FunctionLibrary{
	
	public static void main(String args[])
	{
		test = startTest("Test", "Test Report");
		driverInit("FF");
		navigate("open", "https://iassit3.dl.crednet.de/AS/imap/");
		/*ExtentReports reports = new ExtentReports("D:\\sample\\Reports.html",true);
		ExtentTest test = reports.startTest("Sample Report", "Sample Description");
		test.log(LogStatus.PASS, "Step1", "Desc1");
		test.log(LogStatus.FAIL, "Step2", "Desc2");
		test.log(LogStatus.PASS, "Step3", "Desc3");
		test.log(LogStatus.FAIL, "Step4", "Desc5");
		
		reports.endTest(test);
		//reports.flush();
		//reports.close();
		
		ExtentTest test1 = reports.startTest("Sample Report1", "Sample Description1");
		test1.log(LogStatus.PASS, "Step1", "Desc11");
		test1.log(LogStatus.FAIL, "Step2", "Desc22");
		test1.log(LogStatus.PASS, "Step3", "Desc33");
		
	
		reports.endTest(test1);
		reports.flush();
		reports.close();*/
		//Reports.close();
		
		FluentWait wait = new FluentWait(driver).withTimeout(120, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		/*WebElement foo = wait.until
						(
						new Function()
						{    
				public WebElement apply(WebDriver driver) 
				{    
				return driver.findElement(By.id("foo"));    
				}
			});*/
		
		Active_Windows("yes");
	    setText("Username_XPATH", "SITDLUSER", "Login");
	    setText("Password_XPATH", "Hexaware123", "Login");
	    click("Login_XPATH", "Login");
		
		
		
		
	}
	
	
}
