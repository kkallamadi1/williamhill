package uk.william.hill.DOM;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindUsernamePassword_DOM {

    @FindBy(id="loginButton") public static WebElement FindLoginButton;
    //@FindBy(id="input-nino")				     public static WebElement nino;
    @FindBy(id="loginUsernameInput")				     public static WebElement username;
    @FindBy(id="loginPasswordInput")				     public static WebElement password;


}
