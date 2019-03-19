package com.endava.magentoCustomer.steps;

import com.endava.magentoCustomer.pages.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MagentoCustomerStepDefs {
    private static WebDriver driver;
    private MagentoCustomerHomePage magentoCustomerHomePage;
    private MagentoCustomerLoginPage magentoCustomerLoginPage;
    private MagentoCustomerMyDashboardPage magentoCustomerMyDashboardPage;
    private MagentoCustomerProductsCategoryPage magentoCustomerProductsCategoryPage;
    private MagentoCustomerProductPage magentoCustomerProductPage;
    private MagentoCustomerShippingCheckoutPage magentoCustomerShippingCheckoutPage;
    private MagentoCustomerPaymentsCheckoutPage magentoCustomerPaymentsCheckoutPage;
    private MagentoCustomerSuccessPage magentoCustomerSuccessPage;
    private static final String PATH_TO_CHROMEDRIVER = "C:/chromedriver.exe";

    @Before
    public void setupMagentoCustomerChromeDriver() {
        System.setProperty("webdriver.chrome.driver", PATH_TO_CHROMEDRIVER);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.setHeadless(false);
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDownMagentoCustomerChromeDriver() {
        driver.quit();
    }

    @Given("(?:he|customer) opens the Magento Customer Home page")
    public void openMagentoCustomerHome() {
        magentoCustomerHomePage = new MagentoCustomerHomePage(driver).get();
        Assert.assertTrue("Magento Customer Home page is not opened", magentoCustomerHomePage.isOpened());
    }

    @Given("(?:he|customer) goes to the Magento Customer Login page")
    public void goToMagentoCustomerLoginPage() throws InterruptedException {
        magentoCustomerLoginPage = magentoCustomerHomePage.pressSignInButton();
        Assert.assertTrue("Magento Customer Login Page is not opened", magentoCustomerLoginPage.isOpened());
    }

    @And("^(?:he|customer) enters the email (.*) and the password (.*) for Customer Login page$")
    public void enterMagentoCustomerLogInCredentials(String email, String password) throws InterruptedException {
        magentoCustomerLoginPage.fillLoginFields(email, password);
    }

    @And("^(?:he|customer) presses the Sign in button on the Customer Login page")
    public void customerLogInPressSignIn() throws Throwable {
        magentoCustomerMyDashboardPage = magentoCustomerLoginPage.pressSignInButton();
    }

    @And("^(?:he|customer) (is|isn't) redirected to the My Dashboard page after logging in")
    public void goToHomeAfterLogIn(String argument) {
        if (argument.equals("is"))
            Assert.assertTrue("Magento Customer My Dashboard page is not opened", magentoCustomerMyDashboardPage.isOpened());
        else Assert.assertTrue("Magento Customer Login page is not opened", magentoCustomerLoginPage.isOpened());
    }

    @Then("^(?:he|customer) can see the invalid login error message on Customer Login page")
    public void invalidLogInErrorMessage() {
        Assert.assertTrue("Error message not displayed", magentoCustomerLoginPage.logInErrorMessageIsDisplayed());
    }

    @And("^(?:he|customer) clears the cart if it's not empty")
    public void clearTheCart() throws InterruptedException {
        magentoCustomerHomePage.clearTheCart();
        Assert.assertTrue("The cart is not empty", magentoCustomerHomePage.cartIsEmpty());
    }

    @When("^(?:he|customer) goes to the (.*) category of products$")
    public void goToProductCategoryPage(String productCategory) throws InterruptedException {
        magentoCustomerProductsCategoryPage = magentoCustomerHomePage.goToCategoryOfProducts(productCategory);
        Assert.assertTrue("The page for that category of products is not found", magentoCustomerProductsCategoryPage.isOpened());
    }

    @And("^(?:he|customer) chooses the (.*) product which has the price (.*)$")
    public void goToProductPage(String productName, String productPrice) throws InterruptedException {
        magentoCustomerProductPage = magentoCustomerProductsCategoryPage.goToProductPage(productName, productPrice);
        Assert.assertTrue("The product does not exist", magentoCustomerProductPage.isOpened());
    }

    @And("^(?:he|customer) adds to the cart a quantity of (.*) (?:product|products)")
    public void addProductToCart(String productQuantity) throws InterruptedException {
        magentoCustomerProductPage.addProductToCart(productQuantity);
    }

    @Then("^a quantity of (.*) (?:product|products) with the name (.*) and price (.*) was successfully added to the cart$")
    public void selectAndAddProductToCart(String productQuantity, String productName, String productPrice) throws InterruptedException {
        Assert.assertTrue("The confirmation message cannot be seen", magentoCustomerProductPage.successfullyAddedToCartMessageDisplayed());
        Assert.assertTrue("The product was not added to the cart", magentoCustomerHomePage.checkProductInCartAndRemoveIfDesired(productName, productPrice, productQuantity, false));
    }

    @When("^(?:he|customer) goes to the shipping information checkout page")
    public void goToShippingInformationCheckout() throws InterruptedException {
        magentoCustomerShippingCheckoutPage = magentoCustomerHomePage.goToCheckout();
        Assert.assertTrue("The shipping information checkout page is not open", magentoCustomerShippingCheckoutPage.isOpened());
    }

    @And("(?:he|customer) selects the shipping method with the name (.*) which has the price (.*) and the carrier (.*)$")
    public void selectShippingMethod(String shippingMethodTitle, String shippingMethodPrice, String shippingMethodCarrierTitle) throws InterruptedException {
        Assert.assertTrue("The shipping method was not found", magentoCustomerShippingCheckoutPage.selectShippingMethod(shippingMethodPrice, shippingMethodTitle, shippingMethodCarrierTitle));
    }

    @And("^(?:he|customer) proceeds to the payments checkout page")
    public void goToPaymentsCheckoutPage() throws InterruptedException {
        magentoCustomerPaymentsCheckoutPage = magentoCustomerShippingCheckoutPage.goToPaymentsCheckoutPage();
        Assert.assertTrue("The payments checkout page is not open", magentoCustomerPaymentsCheckoutPage.isOpened());
    }

    @And("^(?:he|customer) selects the payment method with the name (.*)$")
    public void selectPaymentMethod(String paymentMethodTitle) throws InterruptedException {
        Assert.assertTrue("The payment method was not found", magentoCustomerPaymentsCheckoutPage.selectPaymentMethod(paymentMethodTitle));
    }

    @And("^(?:he|customer) enters the card number (.*), expiration date MM/YY (.*)/(.*) and the CVV number (.*),(| without) saving the card for later use$")
    public void enterCreditCardDetails(String cardNumber, String expirationMonth, String expirationYear, String cvv, String argument) throws InterruptedException {
        if (argument.equals("without"))
            magentoCustomerPaymentsCheckoutPage.enterCreditCardDetails(cardNumber, expirationMonth, expirationYear, cvv, false);
        else
            magentoCustomerPaymentsCheckoutPage.enterCreditCardDetails(cardNumber, expirationMonth, expirationYear, cvv, true);
    }

    @And("^(?:he|customer) places the order")
    public void placeTheOrder() throws InterruptedException {
        magentoCustomerSuccessPage = magentoCustomerPaymentsCheckoutPage.placeTheOrder();
    }

    @Then("^The order is declined")
    public void orderIsDeclined() {
        Assert.assertTrue("The transaction is not declined", magentoCustomerPaymentsCheckoutPage.transactionDeclinedMessageDisplayed());
    }

    @Then("(?:he|customer) can see the confirmation message that the order was created, in a new page")
    public void orderIsSuccessful() {
        Assert.assertTrue("The order success page is not open", magentoCustomerSuccessPage.isOpened());
    }
}