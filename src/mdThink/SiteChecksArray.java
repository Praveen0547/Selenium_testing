package mdThink;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Description: This class utilizes Java to check if an array of websites is up and running by looping through an array of urls and
 *              checking if each url responds with an Http 200 status code.
 */

public class SiteChecksArray {

	@Test
	public void checkUrls() throws MalformedURLException {
		
		// Setting path location of Chrome driver for Selenium
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");

		// Creating a driver using Chrome
		WebDriver driver = new ChromeDriver();
		
		// Creating an array of all the MD THINK URLS to check
		String[] urls = new String[] { "https://mymdthink.maryland.gov/cp/",
				"https://ee.mdthink.maryland.gov/ee/",
				"https://access.mymdthink.maryland.gov/openam/XUI/#login/",
				"https://access.mdthink.maryland.gov/openam/XUI/#login/",
				"https://log.mdthink.maryland.gov", /* Splunk */
				"https://build.mdthink.maryland.gov/login?from=%2F", /* Jenkins */
				"https://source.mdthink.maryland.gov", /* BitBucket */
				"https://wiki.mdthink.maryland.gov", /* Confluence */
				"https://servicedesk.mdthink.maryland.gov" };
		
		//Creating an httpConnection object and respCode integer for the 200 status response
		HttpURLConnection httpConnection = null;
		int respCode = 200;

		//Loop through the array of urls
		for (int i = 0; i < urls.length; i++) {
			try {
				
				httpConnection = (HttpURLConnection)(new URL(urls[i]).openConnection());
				
				//request only the header instead of the full page
				httpConnection.setRequestMethod("HEAD");
				httpConnection.connect();

				respCode = httpConnection.getResponseCode();

				if(respCode >= 400){
					System.out.println(urls[i]+" is a broken link");
				}
				else if(respCode >=300 && respCode <=399){
					System.out.println(urls[i]+" is being redirected");
				}
				else {
					System.out.println(urls[i]+" is a valid link");
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//Close browser window
		driver.quit();

	}

}
