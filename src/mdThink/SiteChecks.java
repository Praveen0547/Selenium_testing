package mdThink;

import static org.testng.Assert.assertEquals;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Description: This class utilizes Selenium to check if websites are up and running by requesting the pape title of each url
 * 				and comparing it to its known/stored title.
 */

public class SiteChecks {	
	
	public WebDriver driver;
	
	@Test
	public void checkUrls() {

		//Setting path location of Chrome driver for Selenium
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");

		// Creating a driver using Chrome
		driver = new ChromeDriver();
		
		// Navigating to LTC site
		driver.get("https://mymdthink.maryland.gov/cp/");
		String actualTitle = "DHS Access Management";
		String pageTitle = driver.getTitle();
		compareTitles(actualTitle, pageTitle);

		//Navigating to another site
		driver.navigate().to("https://ee.mdthink.maryland.gov/ee/");
		String actualTitle2 = "DHS Access Management";
		String pageTitle2 = driver.getTitle();
		compareTitles(actualTitle2, pageTitle2);

		// Navigating to core site
		driver.navigate().to("https://access.mymdthink.maryland.gov/openam/XUI/#login/");
		String actualTitle3 = "DHS Access Management";
		String pageTitle3 = driver.getTitle();
		compareTitles(actualTitle3, pageTitle3);

		// Navigating to another core site
		driver.navigate().to("https://identity.mdthink.maryland.gov");
		String actualTitle4 = "Apache Tomcat/7.0.76";
		String pageTitle4 = driver.getTitle();
		compareTitles(actualTitle4, pageTitle4);

		// Navigating to Splunk
		driver.navigate().to("https://log.mdthink.maryland.gov");
		String actualTitle5 = "Login | Splunk";
		String pageTitle5 = driver.getTitle();
		compareTitles(actualTitle5, pageTitle5);
		
		// Navigating to Jenkins
		driver.navigate().to("https://build.mdthink.maryland.gov");
		String actualTitle6 = "Jenkins";
		String pageTitle6 = driver.getTitle();
		compareTitles(actualTitle6, pageTitle6);
		
		// Navigating to Bitbucket
		driver.navigate().to("https://source.mdthink.maryland.gov");
		String actualTitle7 = "Log in - Bitbucket";
		String pageTitle7 = driver.getTitle();
		compareTitles(actualTitle7, pageTitle7);
		
		// Navigating to Confluence
		driver.navigate().to("https://wiki.mdthink.maryland.gov");
		String actualTitle8 = "Log In -";
		String pageTitle8 = driver.getTitle();
		compareTitles(actualTitle8, pageTitle8);

		// Navigating to ServiceDesk
		driver.navigate().to("https://servicedesk.mdthink.maryland.gov");
		String actualTitle9 = "System Dashboard - MD THINK Service Desk";
		String pageTitle9 = driver.getTitle();
		compareTitles(actualTitle9, pageTitle9);
			
		// Close browser window
		driver.quit();
	}
	
	
	public void compareTitles(String actualTitle, String pageTitle) {
		try {
			assertEquals(actualTitle, pageTitle);
			Reporter.log("<font color='green'>Successfully able to connect to: </font>" + driver.getCurrentUrl() + "<br />");
		} catch (Throwable pageNavigationError) {
			System.out.println("Not able to connect to the site: " + driver.getCurrentUrl());
			Reporter.log("<font color='red'><b>Not able to connect to the site: </b></font>" + driver.getCurrentUrl() + "<br />");
			Utilities.takeSnapShot(driver, "incorrect site"); //takes a screenshot
		}
	}

}
