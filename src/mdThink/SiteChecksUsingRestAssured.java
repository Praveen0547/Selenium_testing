/**
 * 
 */
package mdThink;

import org.testng.annotations.Test;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Description: This class uses no Selenium code and instead uses Java and a REST DSL (domain specific language)
 * 				called Rest Assured. This checks if an array of websites is up and running by requesting the HTTP status code
 * 				of each site, and if it returns a 200 code then the site is ok. If not, an error message is printed in the console. 
 */

public class SiteChecksUsingRestAssured {

	@Test
	public void siteChecker() {

		// Creating an array of all the MD THINK URLS to check
		String[] urls = new String[] { "https://mymdthink.maryland.gov/cp/",
				"https://ee.mdthink.maryland.gov/ee/",
				"https://access.mymdthink.maryland.gov/openam/XUI/#login/",
				"https://access.mdthink.maryland.gov/openam/XUI/#login/",
				"https://log.mdthink.maryland.gov" /* Splunk */,
				"https://build.mdthink.maryland.gov/login?from=%2F" /* Jenkins */,
				"https://source.mdthink.maryland.gov" /* Bitbucket */,
				"https://wiki.mdthink.maryland.gov" /* Confluence */,
				"https://servicedesk.mdthink.maryland.gov" };

		// Let users know we are beginning URL checks
		System.out.println("--Iniating URL checks--");

		// Loop through the array of urls
		for (int i = 0; i < urls.length; i++) {

			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.get(urls[i]);

			// Get the status code from the HTTP Response.
			int statusCode = response.getStatusCode();

			// Assert that correct status code is returned.
			try {
				Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
				System.out.println("Correct status code of " + statusCode + " returned for url: " + urls[i]);
			} catch (Throwable pageNavigationError) {
				System.out.println(
						"     ERROR! Incorrect status code of " + statusCode + " returned for url: " + urls[i]);
			}

		}

	}

}
