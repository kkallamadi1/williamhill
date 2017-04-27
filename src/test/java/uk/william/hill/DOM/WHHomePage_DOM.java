package uk.william.hill.DOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WHHomePage_DOM {

    @FindBy(xpath="//a//span[text()='Login']") public static WebElement loginButton;
    @FindBy(xpath="//a[@title='Football']") public static WebElement FootballEvent;
    @FindBy(xpath="//a[@title='Football']") public static WebElement TennisEvent;
    @FindBy(xpath="//button[@id='OB_OU1533641497']") public static WebElement firstSelection;
    @FindBy(xpath="//input[@id='stake-input__1533641497L']") public static WebElement stakeinput;
    @FindBy(xpath="//span[@id='total-to-return-price']") public static WebElement toreturn;
    @FindBy(xpath="//span[@id='total-stake-price']") public static WebElement totalstake;
    @FindBy(xpath="//input[@id='place-bet-button']") public static WebElement placeBet;
    @FindBy(xpath="//div[@id='error-box-header']") public static WebElement error_message;
    @FindBy(linkText="Login") public static WebElement login;

}
