package uk.william.hill.PageObjects;

import org.openqa.selenium.WebDriver;
import uk.william.hill.Cucumber.Hooks;
import uk.william.hill.DOM.Login_FrontEndNavigation_DOM;
import uk.william.hill.SeleniumDSL.BasePage;
import java.util.Properties;


public class Login_FrontEndNavigation_PO extends BasePage {
//	public static Properties env = null;
//	String targetENV = Hooks.targetENV;

	//private static final String Driver = null;

	public Login_FrontEndNavigation_PO(WebDriver driver) {
		super(driver, Login_FrontEndNavigation_DOM.class);
	}
	
	public void clickLoginButton(){
		click(Login_FrontEndNavigation_DOM.Login);
	}

}
