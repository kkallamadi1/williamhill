package uk.william.hill.SeleniumDSL;


import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import cucumber.api.Scenario;

public class Screenshot {
	public static void grabScreenshotForReport(WebDriver driver, Scenario scenario) {

		try {
			byte[] screenshot = ((TakesScreenshot) driver)
								.getScreenshotAs(OutputType.BYTES);
			
			scenario.embed(screenshot, "image/png");
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
	public static File grabScreenshotForComparison(WebDriver driver) {
		
		File scrFile = null;
		try {
			scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return scrFile;
		
	}
	
	public static File saveScreenshot(WebDriver driver, String screen, String filePath) {
		
		File scrFile = null;
		try {
			scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.moveFile(scrFile, new File(filePath+screen+".png"));
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return scrFile;
		
	}


}