package com.endava.pages;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MagentoAdminPaymentMethodsPage {

    private WebDriver driver;
    @FindBy(css = ".action-default.scalable.scalable")
    private WebElement terminalSetup;
    @FindBy(id = "store-change-button")
    private WebElement storeViewSelector;
    @FindBy(css = "ul.dropdown-menu > li:nth-of-type(4)")
    private WebElement alexTestStoreView;
    @FindBy(css = ".action-primary.action-accept")
    private WebElement confirmStoreViewChange;
    @FindBy(id = "row_payment_us_authorizenet_directpost")
    private WebElement authorizeNetDropDown;
    @FindBy(id = "payment_us_converge_gateway_converge_gateway_options-head")
    private WebElement basicConvergePaymentSettings;
    @FindBy(id = "payment_us_converge_gateway_converge_gateway_options_title")
    private WebElement titleTextField;
    @FindBy(id = "payment_us_converge_gateway_converge_gateway_options_active")
    private WebElement enabledDropDown;
    @FindBy(id = "payment_us_converge_gateway_converge_gateway_options_payment_action")
    private WebElement paymentActionDropDown;
    @FindBy(id = "payment_us_converge_gateway_converge_gateway_options_integration_method")
    private WebElement integrationMethodDropDown;
    @FindBy(id = "payment_us_converge_gateway_converge_gateway_options_license_text")
    private WebElement licenseCodeTextField;
    @FindBy(id = "payment_us_converge_gateway_converge_gateway_options_sflu_message")
    private WebElement messageTextField;
    @FindBy(id = "save")
    private WebElement saveConfigButton;
    @FindBy(css = "div#messages > div.messages > div.message.message-success.success")
    private WebElement successfulSaveMessage;

    UtilityMethods utilityMethods;

    public MagentoAdminPaymentMethodsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public String getCurrentStoreView() {
        return storeViewSelector.getText();
    }

    public void changeToAlexStoreView() throws InterruptedException {
        utilityMethods.waitForElementVisibility(storeViewSelector);
        utilityMethods.clickAnElement(storeViewSelector);
        utilityMethods.waitForElementVisibility(alexTestStoreView);
        utilityMethods.clickAnElement(alexTestStoreView);
        utilityMethods.waitForElementVisibility(confirmStoreViewChange);
        utilityMethods.clickAnElement(confirmStoreViewChange);
    }

    public void scrollToAuthorizeNetDropDown() throws InterruptedException {
        utilityMethods.scrollToAnElement(authorizeNetDropDown);
    }

    public void changeTitle(String title) throws InterruptedException {
        utilityMethods.populateField(titleTextField, title);
    }

    public void changeEnabled(String enabled) throws InterruptedException {
        utilityMethods.chooseOptionFromDropDown(enabledDropDown, enabled);
    }

    public void changePaymentAction(String paymentAction) throws InterruptedException {
        utilityMethods.chooseOptionFromDropDown(paymentActionDropDown, paymentAction);
    }

    public void changeIntegrationMethod(String integrationMethod) throws InterruptedException {
        utilityMethods.chooseOptionFromDropDown(integrationMethodDropDown, integrationMethod);
    }

    public void changeLicenseCode(String licenseCode) throws InterruptedException {
        utilityMethods.populateField(licenseCodeTextField, licenseCode);
    }

    public void changeSaveForLaterUseMessage(String message) throws InterruptedException {
        utilityMethods.populateField(messageTextField, message);
    }

    public void changeConvergePaymentSettings(String title, String enabled, String paymentAction, String integrationMethod, String licenseCode, String message) throws InterruptedException {
        scrollToAuthorizeNetDropDown();
        utilityMethods.clickAnElement(basicConvergePaymentSettings);
        changeTitle(title);
        changeEnabled(enabled);
        changePaymentAction(paymentAction);
        changeIntegrationMethod(integrationMethod);
        changeLicenseCode(licenseCode);
        changeSaveForLaterUseMessage(message);
    }

    public void saveTheConfiguration() throws InterruptedException {
        utilityMethods.clickAnElement(saveConfigButton);
    }

    public boolean isSavedSuccessfully() {
        return successfulSaveMessage.isDisplayed();
    }

    public boolean isOpened() {
        return terminalSetup.isDisplayed();
    }
}