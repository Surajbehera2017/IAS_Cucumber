package com.stepdefination;

import com.resources.FunctionLibrary;
import com.resources.Reports;

public class Dummy extends FunctionLibrary
{
	
	public static void main(String srgs[])
	{
	Initialize();
	driverInit("FF");
	
	//wait for browser to load
	WaitForPageLoad(driver);
	
	//Launch application
	navigate("open","https://iassit3.dl.crednet.de/AS/imap/");
	WaitForPageLoad(driver);
	Active_Windows("yes");
	
    setText("Username_XPATH", "SITDLUSER", "Login");
    setText("Password_XPATH", "Hexaware123", "Login");
    
    Reports.reportclose();
	}
}
