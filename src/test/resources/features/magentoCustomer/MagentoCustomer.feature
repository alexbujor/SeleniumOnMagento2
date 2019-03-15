@magentoCustomerFunctionality
Feature: Magento Customer

  Scenario Outline: Make an order as an existing customer with two different products
    Given customer opens the Magento Customer Home page
    And he goes to the Magento Customer Login page
    And he enters the email Shahraeh@jymfit.info and the password @Lex1234 for Customer Login page
    And he presses the Sign in button on the Customer Login page
    And he is redirected to the My Dashboard page after logging in
    And he clears the cart if it's not empty
    When he goes the <productCategory1> category of products
    And he chooses the <productName1> product which has the price <price1>
    And he adds to the cart a quantity of <quantity1> products
    Then a quantity of <quantity1> products with the name <productName1> and price <price1> was successfully added to the cart
    When he goes the <productCategory2> category of products
    And he chooses the <productName2> product which has the price <price2>
    And he adds to the cart a quantity of <quantity2> products
    Then a quantity of <quantity2> products with the name <productName2> and price <price2> was successfully added to the cart

    Examples:
      | productCategory1 | productCategory2 | productName1  | productName2  | quantity1 | quantity2 | price1 | price2 |
      | Drinks           | Bikes            | Brandy - Plum | rukavice      | 10        | 7         | 1.01   | 1.00   |
      | Bikes            | Drinks           | rukavice      | Brandy - Plum | 5         | 14        | 1.00   | 1.01   |


  Scenario: Log in with an invalid customer account
    Given customer opens the Magento Customer Home page
    And he goes to the Magento Customer Login page
    And he enters the email alexabcd@jymfit.info and the password 1234AAA for Customer Login page
    When he presses the Sign in button on the Customer Login page
    Then he isn't redirected to the My Dashboard page after logging in
    And he can see the invalid login error message on Customer Login page