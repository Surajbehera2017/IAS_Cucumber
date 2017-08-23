/*package com.stepdefination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.resources.FunctionLibrary;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Stepdef1 extends FunctionLibrary {
	public String browser_list [ ]; 

	@Before
	public void beforeexc()
	{
		System.out.println("before");
	}
	
	@After
	public void browser_Close()
	{
		//browser(driver, "quit");
		//close();
	}
	

	@Given("^Launch the browser open mercury tours page$")
	public void launch_the_browser_open_mercury_tours_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	
	@Given("^launch jetblue Website \"([^\"]*)\" and \"([^\"]*)\"$")
	public void launch_jetblue_Website_and(String browser,String scenario_name) throws Throwable {
		
	
		test = startTest(scenario_name, "Test Report");
		
		driverInit(browser);
		
		//wait for browser to load
		WaitForPageLoad(driver);
		
		//Launch application
		navigate("open","https://iassit3.dl.crednet.de/AS/imap/");
		
		//wait for jetblue application to load
		WaitForPageLoad(driver);
		
		
		
	}
	//Scenario 1: TravelInfo

		@When("^click on Travel Information tab$")
		public void click_on_Travel_Information_tab() throws Throwable {
				
			//click on travel info tab
			click(driver,"travelInfo_XPATH","travelInfo");
		   
			//wait for page to load 
			WaitForPageLoad(driver);
		   
		}
		
		@Then("^the travel informations should be listed$")
		public void the_travel_informations_should_be_listed() throws Throwable {
			
			//Verify Travel Information contents
		   isElementPresent(driver, "baggage_XPATH","travelInfo");
		   isElementPresent(driver, "cancelAndDelay_XPATH","travelInfo");
		   isElementPresent(driver, "specialAss_XPATH","travelInfo");
		   isElementPresent(driver, "kids_XPATH","travelInfo");
		   isElementPresent(driver, "airport_XPATH","travelInfo");
		   isElementPresent(driver, "homeSweet_XPATH","travelInfo");
		   
		}
	

	//Scenario 2: SuggestionEmail

		@When("^click on contactus link and Write us an email option and select suggestion \"([^\"]*)\"$")
		public void click_on_contactus_link_and_Write_us_an_email_option_and_select_suggestion(String topic) throws Throwable {
		   
			//click contactUs link
			click(driver,"contactUs_XPATH","suggestionEmail");
			
			//wait for page to load 
			WaitForPageLoad(driver);
			   
			//click write us an email option
			click(driver,"emailLink_XPATH","suggestionEmail");
			
			//wait for page to load 
			WaitForPageLoad(driver);
					
			//click suggestion link
			click(driver,"suggestionLink_XPATH","suggestionEmail");
			
			Thread.sleep(2000);
			//select suggestion topic
			click(driver,"select_XPATH","suggestionEmail");
			Thread.sleep(5000);
			click(driver,"selectTopic_XPATH","suggestionEmail");
			
			//wait for page to load 
			WaitForPageLoad(driver);
			
			click(driver,"continue2_XPATH","suggestionEmail");
			
			//wait for page to load 
			WaitForPageLoad(driver);
			
		}
		

		@When("^give details as \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
		public void give_details_as_and_and(String firstName, String lastName, String email) throws Throwable {
			
			Thread.sleep(2000);
			
			//Enter user details
			setText(driver, "firstName_XPATH",firstName,"suggestionEmail");
			setText(driver, "lastName_XPATH", lastName,"suggestionEmail");
			setText(driver, "email_XPATH", email,"suggestionEmail");
			setText(driver, "confirmEmail_XPATH",email,"suggestionEmail");
			click(driver,"continue3_XPATH","property");
			
			//wait for page to load 
			Thread.sleep(2000);
		}
		
		@Then("^give suggestion as \"([^\"]*)\" and submit$")
		public void give_suggestion_as_and_submit(String suggestion) throws Throwable {

			//Enter Suggestion
			setText(driver, "suggestionBox_XPATH", suggestion,"suggestionEmail");
			//click(driver,"submit_XPATH","suggestionEmail");
			
			Thread.sleep(3000);
			
			//Verify the submission of suggestion
			//isElementPresent(driver,"suggConfirmation_XPATH","suggestionEmail");
			
		}
		
	//Scenario 3: Fare Alerts

		@When("^search for email preference as follows$")
		public void search_for_email_preference_as_follows(List<Email_Preference> details) throws Throwable {
			
			//getting data from dataTable
			Email_Preference obj = details.get(0);
			
			WaitForPageLoad(driver);
			Thread.sleep(3000);
	
			//movetoelement(driver, "plantrip_XPATH", "move", "fareAlerts");
			
			navigate(driver,"open","https://email.jetblue.com/pub/sf/ResponseForm?_ri_=X0Gzc2X%3DWQpglLjHJlYQGhWOza03y15zeet0zc9Y1dguN8r4RVXMtX%3DWQpglLjHJlYQGnwfk4JwsiGXg6AzgmLHi3WMaNO&_ei_=Ep5_0VeoLHIo_bipfWjEfF0");
			
			//click FareAlerts 
			//click(driver,"fareAlerts_XPATH","fareAlerts");
			Thread.sleep(2000);
			
			//Entering details for email preference
			setText(driver,"firstNameFare_ID",obj.firstName,"fareAlerts");
			Thread.sleep(1000);
			setText(driver,"lastNameFare_ID",obj.lastName,"fareAlerts");
			Thread.sleep(1000);
			select(driver,"selectHomeAirport_ID","index","2","fareAlerts");
			Thread.sleep(1000);
			setText(driver,"emailAdd_ID",obj.emailAdd,"fareAlerts");
			Thread.sleep(1000);
			setText(driver,"dobMM_ID",String.valueOf(obj.dobMM),"fareAlerts");
			Thread.sleep(1000);
			setText(driver,"dobDD_ID",String.valueOf(obj.dobDD),"fareAlerts");
			Thread.sleep(1000);
			setText(driver,"dobY_ID",String.valueOf(obj.dobY),"fareAlerts");
			
			
			//click submit for email preference
			
			click(driver,"submitPreference_XPATH","fareAlerts");
			if(isElementPresent(driver, "submitPreference_XPATH","fareAlerts"))
			{
				click(driver,"submitPreference_XPATH","fareAlerts");
			}
			Thread.sleep(2000);
			
			
			
		}
		
		@Then("^the email preference should be accepted$")
		public void the_email_preference_should_be_accepted() throws Throwable {
		   
			//wait for BestFare Finder page to load
			WaitForPageLoad(driver);
			
			
			//Verify the confirmation message
			Thread.sleep(2000);
			 isElementPresent(driver, "confirmationMsg_XPATH", "fareAlerts");
		}

		private class Email_Preference{
			String firstName;
			String lastName;
			String emailAdd;
			int dobMM;
			int dobDD;
			int dobY;
			String homeAirport;
		}
		
	//Scenario 4: TimeTable
		
		@When("^click on Timetable and entering search details as \"([^\"]*)\" and \"([^\"]*)\"$")
		public void click_on_Timetable_and_entering_search_details_as_and(String departure_City, String arrival_City) throws Throwable {
		    
			//move to timetable tab
			navigate(driver,"open","https://b6.innosked.com/(S(kwgjkd452nfrefqgfg0sphvl))/default.aspx");
			
			//wait for page to load 
			WaitForPageLoad(driver);
			Thread.sleep(2000);
		
			//Move to frame
			//WebElement element = getElement(driver, "timetableIframe_XPATH", "timeTable");
			//frames(driver, element);
			
			click(driver,"onewayTimeTable_ID","timeTable");
			Thread.sleep(2000);
			select(driver,"selectDeparture_XPATH","text",departure_City,"timeTable");
			Thread.sleep(2000);
			select(driver, "selectArrival_XPATH", "text", arrival_City,"timeTable");
			Thread.sleep(2000);
			click(driver,"search_ID","timeTable");
			Thread.sleep(2000);
			if(isElementPresent(driver, "search_ID","timeTable"))
			{
				click(driver,"search_ID","timeTable");
			}
			
			
		}
		

		@Then("^flight details should be visible to the user as \"([^\"]*)\" and \"([^\"]*)\"$")
		public void flight_details_should_be_visible_to_the_user_as_and(String departure_City, String arrival_City) throws Throwable {
		    
			//wait for page to load 
			WaitForPageLoad(driver);
			Thread.sleep(6000);
			
			String departure = getText(driver, "departureResult_XPATH", "timeTable");
			String arrival = getText(driver, "arrivalResult_XPATH", "timeTable");
			Thread.sleep(2000);
			if(departure_City.contains(departure) && arrival_City.contains(arrival))
			{
				System.out.println("Timetable result page displayed");
			}
			
		}
				
	//Scenario 5: Login
		@When("^giving credentials as \"([^\"]*)\" and \"([^\"]*)\"$")
		public void giving_credentials_as_and(String email, String password) throws Throwable {
			
			//click signIn button
			click(driver,"signInButton_XPATH","loginJetBlue");
			
			WaitForPageLoad(driver);
			Thread.sleep(3000);
			
			//Enter credentials
			setText(driver,"email_ID",email,"loginJetBlue");
			Thread.sleep(1000);
			setText(driver,"password_XPATH",password,"loginJetBlue");
			
		}
		
		@Then("^click on signin button$")
		public void click_on_signin_button() throws Throwable {
			
			Thread.sleep(5000);
			//click signIn
			click(driver,"signIn_XPATH","loginJetBlue");
			WaitForPageLoad(driver);
			Thread.sleep(3000);
		}
		
		@Then("^user should be logged in$")
		public void user_should_be_logged_in() throws Throwable {
			
			//Verify signIn
			//isElementPresent(driver,"signOut_XPATH","loginJetBlue"); 
		   
		}


	//Scenario 6: BookHotel
		
		@When("^search for hotel and select hotel specification as follows:$")
		public void search_for_hotel_and_select_hotel_specification_as_follows(List<get_data> hotel) throws Throwable {
			
			get_data obj = hotel.get(0);
			
			//navigate to hotel booking option
			navigate(driver,"open","https://www.jetblue.com/hotels/");
			
			WaitForPageLoad(driver);
			Thread.sleep(3000);
			
		   
		   //set destination
			setText(driver,"destination_ID",obj.destination,"hotelBooking");
			
			select(driver,"rooms_XPATH","text",obj.rooms,"hotelBooking");
			Thread.sleep(3000);

			//click search
			click(driver,"search_XPATH","hotelBooking");	
			Thread.sleep(1000);
			
		}
		
		
		@When("^give details to checkIn and to make a payment as follows:$")
		public void give_details_to_checkIn_and_to_make_a_payment_as_follows(List<get_data> hotel) throws Throwable {
			
			//getting data from dataTable
			get_data obj = hotel.get(0);
			
			WaitForPageLoad(driver);
			Thread.sleep(3000);
			
			//select hotel 
			click(driver,"selectHotel_XPATH","hotelBooking");
			
			WaitForPageLoad(driver);
			Thread.sleep(5000);
			
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		   for(String tab:tabs2)
		   {
			   driver.switchTo().window(tab);
			   System.out.println(driver.getTitle());
		   }
		   
		   //page loading
		   WaitForPageLoad(driver);
		   Thread.sleep(3000);
			   
		   //book a room from the selected hotel
		   click(driver,"bookHotel_XPATH","hotelBooking");
			
		   WaitForPageLoad(driver);
		   Thread.sleep(5000);
		   
		   
		   WebElement e = getElement(driver, "popUp_XPATH","hotelBooking");
		   if(e.isDisplayed())
		   {
			   click(driver,"popUp_XPATH","hotelBooking");		   
		   }
		   
		
		   click(driver,"popUp_XPATH","hotelBooking");
		  
		   //page loading
		   WaitForPageLoad(driver);
		   Thread.sleep(5000);
			
			//Enter details to checkIn
			setText(driver, "firstName_ID", obj.firstName,"hotelBooking");
			Thread.sleep(2000);
			click(driver, "secondName_ID","hotelBooking");
			setText(driver, "secondName_ID", obj.lastName,"hotelBooking");
			Thread.sleep(2000);
			setText(driver, "firstname2_ID", obj.firstName,"hotelBooking");
			Thread.sleep(2000);
			setText(driver, "lastname2_ID", obj.lastName,"hotelBooking");
			select(driver,"selectCardType_ID","text",obj.cardType,"hotelBooking");
			Thread.sleep(2000);
			setText(driver, "cardNo1_ID", "4111111111111111","hotelBooking");
			Thread.sleep(2000);
			setText(driver, "cvvNo_ID", String.valueOf(obj.securityCode),"hotelBooking");
			Thread.sleep(2000);
			select(driver,"expM_ID","value",String.valueOf(obj.expM),"hotelBooking");
			Thread.sleep(2000);
			select(driver,"expY_ID","value",String.valueOf(obj.expY),"hotelBooking");
			Thread.sleep(2000);
			select(driver,"countryCode_ID","text",String.valueOf(obj.country),"hotelBooking");
			Thread.sleep(2000);
			setText(driver,"zipCode_ID", String.valueOf(obj.zipCode),"hotelBooking");
			Thread.sleep(2000);
			setText(driver, "email2_ID", obj.email,"hotelBooking");
			Thread.sleep(2000);
			setText(driver, "phnNo1_XPATH", String.valueOf(obj.phn),"hotelBooking");
			Thread.sleep(2000);
		}
		
		@Then("^the hotel should be booked$")
		public void the_hotel_should_be_booked( ) throws Throwable {
			
			//click book
			click(driver,"book_ID","hotelBooking");
			
			Thread.sleep(3000);
			
			//click on continue button to book the same hotel again
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
			
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		   for(String tab:tabs2)
		   {
			   driver.switchTo().window(tab);
			   System.out.println(driver.getTitle());
		   }
		   Thread.sleep(3000);
		   
		   //page loading
		  // WaitForPageLoad(driver);
		   
		   if(isElementPresent(driver, "sameHotelPopup_XPATH", "hotelBooking")){
			   click(driver,"sameHotelPopup_XPATH","property");
			}
			
			Thread.sleep(3000);
			//verify the confirmation message
			isElementPresent(driver, "hotelConfirmation_XPATH", "hotelBooking");
		}

		//DataTable for BookHotel scenario
		private class get_data{
			String destination;
			String rooms;
			int checkIn;
			int checkOut;
			String firstName;
			String lastName;
			String cardType;
			int cardNo;
			int securityCode;
			int expM;
			int expY;
			String country;
			int zipCode;
			String email;
			int phn;
		}


	
	
	//Scenario 8:Deals
		
		@When("^search best travel deals$")
		public void search_best_travel_deals() throws Throwable {
			
			//move to deals page
			navigate(driver, "open","https://www.jetblue.com/deals/");
			
			//wait for Deals page to load
			WaitForPageLoad(driver);
			Thread.sleep(10000);
			
			//select the deal
			click(driver,"dealsTo_XPATH","deals");
			
			//wait for BestFare Finder page to load
			WaitForPageLoad(driver);
			
			
			Thread.sleep(5000);
			//select depart date and arrival date
			movetoelement(driver, "moveToDay_XPATH", "move", "deals");
			click(driver,"departDay_XPATH","property");
			Thread.sleep(2000);
			movetoelement(driver, "moveToDay1_XPATH", "move", "deals");
			click(driver,"arrivalDay_XPATH","property");
			Thread.sleep(2000);
			
			 //click continue in popup
			if(isElementPresent(driver, "continueDeal_XPATH", "deals")){
				click(driver,"continueDeal_XPATH","deals");
			
			}
			
		
		}
		@When("^give travellers details and select seat as follows$")
		public void give_travellers_details_and_select_seat_as_follows(List<UserDetails> User) throws Throwable {
			UserDetails obj_Details = User.get(0);
		
			//wait to laod page 
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='waiting']")));
			
			//wait to 
			WaitForPageLoad(driver);
			Thread.sleep(10000);
			
			//select return flight
			click(driver,"returningFlight_XPATH","deals");	
			Thread.sleep(10000);
			
			
			//select departure flight
			click(driver,"departFlight_XPATH","deals");	
			
			click(driver,"continue_XPATH","property");
			Thread.sleep(3000);
			
			if(isElementPresent(driver, "popupcontinue_XPATH", "deals"))
			{
				click(driver,"popupcontinue_XPATH","deals");
			}
			 WaitForPageLoad(driver);
			 Thread.sleep(10000);
			 
			 //Entering Traveller details
			select(driver,"selectTitle_NAME","text",obj_Details.title,"deals");
			Thread.sleep(1000);
			setText(driver,"firstname_ID",obj_Details.firstname,"deals");
			Thread.sleep(1000);
			setText(driver,"secondname_ID",obj_Details.lastname,"deals");
			Thread.sleep(1000);
			click(driver,"gender_ID","deals");
			Thread.sleep(1000);
			select(driver, "selectDOBm_ID", "text", obj_Details.dobM,"deals");
			Thread.sleep(1000);
			select(driver, "selectDOBd_ID", "text", String.valueOf(obj_Details.dobD),"deals");
			Thread.sleep(1000);
			select(driver, "selectDOBy_ID", "text", String.valueOf(obj_Details.dobY),"deals");
			Thread.sleep(1000);
			setText(driver,"address_ID",obj_Details.add,"deals");
			Thread.sleep(1000);
			setText(driver,"city_ID",obj_Details.city,"deals");
			Thread.sleep(1000);
			select(driver, "selectstate_ID", "text",obj_Details.state,"deals");
			Thread.sleep(1000);
			setText(driver, "zip_ID", String.valueOf(obj_Details.zip),"deals");
			Thread.sleep(1000);
			setText(driver,"email_NAME",obj_Details.email,"deals");
			Thread.sleep(1000);
			setText(driver,"confirmEmail_ID",obj_Details.confirm_email,"deals");
			Thread.sleep(1000);
			setText(driver,"phnNo_ID",String.valueOf(obj_Details.phn),"deals");
			Thread.sleep(1000);
			click(driver,"continue1_XPATH","deals");
			 
			WebDriverWait wait1 = new WebDriverWait(driver,30);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='SkipSeatSelectLink']/span/span")));
			Thread.sleep(10000);
			
			//skip seat selection
			click(driver,"skipSeat_XPATH","deals");
			
			 WaitForPageLoad(driver);
			 Thread.sleep(10000);
			click(driver,"continue2_XPATH","deals");
			
		}
		
		@When("^give payment details as follows:$")
		public void give_payment_details_as_follows(List<UserDetails> paymentDetails) throws Throwable {
			
			UserDetails obj_Details = paymentDetails.get(0);
			
			 WaitForPageLoad(driver);
			 Thread.sleep(10000);
			
			//check insurance and credit card
			click(driver, "insuranceChck_NAME","deals");
			Thread.sleep(3000);
			if(isElementNotPresent(driver, "selectCardType_ID", "deals"))
			{
				click(driver, "selectCredit_ID","deals");
			}
			
			Thread.sleep(5000);
			//Enter details for make a payment
			select(driver, "selectCardType_ID", "text", obj_Details.cardType,"deals");
			Thread.sleep(2000);
			setText(driver, "cardNo_ID", String.valueOf(obj_Details.cardNo),"deals");
			Thread.sleep(2000);
			select(driver, "selectCardExpiryMon_ID", "text", obj_Details.expM,"deals");
			Thread.sleep(3000);
			select(driver, "selectCardExpiryYr_ID", "text",String.valueOf(obj_Details.expY) ,"deals");
			Thread.sleep(2000);
			setText(driver, "securityCode_ID", "123","deals");
			Thread.sleep(2000);
			setText(driver, "cardHolderName_ID", "xxx","deals");
			
			Thread.sleep(2000);
			//Checkbox
			click(driver,"billingAddChck_ID","deals");
			Thread.sleep(2000);
			click(driver,"agreechck_ID","deals");
			Thread.sleep(2000);
			click(driver,"acceptTerms_ID","deals");
			
			//click on book
			//click(driver,"bookTrip_XPATH","property");
			
			
			
			
			
		  
		}
		
		@Then("^the flight should be booked$")
		public void the_flight_should_be_booked() throws Throwable {
		   
		}
		
		//DataTable for BookFlight Scenario
		private class UserDetails{
			String title;
			String	firstname;
			String	lastname;
			String state;
			String dobM;
			int dobD;
			int zip;
			int dobY;
			String add;
			String city;
			String country;
			String email;
			int phn;
			String confirm_email;
			String cardType;
			String expM;
			String cardHolderName;
			int cardNo;
			int  expY;
		}
	
}
*/