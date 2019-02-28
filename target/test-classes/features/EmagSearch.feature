@search
Feature: Search Feature on Emag

  Scenario: Verify that I can see the button when I open from laptop page
    Given that I open the emag page
    When I go to the Laptops page
    And I click on the first laptop
    Then The Item Page will display the "Adauga in cos" button


  Scenario: Verify that I can see the button when I open from search page
    Given that I open the emag page
    When I search for "laptops"
    And I click on the first item
    Then The Item Page will display the "Adauga in cos" button