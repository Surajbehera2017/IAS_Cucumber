package com.stepdefination;

import com.resources.FunctionLibrary;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdef3 extends FunctionLibrary
{

	@Given("^Login as DL User and launch Angebot Sparkassen-Leasing application through iMAP$")
	public void login_as_DL_User_and_launch_Angebot_Sparkassen_Leasing_application_through_iMAP() throws Throwable 
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
	    driver.manage().window().maximize();
	    click("Login_XPATH", "Login");
	    
		
	}

	@When("^Select the Customer, Leasing Product, zeilmarkt as ITK and navigate to calculation screen$")
	public void select_the_Customer_Leasing_Product_zeilmarkt_as_ITK_and_navigate_to_calculation_screen() throws Throwable 
	{
		try{
		WaitForPageLoad(driver);
		//Thread.sleep(15000);
		click("StartIMAP_XPATH","IMAP");
		//driver.findElement(By.xpath("//span[contains(text(),'Start application')]")).click();
		//Thread.sleep(5000);
		  
		
		//movetoelement("IASLINK_XPATH","move", "IMAP");
		//Actions act = new Actions(driver);
		//act.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'IAS')]"))).perform();
		//movetoelement("IASLINK_XPATH", "click", "Offer_XPATH", "IMAP");
		movetoelement("IASLINK_XPATH", "move", "IMAP");
		click("Offer_XPATH","IMAP");
		//act.moveToElement(driver.findElement(By.xpath("//*[@id='frmApplicationControl:uisubmenu11']/span"))).click().build().perform();
		//Thread.sleep(15000);
		WaitForPageLoad(driver);
		Thread.sleep(50000);
		/*Active_Windows("True");
		Robot key = new Robot();
		key.keyPress(KeyEvent.VK_TAB);
		driver.findElement(By.id("frmApplicationControl:j_idt136")).click();
		*/	
		//driver.findElement(By.xpath("//*[@id='form:tbView:HexComp_kunde_Offer_xhtml_5']")).click();
		
		latestFrame();
		click("AngebotErstellen_ID", "IMAP");
		WaitForPageLoad(driver);
		
		select("Sparkassenummer_XPATH", "text", "016", "PartnerSelection");
		WaitForPageLoad(driver);
		click("zur_Kundenauswahl_XPATH", "PartnerSelection");
		
		setText("Firstname_XPATH", "a", "Kunde");
		click("Suchen_XPATH", "Kunde");
		click("Personen_XPATH", "Kunde");
		click("Ubernehmen_XPATH", "Kunde");
		
		Thread.sleep(5000);
		WaitForPageLoad(driver);
		
		movetoelement("SindLeasing_XPATH", "move", "Produkt");
		click("SindLeasing_XPATH", "Produkt");
		
		click("Speichern_XPATH", "Produkt");
		click("ZurObjektAuswahl_XPATH", "Produkt");
		
		select("Zielmarkt_XPATH", "text", "ITK", "Object");
		click("ObjectWeiter_XPATH", "Object");
		setText("Standort_XPATH", "Test", "Object");
		select("MaschinenObjekttyp_XPATH", "text", "Software", "Object");
		select("BGN_XPATH", "text", "60", "Object");
		setText("Objektbezeichnung_XPATH", "Test", "Object");
		setText("Anschaffungskosten_XPATH", "25000", "Object");
		click("Speichern_XPATH", "Object");
		click("ZurKalkulation_XPATH", "Object");
		
		Thread.sleep(25000);
		}
		catch(Exception exc)
		{
			System.out.println(exc);
		}
	}

	@Then("^Ensure that the Kalkulationsdaten,Serviceproduk,InterneKalkulation sub tabs are launched under Kalkulation tab$")
	public void ensure_that_the_Kalkulationsdaten_Serviceproduk_InterneKalkulation_sub_tabs_are_launched_under_Kalkulation_tab() throws Throwable 
	{
		isElementPresent("KalkulationdatenTab_XPATH", "Kalkulation");
		isElementPresent("ServiceProduktTab_XPATH", "Kalkulation");
		isElementPresent("InterneKalkulation_XPATH", "Kalkulation");
	}
	
	@Then("^Ensure that the following frames are populated in Kalkulationsdaten sub tab Angaben zur Kalkulation, Angaben zu Zahlungen, Ratenverlauf$")
	public void ensure_that_the_following_frames_are_populated_in_Kalkulationsdaten_sub_tab_Angaben_zur_Kalkulation_Angaben_zu_Zahlungen_Ratenverlauf() throws Throwable 
	{
		isElementPresent("AngebotZurKalkulation_XPATH", "Kalkulation");
		isElementPresent("AngebotZuZahlugen_XPATH", "Kalkulation");
		isElementPresent("Ratenverlauf_XPATH", "Kalkulation");
	}
	
	@Then("^Ensure that the following fields are populated with default values Finanzprodukt, Laufzeit in Monaten, % der AfA$")
	public void ensure_that_the_following_fields_are_populated_with_default_values_Finanzprodukt_Laufzeit_in_Monaten_der_AfA() throws Throwable
	{
		Verify_Selected("FinanzProdukt_XPATH", "K-Vertrag", "Kalkulation");
		Verify_Text_TextField ("LaufzetInMonaten_XPATH", "24", "Kalkulation");
		Verify_Text_TextField ("AFAPercentage_XPATH", "40,000", "Kalkulation");
	}

	@Then("^Ensure that following fields are populated with default value under the frame Angaben zu Zahlungen, Zahlungsweise, Zahlungsplan, Zinssatz, Bearbeitungsgebuhr in EUR$")
	public void ensure_that_following_fields_are_populated_with_default_value_under_the_frame_Angaben_zu_Zahlungen_Zahlungsweise_Zahlungsplan_Zinssatz_Bearbeitungsgebuhr_in_EUR()  
	{
		Verify_Selected("Zahlungsweise_XPATH", "monatlich", "Kalkulation");
		Verify_Selected("Zahlungsplan_XPATH", "Linear", "Kalkulation");
		Verify_Text_TextField ("Zinssatz_XPATH", "7,000", "Kalkulation");
	}
	@Then("^Ensure below fields are available under frame Ratenverlauf, Zahlungsverlauf with info icon, Verlangerungsrate in %, Verlangerungsrate in EUR, new button Abschlusszahlung anzeigen$")
	public void ensure_below_fields_are_available_under_frame_Ratenverlauf_Zahlungsverlauf_with_info_icon_Verl_ngerungsrate_in_Verl_ngerungsrate_in_EUR_new_button_Abschlusszahlung_anzeigen()  
	{
		click("Kalkuleiren_XPATH", "Kalkulation");
		Verify_Text_TextField ("GesamtforderungOhneNG_XPATH", "26.760,96", "Kalkulation");
		Verify_Text_TextField ("VerlängerungsrateIn%_XPATH", "0,500", "Kalkulation");
		Verify_Text_TextField ("VerlängerungsrateInEUR_XPATH", "125,00", "Kalkulation");
		isElementPresent("AbschlusszahlungAnzeigen_XPATH", "Kalkulation");
	}
	
	@Then("^Ensure that the following buttons are enabled in Kalkulationsdaten frame Kalkulieren, Annullieren, Historie, Notizen$")
	public void ensure_that_the_following_buttons_are_enabled_in_Kalkulationsdaten_frame_Kalkulieren_Annullieren_Historie_Notizen() 
	{
		isElementPresent("Kalkulieren_XPATH", "Kalkulation");
		isElementPresent("Annullieren_XPATH", "Kalkulation");
		isElementPresent("Historie_XPATH", "Kalkulation");
		isElementPresent("Notizen_XPATH", "Kalkulation");
	}
	@Then("^Ensure that the following buttons are disabled in Kalkulationsdaten frame Wunschrate, Variante erstellen, Speichern, Drucken, zu den Vertragsdaten$")
	public void ensure_that_the_following_buttons_are_disabled_in_Kalkulationsdaten_frame_Wunschrate_Variante_erstellen_Speichern_Drucken_zu_den_Vertragsdaten() 
	{
		isElementdisabled("Wunschrate_XPATH", "Kalkulation");
		isElementdisabled("VarianteErstellen_XPATH", "Kalkulation");
		//isElementdisabled("Speichern_XPATH", "Kalkulation");
		isElementdisabled("Druken_XPATH", "Kalkulation");
		isElementdisabled("zudenVertragsdaten_XPATH", "Kalkulation");
	}
	@Then("^Check for below values under Finanzprodukt drop down VA,TA,K$")
	public void Check_for_below_values_under_Finanzprodukt_drop_down_VA_TA_K() 
	{
		Verify_SelectedOptions("FinanzProdukt_XPATH", "K-Vertrag,TA-Vertrag,VA-Vertrag,", "Kalkulation");
	}
	@Then("^Select K as Finanzprodukt$")
	public void Select_K_as_Finanzprodukt() 
	{
		select("FinanzProdukt_XPATH", "value", "K-Vertrag", "Kalkulation");
	}
	@Then("^Modify Laufzeit in Monaten and check for Mogliche Laufzeit text$")
	public void modify_Laufzeit_in_Monaten_and_check_for_M_gliche_Laufzeit_text()  {
		setText("LaufzetInMonaten_XPATH", "36", "Kalkulation");
		Verify_Text_TextField ("MöglicheLaufzeit_XPATH", "Mögliche Laufzeit von 24 bis 60 Monaten", "Kalkulation");	
	}
		
}
