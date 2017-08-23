package com.stepdefination;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import java.sql.Driver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.resources.Reports;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/com/IAS.feature"}, 	
						glue = {"com.stepdefination"},
						//tags = {""}, 
						monochrome = true,
						plugin = {"pretty","json:target/cucumber.json"})
public class Runner1test extends Reports{
	
	//Move old reports to archive folder
	@BeforeClass
	public static void oldReportToArch()
	{
		Initialize();
	}
	
	//Closing Reports
	@AfterClass
	public static void closeReport()
	{
		
		Reports.reportclose();
	}
	
}
