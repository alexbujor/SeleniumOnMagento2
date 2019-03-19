package com.endava.magentoAdmin.steps;

import com.endava.magentoAdmin.pages.MagentoAdminConfigurationPage;
import com.endava.magentoAdmin.pages.MagentoAdminDashboardPage;
import com.endava.magentoAdmin.pages.MagentoAdminLoginPage;
import com.endava.magentoAdmin.pages.MagentoAdminPaymentMethodsPage;
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

public class MagentoAdminStepDefs {
    private static WebDriver driver;
    private MagentoAdminLoginPage magentoAdminLoginPage;
    private MagentoAdminDashboardPage magentoAdminDashboardPage;
    private MagentoAdminConfigurationPage magentoAdminConfigurationPage;
    private MagentoAdminPaymentMethodsPage magentoAdminPaymentMethodsPage;
    private static final String PATH_TO_CHROMEDRIVER = "C:/chromedriver.exe";

    @Before
    public void setupMagentoAdminChromeDriver() {
        System.setProperty("webdriver.chrome.driver", PATH_TO_CHROMEDRIVER);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.setHeadless(true);
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDownMagentoAdminChromeDriver() {
        driver.quit();
    }

    @Given("(?:he|admin) opens the Magento Admin Login page")
    public void openMagentoAdminLogIn() {
        magentoAdminLoginPage = new MagentoAdminLoginPage(driver).get();
        Assert.assertTrue("Magento Admin Log in page is not opened", magentoAdminLoginPage.isOpened());
    }

    @And("^(?:he|admin) enters the username (.*) and the password (.*) for Admin Login page$")
    public void enterMagentoAdminLogInCredentials(String username, String password) throws InterruptedException {
        magentoAdminLoginPage.fillLoginFields(username, password);
    }

    @And("^(?:he|admin) presses the Sign in button on the Admin Login page")
    public void adminLogInPressSignIn() throws Throwable {
        magentoAdminDashboardPage = magentoAdminLoginPage.pressSignInButton();
    }

    @And("^(?:he|admin) (is|isn't) redirected to the Dashboard page after logging in")
    public void goToDashboardAfterLogIn(String argument) throws Throwable {
        if (argument.equals("is")) {
            Assert.assertTrue("Magento Admin Dashboard page is not opened", magentoAdminDashboardPage.isOpened());
            magentoAdminDashboardPage.closePopUp();
        } else Assert.assertTrue("Magento Admin Login page is not opened", magentoAdminLoginPage.isOpened());
    }

    @Then("^(?:he|admin) can see the invalid login error message on Admin Login page")
    public void invalidLogInErrorMessage() {
        Assert.assertTrue("Error message not displayed", magentoAdminLoginPage.invalidCredentialsErrorMessageIsDisplayed());
    }

    @And("^(?:he|admin) goes from the Dashboard page to the Configuration page")
    public void goToConfigurationFromDashboard() throws Throwable {
        magentoAdminConfigurationPage = magentoAdminDashboardPage.goToConfigPage();
        Assert.assertTrue("Magento Admin Configuration page is not opened", magentoAdminConfigurationPage.isOpened());
    }

    @And("^(?:he|admin) goes from the Configuration page to the Payment Methods page of the Sales tab")
    public void goToPaymentMethodsFromConiguration() throws Throwable {
        magentoAdminConfigurationPage.collapseGeneralTab();
        magentoAdminPaymentMethodsPage = magentoAdminConfigurationPage.goToPaymentMethods();
        Assert.assertTrue("Magento Admin Payment Methods page is not opened", magentoAdminPaymentMethodsPage.isOpened());
    }

    @And("^(?:he|admin) goes to the Payment Methods page")
    public void goToPaymentMethods() throws Throwable {
        goToConfigurationFromDashboard();
        goToPaymentMethodsFromConiguration();
    }

    @And("^(?:he|admin) changes the store view to (.*)$")
    public void changeStoreViewFromPaymentMethods(String storeView) throws InterruptedException {
        magentoAdminPaymentMethodsPage.changeTheStoreView(storeView);
        Assert.assertEquals("The store view was not changed", storeView, magentoAdminPaymentMethodsPage.getCurrentStoreView());
    }

    @When("^(?:he|admin) changes the Basic Converge Payment Settings to Enabled (.*), Title (.*), Payment Action (.*) and Integration Method (.*)$")
    public void changeBasicConvergePaymentSettings(String enabled, String title, String paymentAction, String integrationMethod) throws InterruptedException {
        magentoAdminPaymentMethodsPage.changeBasicConvergePaymentSettings(enabled, title, paymentAction, integrationMethod);
    }

    @And("^(?:he|admin) presses the Save Config button")
    public void pressSaveConfig() throws InterruptedException {
        magentoAdminPaymentMethodsPage.saveTheConfiguration();
    }

    @Then("^(?:he|admin) (can|can't) see the confirmation message that the configuration is saved successfully")
    public void savingConfirmationMessage(String argument) {
        if (argument.equals("can"))
            Assert.assertTrue("Configuration not saved successfully", magentoAdminPaymentMethodsPage.isSavedSuccessfully());
        else
            Assert.assertFalse("Configuration saved successfully", magentoAdminPaymentMethodsPage.isSavedSuccessfully());
    }
}