package com.endava.magentoCustomer.steps;

import com.endava.magentoCustomer.pages.MagentoCustomerHomePage;
import com.endava.magentoCustomer.pages.MagentoCustomerLoginPage;
import com.endava.magentoCustomer.pages.MagentoCustomerProductPage;
import com.endava.magentoCustomer.pages.MagentoCustomerProductsCategoryPage;
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
    private MagentoCustomerProductsCategoryPage magentoCustomerProductsCategoryPage;
    private MagentoCustomerProductPage magentoCustomerProductPage;
    private static final String PATH_TO_CHROMEDRIVER = "C:/chromedriver.exe";

    @Before
    public void setupMagentoCustomerChromeDriver() {
        System.out.println("caca");
        System.setProperty("webdriver.chrome.driver", PATH_TO_CHROMEDRIVER);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.setHeadless(false);
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDownMagentoCustomerChromeDriver() {
        System.out.println("styjstjstyk");
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
    public void enterMagentoCustomerLogInCredentials(String username, String password) throws InterruptedException {
        magentoCustomerLoginPage.fillLoginFields(username, password);
    }

    @And("^(?:he|customer) presses the Sign in button on the Customer Login page")
    public void adminLogInPressSignIn() throws Throwable {
        magentoCustomerHomePage = magentoCustomerLoginPage.pressSignInButton();
    }

    @And("^(?:he|customer) (is|isn't) redirected back to the Home page after logging in")
    public void goToDashboardAfterLogIn(String argument) {
        if (argument.equals("is"))
            Assert.assertTrue("Magento Admin Dashboard page is not opened", magentoCustomerHomePage.isOpened());
        else Assert.assertFalse("Magento Admin Dashboard page is opened", magentoCustomerHomePage.isOpened());
    }

    @Then("^(?:he|customer) can see the invalid login error message on Customer Login page")
    public void invalidLogInErrorMessage() {
        Assert.assertTrue("Error message not displayed", magentoCustomerLoginPage.logInErrorMessageIsDisplayed());
    }

    @And("^(?:he|customer) clears the cart")
    public void clearTheCart() throws InterruptedException {
        magentoCustomerHomePage.clearTheCart();
        Assert.assertTrue("The cart is not empty", magentoCustomerHomePage.cartIsEmpty());
    }

    @When("^(?:he|customer) goes the (.*) category of products$")
    public void goToProductCategoryPage(String productCategory) throws InterruptedException {
        magentoCustomerProductsCategoryPage = magentoCustomerHomePage.goToCategoryOfProducts(productCategory);
        Assert.assertTrue("The page for that category of products is not found", magentoCustomerProductsCategoryPage.isOpened());
    }

    @And("^(?:he|customer) chooses the (.*) product with the price (.*)$")
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
        Assert.assertTrue("The product was not added to the cart", magentoCustomerHomePage.checkProductInCartAndRemove(productName, productPrice, productQuantity, false));
    }
}