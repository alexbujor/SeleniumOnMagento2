@magentoCustomerFunctionality
Feature: Magento Customer

  Scenario Outline: Make orders with different amounts as an existing customer
    Given customer opens the Magento Customer Home page
    And he goes to the Magento Customer Login page
    And he enters the email Shahraeh@jymfit.info and the password @Lex1234 for Customer Login page
    And he presses the Sign in button on the Customer Login page
    And he is redirected to the My Dashboard page after logging in
    And he clears the cart if it's not empty
    When he goes to the Drinks category of products
    And he chooses the Brandy - Plum product which has the price 1.01
    And he adds to the cart a quantity of <quantity> products
    Then a quantity of <quantity> products with the name Brandy - Plum and price 1.01 was successfully added to the cart
    When customer goes to the shipping information checkout page
    And he selects the shipping method with the name Fixed which has the price 5.00 and the carrier Flat Rate
    And he goes to the Payment Methods page
    And he selects the payment method with the name Elavon Main Website
    And he enters the card number 4159282222222221, expiration date MM/YY 12/19 and the CVV number 123, without saving the card for later use
    And he places the order
    Then customer can see the confirmation message that the order was created, in a new page

    Examples:
      | quantity |
      | 8        |
#      | 24       |
#      | 40       |
#      | 56       |
#      | 70       |
#      | 95       |


  Scenario: Log in with an invalid customer account
    Given customer opens the Magento Customer Home page
    And he goes to the Magento Customer Login page
    And he enters the email alexabcd@jymfit.info and the password 1234AAA for Customer Login page
    When he presses the Sign in button on the Customer Login page
    Then he isn't redirected to the My Dashboard page after logging in
    And he can see the invalid login error message on Customer Login page