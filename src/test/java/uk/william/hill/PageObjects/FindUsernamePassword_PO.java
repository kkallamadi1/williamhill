package uk.william.hill.PageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import uk.william.hill.Cucumber.Hooks;
import uk.william.hill.DOM.FindUsernamePassword_DOM;
import uk.william.hill.SeleniumDSL.BasePage;
import uk.william.hill.Utils.Environment;

import java.io.IOException;
import java.util.Properties;


public class FindUsernamePassword_PO extends BasePage {
    public static Properties env = null;
    String targetENV = Hooks.targetENV;
    public static Properties objectProperties = new Properties();

    public FindUsernamePassword_PO(WebDriver driver) {
        super(driver, FindUsernamePassword_DOM.class);
    }

    public FindUsernamePassword_PO checkPageUrl() {
        try {
            env = Environment.getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String loadingEnvironment = env.getProperty("environment");
        checkPageUrl("football", loadingEnvironment);
        return this;
    }


    public FindUsernamePassword_PO populateUsernamePassword(String username, String password) throws InterruptedException {
        clearUsernameField();
        setUsername(username);
        clearPasswordField();
        setPassword(password);
        return this;
    }


    private void setUsername(String val) throws InterruptedException {
        enterText(FindUsernamePassword_DOM.username, val);

    }
    private void setPassword(String val) throws InterruptedException {
        enterText(FindUsernamePassword_DOM.password, val);

    }
    public void clickLoginButton() {
        click(FindUsernamePassword_DOM.FindLoginButton);
    }

    public void clearUsernameField() {
        FindUsernamePassword_DOM.username.clear();
    }

    public void clearPasswordField() {
        FindUsernamePassword_DOM.password.clear();
    }


}
