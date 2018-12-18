package mdThink;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Description: This class contains helpful functions that can be used throughout our automation code.
 */
public class Utilities {
	
	public static WebDriver driver;

	public static WebDriver setBrowser(String browser) {
		
		if(browser.equals("Firefox")) {
			//Disables browser logs from showing in the console. Otherwise will show a dozen lines of red browserlogs (not errors, completely ok) in the console output.
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
			
			//Setting path location of FireFox driver for Selenium
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\geckodriver.exe");
			
			//Creating a driver using FireFox
			driver = new FirefoxDriver();
		}
		else if(browser.equals("Chrome")) {
			// Setting path location of Chrome driver for Selenium
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");

			// Creating a driver using Chrome
			driver = new ChromeDriver();
		}
		else if(browser.equals("IE")) {
			// Setting path location of Chrome driver for Selenium
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\IEDriverServer.exe");

			// Creating a driver using Chrome
			driver = new InternetExplorerDriver();
		}
		else {
			System.out.println("No browser type was chosen to create a webdriver!");
		}
		return driver;
		
	}
	
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	
	/**
     * This method takes a screenshot
     * @param webdriver - the Selenium webdriver object
     * @param fileWithPath - a file path and file name to save to
	 * @return 
     * @throws Exception
     */
    public static String takeSnapShot(WebDriver webdriver, String screenShotName){

    	//Setting timestamp to include Month, Day, Year, Hour, Minute, Seconds, and Milliseconds. Example: 08.15.
    	String timeStamp = new SimpleDateFormat("MM.dd.yyy_hh.mm.ss.SS").format(new Date());
    	
    	//Setting where the screenshot will be saved and what filename it will have. The file name will be "screenshot" + the timestamp I created above.
    	String filePath = System.getProperty("user.dir")+"\\test-output\\Screenshots\\"+ screenShotName + timeStamp +".png";
    	
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        File DestFile=new File(filePath);

        //Copy file at destination
        try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return filePath;

    }
	
    /**
     * Main class to be used for internal code development and testing
     */
	public static void main(String[] args) {

	}

}
