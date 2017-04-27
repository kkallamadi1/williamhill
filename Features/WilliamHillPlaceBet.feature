@test
Feature:  Placing Bet functionality
  As an user I want to select active selection event parameterise the betslip assert to Return Total Stake and parameterise the sport


  Background: Launch Application
    Given the William Hill betting application is launched
    Then User is taken to the landing page
    And The landing page should have following title
    When User select Login button on Landing Page


    #AC1, #AC2
  Scenario Outline: User on the Login dialog box enter username and password
    When user enters a valid "<username>" and valid "<password>"
    And User clicks on login button
    Then User should not view Join link

    Examples:
    |username |password       |
    | WHATA_FOG2 |F0gUaTtest  |

    #AC3,#AC4,#AC5,#AC6,#AC7
  Scenario Outline: user parameterise sport and betslip to assert To Return and Total Stake
    When The user selects the option "<option>" on the navigation bar
    And Adds the first active selection to the betslip
    And user places a bet of "<value>" under bet slip
    Then The user should see the to return value as "<To_Return>"
    And should see total stake value as "<Total_Stake>"
    #And The user balance is updated as "<User_Balance>"
    When The user clicks on Place Bet button
    Then Error message should be displayed for "<value>" as "<error_message>"
    When user enters a different "<different_bet_value>"
    And The user clicks on Place Bet button
    Then on entering different "<different_bet_value>" existing error message "<error_message>" should still be visible



    Examples:
      | option   |value|To_Return|Total_Stake|User_Balance|error_message|different_bet_value|
      | Football |0.05 | 0.27    |0.05       |0.01        |Alert - your bet has not been placed Sorry, you have insufficient funds available. Please deposit in order to place your bet.|1.0|
      | Tennis   |1.0  |1.08     |1.0        |0.01        |Alert - your bet has not been placed Sorry, you have insufficient funds available. Please deposit in order to place your bet.|2.0|

