package mdThink;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Description: This class utilizes Selenium to check if websites are up and running by requesting the pape title of each url
 * 				and comparing it to its known/stored title.
 */
public class SiteChecksSplitRemote {
	
	ExtentReports reports;
	ExtentTest testInfo;
	ExtentHtmlReporter htmlReporter;
	public WebDriver driver;
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) throws MalformedURLException {
		System.out.println("-Starting Automated Tests-");
		
		DesiredCapabilities capability = null;
		
		//If the passed browser parameter is Chrome, setup so that we will use Chrome
		if(browser.equalsIgnoreCase("Chrome")) {
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}
		//If the passed browser parameter is FireFox, setup so that we will use Firefox
		else if(browser.equalsIgnoreCase("Firefox")) {
			capability = DesiredCapabilities.firefox();
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}
		//If the passed browser parameter is IE or Internet Explorer, setup so that we will use internet explorer
		else if(browser.equalsIgnoreCase("IE") || browser.equalsIgnoreCase("Internet Explorer")) {
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("Internet Explorer");
			capability.setCapability("nativeEvents", false);
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
			capability.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		}
		else {
			System.out.println("No browser selected");
		}
		
		//create remote web driver object
		driver = new RemoteWebDriver(new URL("https://selenium.mdthink.maryland.gov/wd/hub"), capability);
		
		//Extent Reports configuration
		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/AutomationReport.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
	}
	
	@BeforeMethod
	public void register(Method method) {
		String testName = method.getName();
		testInfo = reports.createTest(testName);
		
	}
	
	
	/**
	 * Goes to LTC and checks that the page title is the same as a stored title
	 */
	@Test
	public void checkLTC() {
		// Navigating to LTC site
		driver.get("https://mymdthink.maryland.gov/cp/");
		String actualTitle11 = "mymdthink.maryland.gov";
		String pageTitle11 = driver.getTitle();
		compareTitles(actualTitle11, pageTitle11);
	}
	
	
	/**
	 * Goes to ee.mdthink and checks that the page title is the same as a stored title
	 */
	@Test
	public void checkEeMDThink() {
		//Navigating to another site
		driver.navigate().to("https://ee.mdthink.maryland.gov/ee/");
		String actualTitle2 = "DHS Access Management";
		String pageTitle2 = driver.getTitle();
		compareTitles(actualTitle2, pageTitle2);
	}
	
	
	/**
	 * Goes to access.mdthink and checks that the page title is the same as a stored title
	 */
	@Test
	public void checkAccessMDThink() {
		// Navigating to core site
		driver.navigate().to("https://access.mymdthink.maryland.gov/openam/XUI/#login/");
		String actualTitle3 = "DHS Access Management";
		String pageTitle3 = driver.getTitle();
		compareTitles(actualTitle3, pageTitle3);
	}
	
	
	/**
	 * Goes to identity.mdthink and checks that the page title is the same as a stored title
	 */
	@Test
	public void checkIdentityMDThink() {
		// Navigating to another core site
		driver.navigate().to("https://identity.mdthink.maryland.gov");
		String actualTitle4 = "Apache Tomcat/7.0.76";
		String pageTitle4 = driver.getTitle();
		compareTitles(actualTitle4, pageTitle4);
	}
	
	
	/**
	 * Goes to Splunk and checks that the page title is the same as a stored title
	 */
	@Test
	public void checkSplunk() {
		// Navigating to Splunk
		driver.navigate().to("https://log.mdthink.maryland.gov");
		String actualTitle5 = "Login | Splunk";
		String pageTitle5 = driver.getTitle();
		compareTitles(actualTitle5, pageTitle5);
	}
	
	
	/**
	 * Goes to Jenkins and checks that the page title is the same as a stored title
	 */
	@Test
	public void checkJenkins() {
		// Navigating to Jenkins
		driver.navigate().to("https://build.mdthink.maryland.gov");
		String actualTitle6 = "Jenkins";
		String pageTitle6 = driver.getTitle();
		compareTitles(actualTitle6, pageTitle6);
	}
		
	/**
	 * Goes to Bitbucket and checks that the page title is the same as a stored title
	 */
	@Test
	public void checkBitbucket() {
		// Navigating to Bitbucket
		driver.navigate().to("https://source.mdthink.maryland.gov");
		String actualTitle7 = "Log in - Bitbucket";
		String pageTitle7 = driver.getTitle();
		compareTitles(actualTitle7, pageTitle7);
	}
	
	
	/**
	 * Goes to the Confluence and checks that the page title is the same as a stored title
	 */	
	@Test
	public void checkConfluence() {
		// Navigating to Confluence
		driver.navigate().to("https://wiki.mdthink.maryland.gov");
		String actualTitle8 = "Log In -";
		String pageTitle8 = driver.getTitle();
		compareTitles(actualTitle8, pageTitle8);
	}
	
	
	/**
	 * Goes to the Service Desk and checks that the page title is the same as a stored title
	 */
	@Test
	public void checkServicedesk() {
		// Navigating to ServiceDesk
		driver.navigate().to("https://servicedesk.mdthink.maryland.gov");
		String actualTitle9 = "System Dashboard - MD THINK Service Desk";
		String pageTitle9 = driver.getTitle();
		compareTitles(actualTitle9, pageTitle9);
	}
	
	
	/**
	 * Goes to OpenAM and logs in as a user (tests user authentication)
	 */
	@Test
	public void checkOpenAM() {
		// Open the UAT OpenAM login page
		driver.get("https://uat.access.mdthink.maryland.gov/openam/");
		
		//Wait for up to 5 seconds for the page to load
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id("idToken1")));
		
		//Enter username and password for a generic account, then click on login button
		String username = "automationuser";
		String password = "P@ssw0rd@ixi450";
		driver.findElement(By.id("idToken1")).sendKeys(username);
		driver.findElement(By.id("idToken21")).sendKeys(password);
		driver.findElement(By.id("loginButton_0")).click();

		//Verify that after logging in the user is on the User Profile page
		try{
			WebElement userProfile = driver.findElement(By.cssSelector(".page-header > h1:nth-child(1)"));
			userProfile.isDisplayed();
			Reporter.log("<font color='green'>Successfully able to login to: </font>" + driver.getCurrentUrl() + "<br />");
		}
		catch(Throwable pageNavigationError) {
			Reporter.log("<font color='red'><b>User did not land on profile page after login </b></font><br />");
			Utilities.takeSnapShot(driver, "incorrect site"); //takes a screenshot

			//purposely search for an element that doesn't exist, so the testcase fails for our reporting
			driver.findElement(By.cssSelector("FailonPurpose"));
		}
	}
	
	
	public void compareTitles(String actualTitle, String pageTitle) {
		try {
			//Compare the actual page title to the page title that the driver finds
			assertEquals(actualTitle, pageTitle);
			
			//Print that it was successful in a TestNG and Extent Reports
			Reporter.log("<font color='green'>Successfully able to connect to: </font>" + driver.getCurrentUrl() + "<br />");
			testInfo.log(Status.INFO, "Successfully able to connect to: " + driver.getCurrentUrl());
		} catch (Throwable pageNavigationError) {
			//Print out unsuccessful statements to the console, TestNg and Extent Reports
			System.out.println("Not able to connect to the site: " + driver.getCurrentUrl());
			Reporter.log("<font color='red'><b>Not able to connect to the site: </b></font>" + driver.getCurrentUrl() + "<br />");
			testInfo.log(Status.INFO, "Not able to connect to the site: " + driver.getCurrentUrl());
			
			//Create a filename, take a screenshot, and attach the the screenshot in the Extent Report for all failed comparisons
			String temp = Utilities.takeSnapShot(driver,"incorrect site");
			try {
				testInfo.fail("Screenshot where test failed:", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//purposely search for an element that doesn't exist, so the testcase fails for our reporting
			driver.findElement(By.cssSelector("FailonPurpose"));
		}
	}
	
	
	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			System.out.println("-End of Automated Tests-");
			
			// Close browser window
			driver.quit();
			
			reports.flush();
		}
	}

}
