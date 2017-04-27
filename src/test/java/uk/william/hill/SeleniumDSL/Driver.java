package uk.william.hill.SeleniumDSL;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import cucumber.api.Scenario;

import uk.william.hill.Utils.Log;
import uk.william.hill.Utils.Environment;

@SuppressWarnings("deprecation")
public class Driver {
	
private static WebDriver driver;
	
/*public static WebDriver setDriver() throws IOException{
	Log.info("opening Browser");
	if(Environment.getProperty("Browser").equalsIgnoreCase("firefox"))
		driver = new FirefoxDriver();
	
	if(Environment.getProperty("Browser").equalsIgnoreCase("chrome")){
		System.setProperty("webdriver.chrome.driver", Environment.getProperty("chromeDriver"));
		driver = new ChromeDriver();
	}
	return driver;	
}*/

public static WebDriver getDriver() throws IOException{
	
	if (driver == null){
		if(Environment.getProperty("Browser").equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		
		if(Environment.getProperty("Browser").equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", Environment.getProperty("chromeDriver"));
			driver = new ChromeDriver();
		}	
	}
	return driver;
}

public static String getTitle() {
	return driver.getTitle();
}

public static String getWindow() throws IOException{
	Log.info("getting window handle");
	return getDriver().getWindowHandle();
}

public static Set<String> getWindows() throws IOException{
		return getDriver().getWindowHandles();
}

public static void switchFrame(WebElement targetFrame) throws IOException{
	Log.info("switching to the frame with id = "+targetFrame);
	getDriver().switchTo().frame(targetFrame);
}

public static void switchWindow(String windowHandle) throws IOException{
	Log.info("switching to the new window");
	getDriver().switchTo().window(windowHandle);
}


public static void switchDefault() throws IOException{
	Log.info("switching to default page content frame");
	getDriver().switchTo().defaultContent();
}

public static void close() throws IOException {
	Log.info("closing Browser");
	getDriver().quit();
}

public static void checkPageIsReady(int loopCount) throws IOException { 
	Log.info("checking webpage is ready");
	JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
	
	if (js.executeScript("return document.readyState").toString().equals("complete")){ 
		return; 
	} 
	
	//This loop will rotate for specified number of times to check if page is ready after every 1 second. 
	for (int i=0; i<loopCount; i++){ 
		try { 
			Thread.sleep(1000); 
		}catch (InterruptedException e) {} 
		
		//To check page ready state. 
		if (js.executeScript("return document.readyState").toString().equals("complete")){
			break; 
		} 
	} 
	
	} 

public static void validatePageTitleEquals(String text) throws IOException { 
	Assert.assertTrue("Text Not Found : " +text, getDriver().getTitle().equalsIgnoreCase(text));	
}

public static void grabScreenshot(Scenario scenario) {

	try {
		byte[] screenshot = ((TakesScreenshot) Driver.getDriver())
							.getScreenshotAs(OutputType.BYTES);
		
		scenario.embed(screenshot, "image/png");
	} catch (Exception e) {
	
		e.printStackTrace();
	}
	
}

public static void setTimeout(int timeout) throws IOException{
	getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
}

}
