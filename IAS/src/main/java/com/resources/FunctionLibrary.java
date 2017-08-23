
/**
 * @author Suraj
 *
 */

package com.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.support.ui.FluentWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class FunctionLibrary extends Reports{

	//static WebElement element = null;
	static boolean elementPresent;
	static boolean elementSelected;
	static boolean elementClicked;
	static File gb_Obj_directory;
	public static String gb_Str_Basepath; 
	static int gb_Int_Location;
	public static WebDriver driver;
	static WebDriverWait wait;
	
	/****************** Driver Initialization *********************/

	public static WebDriver driverInit(String selectBrowser) {

		if (selectBrowser.equalsIgnoreCase("IE")) {
			getPath();
			File file = new File(gb_Str_Basepath + "\\CI_Automation\\src\\main\\java\\com\\resources\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			// to ignore protected mode setting
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			// to ignore zoom settings
			capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			driver = new InternetExplorerDriver(capability);
			logStep(LogStatus.PASS, "Initiating the browser:Internet Explorer ", "The browser is initiated Successfully",false);
		}
		if (selectBrowser.equalsIgnoreCase("Chrome")) {
			getPath();
			File file = new File(gb_Str_Basepath + "\\CI_Automation\\src\\main\\java\\com\\resources\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			driver = new ChromeDriver();
			logStep(LogStatus.PASS, "Initiating the browser:Chrome ", "The browser is initiated Successfully",false);
		}
		if (selectBrowser.equalsIgnoreCase("FF")) 
		{
			FirefoxBinary FBinary = new FirefoxBinary(new File("D:/Firefox25/firefox.exe"));
			FirefoxProfile FProfile = new FirefoxProfile();
			FProfile.setPreference("network.proxy.type", 0);
			driver = new FirefoxDriver(FBinary, FProfile);
			logStep(LogStatus.PASS, "Initiating the browser:Firefox ", "The browser is initiated Successfully",false);
		}
		return driver;
	}

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
	
	
	
	public static void waitfordiv() throws InterruptedException
	{
		/*for(int i = 0; i <= 60; i++)
		{
			List<WebElement> AllElement = driver.findElements(By.tagName("div"));
			WebElement lastelement = AllElement.get((AllElement.size()-1));
			if((!lastelement.getAttribute("id").contains("loadingPanelCursorDiv")))
			{
				break;
			}
			Thread.sleep(700);
		}	*/	
		
		wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("HComp_Offer_Loading_xhtml_9_modal")));
		
		
			
				
	}
	
	/*************** Element highlighter code*****************/
	
		public static void highLightElement(WebElement element)
		{
			try
			{
				View_WebElement_Down_Key(element);
								
				JavascriptExecutor js = (JavascriptExecutor) driver;

				js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",element);
					Thread.sleep(200);
				js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')",element);
			}
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}

	/******************* Move to element *********************/
		
		public static void View_WebElement_Down_Key(WebElement Element)
		{
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", Element);
			

		}
	/****************** Get Element *********************/

    public static WebElement getElement(String objName, String page) {
           FileInputStream fileInput = null;
           WebElement element = null;
           try 
           {
        	   waitfordiv();
        	   getPath();
               File scrfile = new File(gb_Str_Basepath + "\\IAS\\src\\main\\java\\com\\property\\" + page + ".properties");
               // creating FileinputStream object
               fileInput = new FileInputStream(scrfile);
           

           
           // Creating the properties class object for accessing the values
           Properties properties = new Properties();

           // loading the properties file
          
                  properties.load(fileInput);
                  wait = new WebDriverWait(driver, 90);
                  String key = objName;
                  if (key.endsWith("ID")) {
                                                    
                        String value = properties.getProperty(key);
                        wait.until(ExpectedConditions.elementToBeClickable((By.id(value))));

                        	element = driver.findElement(By.id(value));

                  } else if (key.endsWith("NAME")) {
                        String value = properties.getProperty(key);
                        wait.until(ExpectedConditions.elementToBeClickable(By.name(value)));
                        

                        	element = driver.findElement(By.name(value));

                  } else if (key.endsWith("CSS")) {
                        String value = properties.getProperty(key);
                         wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(value)));
                        

                        	element = driver.findElement(By.cssSelector(value));

                  } else if (key.endsWith("XPATH")) {
	                        String value = properties.getProperty(key);
	                     // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
	                       wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
	                       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
	                       
	                       
	                       element = driver.findElement(By.xpath(value));
                  } 
                  else if (key.endsWith("LINK")) 
                  {
                        String value = properties.getProperty(key);
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
                        

                        	element = driver.findElement(By.xpath(value));

                  }
                  
                  else if (element==null) 
                  {
                	  logStep(LogStatus.FAIL, "Identify the element: "+objName+" in the application", "Unable to identify the element in the application: " , true);
                  }

           	} 
           	catch (Exception e) 
           	{
                  System.out.println("IOException for Properties");
                  e.printStackTrace();
                  logStep(LogStatus.FAIL, "Identify the element: "+objName+" in the application", "Unable to identify the element in the application: " +e, true);
           	}
           
           highLightElement(element);
           return element;

    }
    
    /****************** Get Element without condition *********************/

    public static WebElement getElementWithoutCondition(String objName, String page) {
           FileInputStream fileInput = null;
           WebElement element = null;
           try 
           {
        	   waitfordiv();
        	   getPath();
               File scrfile = new File(gb_Str_Basepath + "\\IAS\\src\\main\\java\\com\\property\\" + page + ".properties");
               // creating FileinputStream object
               fileInput = new FileInputStream(scrfile);
           

           
           // Creating the properties class object for accessing the values
           Properties properties = new Properties();

           // loading the properties file
          
                  properties.load(fileInput);
                  wait = new WebDriverWait(driver, 90);
                  String key = objName;
                  if (key.endsWith("ID")) {
                                                    
                        String value = properties.getProperty(key);
                       // wait.until(ExpectedConditions.elementToBeClickable((By.id(value))));

                        	element = driver.findElement(By.id(value));

                  } else if (key.endsWith("NAME")) {
                        String value = properties.getProperty(key);
                       // wait.until(ExpectedConditions.elementToBeClickable(By.name(value)));
                        

                        	element = driver.findElement(By.name(value));

                  } else if (key.endsWith("CSS")) {
                        String value = properties.getProperty(key);
                       //  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(value)));
                        

                        	element = driver.findElement(By.cssSelector(value));

                  } else if (key.endsWith("XPATH")) {
	                        String value = properties.getProperty(key);
	                     // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
	                    //   wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
	                    //   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
	                       
	                       
	                       element = driver.findElement(By.xpath(value));
                  } 
                  else if (key.endsWith("LINK")) 
                  {
                        String value = properties.getProperty(key);
                       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
                        

                        	element = driver.findElement(By.xpath(value));

                  }
                  
                  else if (element==null) 
                  {
                	  logStep(LogStatus.FAIL, "Identify the element: "+objName+" in the application", "Unable to identify the element in the application: " , true);
                  }

           	} 
           	catch (Exception e) 
           	{
                  System.out.println("IOException for Properties");
                  e.printStackTrace();
                  logStep(LogStatus.FAIL, "Identify the element: "+objName+" in the application", "Unable to identify the element in the application: " +e, true);
           	}
           
           highLightElement(element);
           return element;

    }
  
    /****************** Alerts *********************/

    public static WebDriver alert(String actions) {

           try {
                  WebDriverWait wait = new WebDriverWait(driver, 20);
                  wait.until(ExpectedConditions.alertIsPresent());

                  Alert alert = driver.switchTo().alert();

                  if (actions.trim().equalsIgnoreCase("accept")) {
                        alert.accept();
                  } else if (actions.trim().equalsIgnoreCase("dismiss")) {
                        alert.dismiss();
                  }
                  
                  logStep(LogStatus.PASS, actions+" alert", "alert "+actions+" successfully",false);

           } catch (Exception e) {
                  e.printStackTrace();
                  logStep(LogStatus.FAIL, actions+" alert", "alert "+actions+" not done successfully" +e,true);
           }
           return driver;
    }

	// Authenticating the alert

	public static WebDriver alert(String actions, String username, String password) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.alertIsPresent());

			Alert alert = driver.switchTo().alert();
			if (actions.trim().equalsIgnoreCase("authenticate")) {
				alert.authenticateUsing(new UserAndPassword(username, password));
				logStep(LogStatus.PASS, "Performing the  "+actions+" on the alert", actions+" performed successfully on the alert", false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logStep(LogStatus.FAIL, "Navigate Browser - "+actions, "Navigation failed- " +e,true);
		}
		return driver;

	}

	/****************** Screen Capture @return *********************/

	public String screenCapture(String imgLocation)

	// imgLocation should be of the format C:\\<Folder Name>\\<screenshot
	// name.png>

	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(imgLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgLocation;
	}
	
	/**************************Window methods********************************/

	public static void Active_Windows(String Flag)
	{
		try
		{
		String Parentwindow = driver.getWindowHandle();
		Set<String> All_Window= driver.getWindowHandles();
		for(String win:All_Window)
		{
			driver.switchTo().window(win);
		}
		logStep(LogStatus.PASS, "Swiching to latest window: "+driver.getWindowHandles(), "Switched to window successfully",false);
		}
		catch(Exception e)
		{
			logStep(LogStatus.FAIL, "Swiching to window name: "+driver.getWindowHandle() , "Switched to window failed "+e, true);			
		}
	}

	public static int windowCount(WebDriver driver) {
		Set<String> windowList = driver.getWindowHandles();
		return windowList.size();
	}

	/****************** frames *********************/

	public static void frames(String frameID) {
		// framID can either be name/ID of the frame
		try
		{
		driver.switchTo().frame(frameID);
		logStep(LogStatus.PASS, "Switch to the frame: "+frameID, "Switched to frame successfully",false);
		}
		catch(Exception e)
		{
			System.out.println();
			logStep(LogStatus.FAIL, "Switch to the frame: "+frameID, "Switching failed "+e , true);
		}
	}

	public static void frames(int frameIndex) {
		try{
			driver.switchTo().frame(frameIndex);
			logStep(LogStatus.PASS, "Switch to the frame: "+frameIndex, "Switched to frame successfully", false);
			}
			catch(Exception e)
			{
				System.out.println();
				logStep(LogStatus.FAIL, "Switch to the frame: "+frameIndex, "Switching failed "+e , true);
			}
		}
	
	public static void frames(WebElement frameElement) {
		try{
			driver.switchTo().frame(frameElement);
			logStep(LogStatus.PASS, "Switch to the frame: "+frameElement, "Switched to frame successfully", false);
			}
			catch(Exception e)
			{
				System.out.println();
				logStep(LogStatus.FAIL, "Switch to the frame: "+frameElement, "Switching failed "+e , true);
			}
		}

	// To get back to specific frame i.e parent or default

	public static void framesSpecific(String actions) {
		if (actions.equalsIgnoreCase("default")) {
			try{
				driver.switchTo().defaultContent();
				logStep(LogStatus.PASS, "Switch to the default frame: ", "Switched to frame successfully", false);
			   }
				catch(Exception e)
				{
					System.out.println();
					logStep(LogStatus.FAIL, "Switch to the default frame: ", "Switched to frame successfully "+e , true);
				}
			}
		 else if (actions.equalsIgnoreCase("parent")) {
			 try{
					driver.switchTo().defaultContent();
					logStep(LogStatus.PASS, "Switch to the parent frame: ", "Switched to frame successfully", false);
				   }
					catch(Exception e)
					{
						System.out.println();
						logStep(LogStatus.FAIL, "Switch to the parent frame: ", "Switched to frame successfully "+e , true);
					}
				}
		
		}
	
	
		public static void latestFrame()
		{
			List <WebElement> allframes = driver.findElements(By.tagName("iframe"));
			int size = allframes.size()-1;
			WebElement lastframe = allframes.get(size);
			/*for(WebElement ele:allframes)
			{
				System.out.println(ele.getAttribute("name"));
			}*/
			String iframename = lastframe.getText();
			driver.switchTo().frame(5);
						
/*			for(WebElement ele:allframes)
			{
				driver.switchTo().frame(ele);
			//	iframename = ele.getAttribute("name");
			}
*/			
			
			logStep(LogStatus.PASS , "Switch to latest iframe:", "Switched to the latest frame successfully", true);
		}
		
	

	/****************** navigate *********************/

	public static WebDriver navigate(String actions) {
		try {
			if (actions.trim().equalsIgnoreCase("forward"))
			{
				driver.navigate().forward();
				logStep(LogStatus.PASS, "Navigate Browser - Forward", "Navigate Browser - Forward is done successfully", false);
			}
			if (actions.trim().equalsIgnoreCase("back"))
			{
				driver.navigate().back();
				logStep(LogStatus.PASS, "Navigate Browser - Back", "Navigate Browser - Back is done successfully", false);
			}
			if (actions.trim().equalsIgnoreCase("refresh"))
			{
				driver.navigate().refresh();
				logStep(LogStatus.PASS, "Navigate Browser - Refresh", "Navigate Browser - Refresh is done successfully", false);
			}
		} catch (Exception e) {
			System.out.println(e);
			logStep(LogStatus.FAIL, "Navigate Browser - "+actions, "Navigation failed- " +e, true);
		}
		return driver;

	}

	public static WebDriver navigate(String actions, String url) {

		if (actions.trim().equalsIgnoreCase("open"))
		{
			driver.navigate().to(url);
			logStep(LogStatus.PASS, "Open the Browser and enter the URL: "+url, "Browser is opened successfully", false);
		}
			
		driver.manage().window().maximize();
		logStep(LogStatus.PASS, "Maximise the Browser", "Browser is maximised successfully", true);
	
		return driver;

	}

	/****************** get functionality *********************/

	public static WebDriver get(String url) {
		driver.get(url);
		logStep(LogStatus.PASS, "Open the Browser and enter the URL: "+url, "Browser is opened successfully", true);
		return driver;
	}

	/****************** Browser Methods *********************/

	public static WebDriver browser(String actions) {
		try {
			if (actions.trim().equalsIgnoreCase("close"))
				driver.close();
			if (actions.trim().equalsIgnoreCase("maximise"))
				driver.manage().window().maximize();
			if (actions.trim().equalsIgnoreCase("deletecookies"))
				driver.manage().deleteAllCookies();
			if (actions.trim().equalsIgnoreCase("quit"))
				driver.quit();
			logStep(LogStatus.PASS, actions+" the browser","The browser "+actions+" Sucessfully", false);
		}

		catch (Exception e) {
			System.out.println(e);
			logStep(LogStatus.FAIL, actions+" the browser", "The browser action: "+actions+"is not done successfully", true);
		}
		return driver;
	}

	/****************** set text *********************/

	public static WebDriver setText(String element, String value, String page) {
		WebElement ele = getElement( element, page);
		//System.out.println(ele.getText());
		String[] elename = element.split("_");
		// if prior values present

		try {
			if ((ele.toString() != null || ele.getAttribute("value") != null || ele.getText() != null)
					&& ele.getAttribute("autocomplete") == null) {
				ele.clear();
				ele.sendKeys(value);
				logStep(LogStatus.PASS, "Set the value " +value +" to the textbox"+elename[0],
						"The value: " +value+ " was set successfully",false);
				// System.out.println("1");
			}

			
			// dynamic suggestions

			/*else if (ele.getAttribute("autocomplete").equalsIgnoreCase("off")) {
				ele.clear();
				ele.sendKeys(value);
				Thread.sleep(2000);
				ele.sendKeys(Keys.ARROW_DOWN);
				ele.sendKeys(Keys.ENTER);
				logStep(LogStatus.PASS, "Set the value " +value +" to the textbox",
						"The value: " +value+ " was set successfully");
				// System.out.println("2");
			}*/

			else{
				ele.sendKeys(value);
				ele.sendKeys(Keys.TAB);
				
				
				logStep(LogStatus.PASS, "Set the value " +value +" to the textbox "+elename[0],
						" The value: " +value+ " was entered successfully", false);
			}

		} catch (Exception e) {
			System.out.println(e);
			logStep(LogStatus.FAIL, "Set the value" +value +" to the textbox ", "The value" +value+ "was not set in the textbox", true);
			
		}
		return driver;
	}
	/****************** Wait functions *********************/

	// individual element wait

	public static WebDriver Wait(long time, String actions) {
		try {
			
			if (actions.trim().equalsIgnoreCase("implicitwait"))
				driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);

			// wait for page to load fully

			if (actions.trim().equalsIgnoreCase("page"))
			{
						String pagereadystate = (String)((JavascriptExecutor)driver).executeScript("return document.readyState");	
						while(!pagereadystate.equals("complete"))
						{
							pagereadystate = (String)((JavascriptExecutor)driver).executeScript("return document.readyState");						
						}			
			}

		}

		catch (Exception e) {
			System.out.println("the element is not present");
		}
		return driver;
	}
	


	/******************
	 * Verifying the visibility of element only for assert conditions
	 *********************/

	public static boolean isElementPresent(String obj_Name, String page) {

		WebElement element = getElement( obj_Name, page);
		String[] elename = obj_Name.split("_");
		
		elementPresent = false;
		try {

			if (element.isDisplayed()) {
				logStep(LogStatus.PASS, "Verifying if the element is displayed "+elename[0], "The element is displayed",true);
				//System.out.println("Element present");
				elementPresent = true;
			}
			else
				logStep(LogStatus.FAIL, "Verifying if the element is not displayed "+elename[0], "The element is displayed ", true);

		}

		catch (Exception e) {
			System.out.println(e);
			logStep(LogStatus.FAIL, "Verifying if the element is displayed "+elename[0], "The element is not displayed ", true);
		}
		return elementPresent;

	}
	
	/********************* Verifying the element is disabled *********************/

	public static boolean isElementdisabled(String obj_Name, String page) 
	{
		WebElement element = getElementWithoutCondition(obj_Name, page);
		String[] elename = obj_Name.split("_");
		elementPresent = false;
		try {
			

			if (!element.isEnabled()) {
				//System.out.println("Element not present");
				logStep(LogStatus.PASS, "Verifying if the element is disabled "+elename[0], "The element is disabled", true);
				elementPresent = true;
			}
			
			else
				logStep(LogStatus.FAIL, "Verifying if the element is disabled "+elename[0], "The element is disabled ", true);

		} catch (Exception e) {
			logStep(LogStatus.FAIL, "Verifying if the element is disabled "+elename[0], "The element is disabled ", true);
			System.out.println(e);
		}
		return elementPresent;
	}

	

	/********************
	 * Verifying the visibility of element if not present
	 *********************/

	public static boolean isElementNotPresent(String obj_Name, String page) {
		WebElement element = getElement( obj_Name, page);
		String[] elename = obj_Name.split("_");
		elementPresent = false;
		try {
			
			if (!element.isDisplayed()) {
				//System.out.println("Element not present");
				logStep(LogStatus.PASS, "Verifying if the element is not displayed "+elename[0], "The element is not displayed", true);
				elementPresent = true;
			}
			
			else
				logStep(LogStatus.FAIL, "Verifying if the element is not displayed "+elename[0], "The element is displayed ", true);

		} catch (Exception e) {
			logStep(LogStatus.FAIL, "Verifying if the element is not displayed "+elename[0], "The element is displayed ", true);
			System.out.println(e);
		}
		return elementPresent;
	}

	/***********************
	 * Verifying the Element has selected or not
	 *********************/

	public static boolean isElementSelected(String obj_Name, String page) {
		String[] elename = obj_Name.split("_");
		WebElement element = getElement( obj_Name, page);
		elementSelected = true;
		try {
			
			if (element.isSelected()) {
				elementSelected = true;
				logStep(LogStatus.PASS, "Verifying if the element is selected  "+elename[0], "The element is selected", true);
				//System.out.println("Element Selected");
			}
			else
				logStep(LogStatus.FAIL, "Verifying if the element is selected "+elename[0], "The element is not selected ", true);
		} catch (Exception e) {
			logStep(LogStatus.FAIL, "Verifying if the element is selected "+elename[0], "The element is not selected ", true);
			System.out.println(e);
		}
		return elementSelected;
	}
	
	
	/****************** Verify select Functionality *********************/
	
	public static void Verify_Selected(String obj_Name, String value, String page) 
	
	{
		try
			{
			
			WebElement element = getElement( obj_Name, page);
			
			Select selected = new Select(element);
			WebElement option = selected.getFirstSelectedOption();

			if (option.getText().equalsIgnoreCase(value)) 
			{
				logStep(LogStatus.PASS, "Verify whether the selected value is rendered in the application, Expected Value: "+value, "The actual value is: "+option.getText() , true);
			}
			
			else 
			{
				logStep(LogStatus.FAIL, "Expected selected Value: "+value, "Actual selected value: "+option.getText(),true );
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logStep(LogStatus.FAIL, "Verify whether the selected value is rendered in the application, Expected Value: "+value, "Unable to fetch the selected value "+e,true );
			}
	}
	
/****************** Verify select option available *********************/
	
	public static void Verify_SelectedOptions (String obj_Name, String value, String page) 
	
	{
		try
			{
			
			WebElement element = getElement( obj_Name, page);
			String elementoption = "";
			
			Select selected = new Select(element);
			List<WebElement> option = selected.getOptions();
			
			for(WebElement ele:option)
			{
				elementoption = elementoption+ele.getText()+",";
			}
			
			
			if (elementoption.equalsIgnoreCase(value)) 
			{
				logStep(LogStatus.PASS, "Verify if all the values are displayed in the select dropdown, Expected: "+value, "The actual value displayed are: "+elementoption, true);
			}
			
			else 
			{
				logStep(LogStatus.FAIL, "Expected selected Value: "+value, "Actual selected value: "+elementoption,true );
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logStep(LogStatus.FAIL, "Verify whether the selected value is rendered in the application, Expected Value: "+value, "Unable to fetch the selected value "+e,true );
			}
	}
	
	
	/******************* Verify text present in webelement ****************/
	
	
	public static void Verify_Text_Webelement (String obj_Name, String value, String page)
	{
		try
		{
		
		WebElement element = getElement( obj_Name, page);
		
		String label = element.getText();
		
		
		if (label.equalsIgnoreCase(value)) 
		{
			logStep(LogStatus.PASS, "Verify whether the element label is rendered in the application, Expected Value: "+value, "The actual value is: "+label, true );
		}
		
		else 
		{
			logStep(LogStatus.FAIL, "Expected element label: "+value, "Actual rendered value: "+label,true );
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logStep( LogStatus.FAIL, "Verify whether the selected value is rendered in the application, Expected Value: "+value, "Unable to fetch the text, Exception: "+e,true );
		}
	}
	

	/******************* Verify value present in text field****************/
	
	
	public static void Verify_Text_TextField(String obj_Name, String value, String page)
	{
		try
		{
		
		WebElement element = getElement( obj_Name, page);
		
		String textValue = element.getAttribute("value");
		
		if (textValue.equalsIgnoreCase(value)) 
		{
			logStep(LogStatus.PASS, "Verify whether the element label is rendered in the application, Expected Value: "+value, "The actual value is: "+textValue, true );
		}
		
		else 
		{
			logStep(LogStatus.FAIL, "Expected element label: "+value, "Actual rendered value: "+textValue,true );
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logStep( LogStatus.FAIL, "Verify whether the selected value is rendered in the application, Expected Value: "+value, "Unable to fetch the text, Exception: "+e,true );
		}
	}
	
	/****************** click the element 
	 * @param driver2 *********************/

	public static boolean click(String obj_Name, String page) {
		WebElement element = getElement( obj_Name, page);
		elementClicked = false;
		try {
			String elename[] = obj_Name.split("_");
			element.click();
			logStep(LogStatus.PASS, " Clicking the element "+elename[0], " The element: "+elename[0]+ " is clicked", false);
			elementClicked = true;
			// System.out.println("Element clicked");
		} catch (Exception e) {
			logStep( LogStatus.FAIL, "Clicking on the element "+obj_Name, " Unable to click "+e.getMessage(), true);
			e.printStackTrace();
		}
		return elementClicked;
	}

	/******************
	 * getting the text from non editable field
	 *********************/

	public static String getText(String obj_Name, String page) {

		WebElement element = getElement( obj_Name, page);
		String text = null;
		try {

			if (element.getText() != null)
			{
				text = element.getText();
				System.out.println("getText:" + text);
				logStep(LogStatus.INFO, "Getting required text from the application", "The required text is: " +text,true);
			}	

		}

		catch (Exception e) {
			System.out.println(e);
			logStep(LogStatus.FAIL, "Getting required text from the application", "Unable to get text: " +e,true);
		}

		return text;
	}

	/******************
	 * getting the text from editable field
	 *********************/

	public static String getValue(String obj_Name, String page) {
		WebElement element = getElement( obj_Name, page);
		String value = null;
		try {
			if (element.getAttribute("value") != null)
				{
				value = element.getAttribute("value");
				System.out.println("getValue:" + value);
				logStep(LogStatus.PASS, "Getting required text from the editable field", "The required text is: "+value ,true);
				}
			else
				logStep( LogStatus.FAIL, "Getting required text from the application", "Unable to get text: " ,true);
			
		} catch (NullPointerException e) {
			System.out.println(e);
			logStep(LogStatus.FAIL, "Getting required text from the application", "Unable to get text: " +e,true);
		}
		return value;
	}

	/****************************** select ******************************/

	public static boolean select(String obj_Name, String action, String value, String page) {
		boolean elementSelected = false;
		try {
				WebElement element = getElement( obj_Name, page);
	
				Select obj_select = new Select(element);
				if (action.equalsIgnoreCase("value")) {
					obj_select.selectByValue(value);
					
				} else if (action.equalsIgnoreCase("index")) {
					obj_select.selectByIndex(Integer.parseInt(value));
					
				} else if (action.equalsIgnoreCase("text")) {
					obj_select.selectByVisibleText(value);
					
				}
				
				logStep(LogStatus.PASS, "Select the value "+value+ " from the dropdown ", "The value "+value+ " is selected from the dropdown", true );
	
			} catch (Exception e) {
				e.printStackTrace();
				logStep(LogStatus.FAIL, "Select the value "+value+ " from the dropdown ", "Unable to select the value from the dropdown "+e,true );
			}

		return elementSelected;

	}
	
	
	

	/****************** Action functionality *********************/

	public static WebDriver movetoelement(String obj_Name, String actions, String obj_name2,String page) 
	{
		WebElement element = getElement( obj_Name, page);
		WebElement element2 = getElement( obj_name2, page);
			
		String elename[] = obj_Name.split("_");
		
		Actions action = new Actions(driver);

		// action.moveToElement(element).perform();

		if (actions.equalsIgnoreCase("click"))
			if (element.isDisplayed())

			{
				action.moveToElement(element).build().perform();
				logStep(LogStatus.PASS, "Move to element " +elename[0], "Moved to element " +obj_Name, true);
				Wait(20, "implicit");
			}

		click( obj_name2, page);

		return driver;

	}

	public static WebDriver movetoelement(String obj_Name, String actions, String page) {
		Actions action = new Actions(driver);
		String elename[] = obj_Name.split("_");
		
		WebElement element = getElement( obj_Name, page);
		if (element.isDisplayed()) {
			if (actions.equalsIgnoreCase("move"))
			{
				action.moveToElement(element).build().perform();
				logStep(LogStatus.PASS, "Move to element " +elename[0], "Moved to element " +obj_Name, true);
			}
				
			if (actions.equalsIgnoreCase("doubleclick"))
			{
				action.doubleClick(element).perform();
				logStep(LogStatus.PASS, "Double Click an element " +elename[0], "Double Clicked an element " +obj_Name,true);
			}
				
			if (actions.equalsIgnoreCase("contextclick"))
			{
				action.contextClick(element).perform();
				logStep(LogStatus.PASS, "Context Click an element " +elename[0], "Context Clicked an element " +obj_Name, true);
			}
			
		}
		return driver;
	}

	/***************************** Assert ******************************/

	public static WebDriver Assert(String obj_Name1, String actions, String page) {

		

		WebElement element1 = getElement( obj_Name1, page);
		try{
			if (actions.equalsIgnoreCase("enabled"))
				{
				org.junit.Assert.assertTrue(element1.isEnabled());
				logStep(LogStatus.PASS, "Assert whether the element is enabled ", "The element " +obj_Name1+ " is enabled", true);
				}
			if (actions.equalsIgnoreCase("displayed"))
				{
				org.junit.Assert.assertTrue(element1.isDisplayed());
				logStep(LogStatus.PASS, "Assert whether the element is displayed ", "The element " +obj_Name1+ " is displayed", true);
				}
			if (actions.equalsIgnoreCase("notdisplayed"))
				{
				org.junit.Assert.assertTrue(!element1.isDisplayed());
				logStep(LogStatus.PASS, "Assert whether the element is not displayed ", "The element " +obj_Name1+ " is not displayed", true);
				}
		}
		catch(Exception e)
		{
			System.out.println(e);
			logStep(LogStatus.FAIL, "Assert whether the element is " +actions, "Assert failed", true);
		}
		return driver;
	}
	
	public static WebDriver Assert(String obj_Name1, String actions,String obj_Name2, String page)
	{
	  try
		{
		if (actions.equalsIgnoreCase("compare"))
			{
				org.junit.Assert.assertEquals(obj_Name1.trim(), obj_Name2.trim());
				System.out.println("The comparision is successful");
				logStep(LogStatus.PASS, "Assert Compare"+obj_Name1+" and "+obj_Name2, "Assert Compare is successfull", true);
			}
		}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				logStep( LogStatus.FAIL, "Assert Compare"+obj_Name1+" and "+obj_Name2, "Assert Compare is not successfull", true);
			}
		
		return driver;	
	}
	
	public static void WaitForPageLoad(WebDriver driver) {
		String pageReadyState = (String) ((JavascriptExecutor)driver).executeScript("return document.readyState");
		while(!pageReadyState.equals("complete"))
		{
			pageReadyState = (String) ((JavascriptExecutor)driver).executeScript("return document.readyState");
			
		}
	}

	
	/*//Method to wait for the element to load
	public static boolean waitForObjectToLoad(WebDriver driver, String obj_Name, int sec, String page)
	{
		boolean elementFound = false;
		boolean isDisplayed = false;
		boolean isEnabled = false;
		element = getElement(driver, obj_Name, page);
		try{
			for(int counter =1;counter<sec;counter++)
			{
				elementFound = isElementPresent(driver, obj_Name, page);
				isDisplayed = element.isDisplayed();
				isEnabled = element.isEnabled();
				
				if(elementFound == true && isDisplayed == true && isEnabled == true)
				{
					break;
				}
				else
				{
					Thread.sleep(1000);
				}
			}
			
			if(elementFound == true)
			{
				logStep(LogStatus.PASS, "Wait for object "+obj_Name, "The element: "+obj_Name+ "is succesfully loaded in the page");
			}
			else
			{
				logStep(driver, LogStatus.FAIL, "Wait for object "+obj_Name, "is not loaded successfully in the page ", true);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(elementFound+""+obj_Name);
		System.out.println(isDisplayed+""+obj_Name);
		System.out.println(isEnabled+""+obj_Name);
		return elementFound;	
		
	}
*/
	
	/*************** Image comparison *********************/
	
	public static void imgLoading(WebElement srcimage)
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
	    	Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", srcimage);
			
	    	 if (!ImagePresent)
	    	    {
	    			logStep(LogStatus.PASS, " Verify if the image is displayed ", " The image is present ", true);
	    	    }
	    	    else
	    	    {
	    	    	logStep( LogStatus.FAIL, " Verify if the image is displayed ", " The image is not loaded properly. ", true);
	    	    }
		}
		catch(Exception exc)
		{
			logStep( LogStatus.FAIL, " Verify if the image is displayed ", " The image is not loaded properly with the exception: "+exc, true);		
		}
	}	
	
	
}


