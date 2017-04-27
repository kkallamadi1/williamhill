package uk.william.hill.Cucumber;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.william.hill.PageObjects.*;

public class Steps {

    WebDriver driver = Hooks.driver;
    Scenario scenario;
    String targetURL = Hooks.targetURL;
    String targetENV = Hooks.targetENV;
    public static Properties env;
    String content = "Login";

//--------------------Application Launch-----------------------------//

    @Given("^(?:the William Hill betting application is launched|an user has navigated to the Front End System)$")
    public void the_William_Hill_betting_application_is_launched() throws Throwable {
        //driver.manage().deleteAllCookies();
        driver.get(targetURL);
        System.out.println("The URL is: " + targetURL);
    }

    @Then("^(?:User is taken to the landing page|the landing page is displayed)$")
    public void the_landing_page_content_is_correct() throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        WMHomePage_PO.validatePageContent(content);
    }


    @Then("^The landing page should have following title")
    public void the_landing_page_relevant_links() throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        WMHomePage_PO.pageMandatoryChecks();
    }


    @When("^User select Login button on Landing Page")
    public void select_login_button() throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        WMHomePage_PO.selectLogin();
    }

    @Then("^The user selects the option \"([^\"]*)\" on the navigation bar$")
    public void user_select_option(String option) throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        if (option.equals("Football")) {
            WMHomePage_PO.selectFootballEventNavigationBar();
        } else {
            WMHomePage_PO.selectTennisEventNavigationBar();
        }
    }

    @Then("^Adds the first active selection to the betslip$")
    public void add_first_active_selection_betslip() throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        WMHomePage_PO.addFirstSelection();

    }


    @And("user places a bet of \"([^\"]*)\" under bet slip$")
    public void placeBetValue(String bet_value) throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        WMHomePage_PO.clearStakeInputField();
        WMHomePage_PO.enterBetAmount(bet_value);
    }

    @And("^The user should see the to return value as \"([^\"]*)\"$")
    public void to_retrun(String to_return) throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        WMHomePage_PO.viewToReturnValue(to_return);

    }

    @And("^should see total stake value as \"([^\"]*)\"$")
    public void total_stake(String total_stake) throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        WMHomePage_PO.viewToTotalStakeValue(total_stake);

    }

    @When("^The user clicks on Place Bet button$")
            public void click_place_bet() throws Throwable {
            WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
            WMHomePage_PO.selectPlaceBet();

            }

    @And("^user enters a valid \"([^\"]*)\" and valid \"([^\"]*)\"$")
    public void enter_valid_username_password(String username, String password) throws Throwable {
        FindUsernamePassword_PO FindUsernamePassword = new FindUsernamePassword_PO(driver);
        FindUsernamePassword.populateUsernamePassword(username, password);
    }


    @And("^User clicks on login button$")
    public void click_login_button() throws Throwable {
        FindUsernamePassword_PO FindUsernamePassword = new FindUsernamePassword_PO(driver);
        FindUsernamePassword.clickLoginButton();
    }


    @And("^User should not view Join link$")
    public void agent_view_search_result_information(String searchresult, String nino) throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        WMHomePage_PO.checkJoinLink();

    }

    @And("^Error message should be displayed for \"([^\"]*)\" as \"([^\"]*)\"$")
    public void error_message(String value, String error_message) throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        WMHomePage_PO.viewErrorMessage(error_message);

    }

    @And("^user enters a different \"([^\"]*)\"$")
    public void agent_enter_different_nino(String bet_value) throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        WMHomePage_PO.clearStakeInputField();
        WMHomePage_PO.setBetValue(bet_value);
    }

    @And("^on entering different \"([^\"]*)\" existing error message \"([^\"]*)\" should still be visible$")
    public void existing_error_message_still_visible(String nino, String error_message) throws Throwable {
        WMHomePage_PO WMHomePage_PO = new WMHomePage_PO(driver);
        WMHomePage_PO.viewErrorMessage(error_message);
        WMHomePage_PO.clearStakeInputField();
    }

}





