@all @magentoAdminFunctionality
Feature: Magento Admin

  Scenario Outline: Change the Basic Converge Payment Settings for all the store views
    Given admin opens the Magento Admin Login page
    And he enters the username abujor and the password @lex123 for Admin Login page
    And he presses the Sign in button on the Admin Login page
    And he is redirected to the Dashboard page after logging in
    And he goes to the Payment Methods page
    And he changes the store view to <storeView>
    When he changes the Basic Converge Payment Settings to Enabled <enabled>, Title <title>, Payment Action <paymentAction> and Integration Method <integrationMethod>
    And he presses the Save Config button
    Then he can see the confirmation message

    Examples:
      | storeView                     | enabled | title                                | paymentAction         | integrationMethod   |
      | Main Website                  | Yes     | Elavon Main Website                  | Authorize and Capture | Checkout JS         |
      | Default Config                | No      | Elavon Default Config                | Authorize             | Hosted Payment Page |
      | Default Store View - Store1   | Yes     | Elavon Default Store View - Store1   | Authorize and Capture | Checkout JS         |
      | Default Store View - Store 2  | No      | Elavon Default Store View - Store 2  | Authorize             | Hosted Payment Page |
      | Optional Store View - Store 2 | Yes     | Elavon Optional Store View - Store 2 | Authorize and Capture | Checkout JS         |


  Scenario: Log in with invalid user
    Given admin opens the Magento Admin Login page
    And he enters the username abujor2 and the password @lex1234 for Admin Login page
    And he presses the Sign in button on the Admin Login page
    And he isn't redirected to the Dashboard page after logging in
    And he can see the invalid login error message