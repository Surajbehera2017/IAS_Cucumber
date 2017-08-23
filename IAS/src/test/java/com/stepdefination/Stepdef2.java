package com.stepdefination;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.resources.FunctionLibrary;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdef2 extends FunctionLibrary
{

	@Given("^Login page is launched and open in the browser$")
	public void login_page_is_launched_and_open_in_the_browser() throws Throwable
	{
		driverInit("FF");
		
		//wait for browser to load
		WaitForPageLoad(driver);
		
		//Launch application
		navigate("open","https://iassit3.dl.crednet.de/AS/imap/");
		WaitForPageLoad(driver);
		Active_Windows("yes");
		Thread.sleep(2000);
	    setText("Username_XPATH", "SITDLUSER", "Login");
	    setText("Password_XPATH", "Hexaware123", "Login");
	}

	@When("^I enter the userid and password$")
	public void i_enter_the_userid_and_password() throws Throwable 
	{
	   
	   
	}

	@When("^some other action$")
	public void some_other_action() throws Throwable 
	{
	   
	   
	}

	@When("^yet another action$")
	public void yet_another_action() throws Throwable 
	{
	   
	   
	}

	@Then("^I validate the outcomes$")
	public void i_validate_the_outcomes() throws Throwable 
	{
	   
	   
	}

	@Then("^check more outcomes$")
	public void check_more_outcomes() throws Throwable 
	{
	   
	   
	}

}
