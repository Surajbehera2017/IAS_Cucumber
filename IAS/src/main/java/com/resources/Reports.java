package com.resources;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.joda.time.LocalDateTime;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports{
	
	static File gb_Obj_directory;
	public static String gb_Str_Basepath;
	static int gb_Int_Location;
	public static ExtentReports reports;
	public static ExtentTest test;
	public static ExtentTest test1;
	public static int counter=1;
	
	public static FunctionLibrary funcLib = new FunctionLibrary();
	
	/****************** Get project path *********************/

	public static void getPath() {
		gb_Obj_directory = new File(".");

		try {
			gb_Str_Basepath = gb_Obj_directory.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gb_Int_Location = gb_Str_Basepath.indexOf("IAS");
		gb_Str_Basepath = gb_Str_Basepath.substring(0, gb_Int_Location);

	}
	
	public static ExtentReports getInstance() {
		if (reports == null) {
			
			getPath();
			
			reports = new ExtentReports(gb_Str_Basepath + "//IAS//src//main//resources//AutomationSuite//Reports.html",true);
			
			// optional
			reports.config().documentTitle("Automation Report").reportName("Automation Suite")
					.reportHeadline("");

			// optional
			reports.addSystemInfo("Selenium Version", "2.51").addSystemInfo("Environment", "QA");
		}
		
		return reports;
	}

	public static ExtentTest startTest(String sName, String sDesc) {
		
		//if (test == null) {
			test = getInstance().startTest(sName, sDesc);
		//}
		
		return test;
		
	}

	// returns test object
	public static ExtentTest getTest() {
		return test;

	}
	

	public static void logStep(LogStatus sStatus, String stepName, String sActual, boolean isScreenShot) 
	{
	
		try 
		{
			
		File gb_Obj_directory;
		String gb_Str_Basepath;

		String fwdSlash;
		fwdSlash = "\\";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Date date = new Date();
		String timestamp = dateFormat.format(date).toString();
		
		// TODO Auto-generated method stub
		if(LogStatus.FAIL.equals(sStatus))
				{
			if(isScreenShot)
				{
				
					gb_Obj_directory = new File(".");
					//System.out.println(gb_Obj_directory);
	
					
						gb_Str_Basepath = gb_Obj_directory.getCanonicalPath();
				
					//System.out.println(gb_Str_Basepath);
					
					String imgPath = gb_Str_Basepath + "Result" + fwdSlash
							+ "FailureScreenshots_"
							+  timestamp
							+ ".png";
	
					
					//test.log(LogStatus.PASS,stepName,sActual + test.addScreenCapture(imgPath));
					getTest().log(LogStatus.FAIL, stepName,sActual+test.addScreenCapture(funcLib.screenCapture(imgPath))); 
				}
			}
			else if(LogStatus.PASS.equals(sStatus))	
			{
			
				if(isScreenShot)
				{
				
					gb_Obj_directory = new File(".");
					//System.out.println(gb_Obj_directory);
	
					
						gb_Str_Basepath = gb_Obj_directory.getCanonicalPath();
				
					//System.out.println(gb_Str_Basepath);
					
					String imgPath = gb_Str_Basepath + "Result" + fwdSlash
							+ "FailureScreenshots_"
							+  timestamp
							+ ".png";
	
					
					//test.log(LogStatus.PASS,stepName,sActual + test.addScreenCapture(imgPath));
					getTest().log(LogStatus.PASS, stepName,sActual+test.addScreenCapture(funcLib.screenCapture(imgPath)));
				}
				else
				{
					getTest().log(LogStatus.PASS, stepName,sActual);
				}
				
				
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}


	/*public static void logStep(LogStatus sStatus, String stepName, String sActual, boolean isScreenShot) 
	
	{

		try {
			
			File gb_Obj_directory;
			String gb_Str_Basepath;

			String fwdSlash;
			fwdSlash = "\\";
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			Date date = new Date();
			String timestamp = dateFormat.format(date).toString();
			
			// TODO Auto-generated method stub
			if(LogStatus.PASS.equals(sStatus))
					{
				if(isScreenShot)
				{
					
					gb_Obj_directory = new File(".");
					//System.out.println(gb_Obj_directory);

					
						gb_Str_Basepath = gb_Obj_directory.getCanonicalPath();
				
					//System.out.println(gb_Str_Basepath);
					
					String imgPath = gb_Str_Basepath + "Result" + fwdSlash
							+ "FailureScreenshots_"
							+  timestamp
							+ ".png";

					
					//test.log(LogStatus.PASS,stepName,sActual + test.addScreenCapture(imgPath));
					getTest().log(LogStatus.FAIL, stepName,sActual+test.addScreenCapture(funcLib.screenCapture(imgPath))); 
				}
						
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
	}*/
	
	public static void reportclose() {

		if (reports != null) 
		{
			reports.endTest(test);
			reports.flush();
		}
		FunctionLibrary.driver.quit();
	}
	

	/***************auto archival***************/
	
	public static void Initialize() 
	
	{
		try 
		{
		test = startTest("Pre Requisite:", "Test Report");
		getPath();
		File dirReport = new File(gb_Str_Basepath + "//IAS//src//main//resources//AutomationSuite//Reports.html");
		File dirArchive =new File(gb_Str_Basepath + "//IAS//src//main//resources//AutomationArchive");
		File dirSuite = new File(gb_Str_Basepath + "//IAS//src//main//resources//AutomationSuite");
		
		
		if(dirReport!=null)
			
			{
			/*	if(!dirSuite.exists())
				{
					dirSuite.mkdir();
				}
				
				if(!dirArchive.exists())
				{
					dirArchive.mkdir();
				}*/
				
				//File f1 = new File("E:\\AutomationSuite\\Reports.html");
				
				//File f2 = new File("E:\\AutomationArchive");
		
				LocalDateTime now = LocalDateTime.now();
				int year = now.getYear();
				int month = now.getMonthOfYear();
				int day = now.getDayOfMonth();
				int hour = now.getHourOfDay();
				int minute = now.getMinuteOfHour();
				int second = now.getSecondOfMinute();
				
				String snewFilename1 = year + "_" + month+ "_" + day+ "_" + hour+ "_" + minute+ "_" +second;
				//System.out.println(snewFilename1);
				
				File f3 = new File(dirArchive+"\\"+snewFilename1 );
				f3.mkdir();
				FileUtils.moveFileToDirectory(dirReport, f3, true);
				
		
			}	
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public static boolean createFolder(String sFolderPath) {

		File theDir = new File(sFolderPath);
		if (!theDir.exists()) {
			theDir.mkdir();
			return true;
		} 
		else {
			return false;
		}
	}

}


