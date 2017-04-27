package uk.william.hill.PageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import uk.william.hill.DOM.FindUsernamePassword_DOM;
import uk.william.hill.DOM.WHHomePage_DOM;
import uk.william.hill.SeleniumDSL.BasePage;

public class WMHomePage_PO extends BasePage {
    String actualErrorMessageText;
    public WMHomePage_PO(WebDriver driver) {
        super(driver, WHHomePage_DOM.class);
    }

    public WMHomePage_PO validatePageContent(String content) throws Throwable {
        checkPageIsReady(60);
        validatePageTextContains(content);

        waitElementLocated(WHHomePage_DOM.loginButton, 60);
        return this;
    }

//    public WMHomePage_PO validateSelectedPageContainsTitle(String title) throws Throwable {
//        validatePageContainsTitle(title);
//        return this;
//    }


//    public WMHomePage_PO validateNavigationBarOptions(String option) throws Throwable {
//        validatePageTextContains(option);
//        return this;
//    }

//    public WMHomePage_PO validatePageLinks() throws Throwable {
//        validateElementVisible(WHHomePage_DOM.loginButton, "Login");
//
//
//        validateElementEnabled(WHHomePage_DOM.loginButton, "Login");
//
//        return this;
//    }

    public WMHomePage_PO pageMandatoryChecks() {
        validatePageContainsTitle("William Hill Sports homepage");
        return this;
    }

    public WMHomePage_PO checkJoinLink() {
        validatePageNotContainsLink("Join");
        return this;
    }


    public void selectTennisEventNavigationBar() {
        click(WHHomePage_DOM.TennisEvent);
    }


    public void selectFootballEventNavigationBar() {
        click(WHHomePage_DOM.FootballEvent);
    }

    public void addFirstSelection()
    {
        click(WHHomePage_DOM.firstSelection);
    }

    public void selectPlaceBet()
    {
        click(WHHomePage_DOM.placeBet);
    }

    public WMHomePage_PO enterBetAmount(String bet_value) throws InterruptedException {
        setBetValue(bet_value);
        return this;
    }

    public WMHomePage_PO viewToReturnValue(String to_return) {
        String actual_to_return = WHHomePage_DOM.toreturn.getAttribute("value");
        System.out.println("The value of actual_to_return is: " + actual_to_return);

            Assert.assertEquals(to_return, actual_to_return);

        return this;
    }

    public WMHomePage_PO viewToTotalStakeValue(String total_stake) {
        String actual_total_stake_value = WHHomePage_DOM.totalstake.getAttribute("value");
        System.out.println("The value of actual_total stake is: " + actual_total_stake_value);

        Assert.assertEquals(total_stake, actual_total_stake_value);

        return this;
    }


    public void clearStakeInputField() {
        WHHomePage_DOM.stakeinput.clear();
    }


    public void setBetValue(String val) throws InterruptedException {
        enterText(WHHomePage_DOM.stakeinput, val);
    }

    public WMHomePage_PO viewErrorMessage(String error) {
        actualErrorMessageText = WHHomePage_DOM.error_message.getText();
        Assert.assertTrue("Incorrect Text: " +actualErrorMessageText,actualErrorMessageText.contains(error));
        return this;
    }

    public void selectLogin() {
        click(WHHomePage_DOM.login);
    }




}
