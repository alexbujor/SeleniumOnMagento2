package com.endava.pages.magentoAdmin;

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
    @FindBy(id = "payment_us_converge_gateway_converge_gateway_options_tokenization")
    private WebElement tokenizationDropDown;
    @FindBy(id = "save")
    private WebElement saveConfigButton;
    @FindBy(css = "div#messages > div.messages > div.message.message-success.success")
    private WebElement successfulSaveMessage;

    private String storeViewCssSelector = ".dropdown-menu > li";

    private UtilityMethods utilityMethods;

    MagentoAdminPaymentMethodsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public String getCurrentStoreView() {
        return storeViewSelector.getText();
    }

    public void changeTheStoreView(String storeView) throws InterruptedException {
        utilityMethods.waitForElementVisibility(storeViewSelector);
        utilityMethods.clickAnElement(storeViewSelector);
        if (!getCurrentStoreView().equals(storeView)) {
            utilityMethods.clickElementWithChildFromList(storeViewCssSelector, "a", storeView);
            utilityMethods.waitForElementVisibility(confirmStoreViewChange);
            utilityMethods.clickAnElement(confirmStoreViewChange);
        } else utilityMethods.clickAnElement(storeViewSelector);
    }

    private void scrollToAuthorizeNetDropDown() throws InterruptedException {
        utilityMethods.scrollToAnElement(authorizeNetDropDown);
    }

    private void changeTitle(String title) throws InterruptedException {
        utilityMethods.uncheckDefaultValue(titleTextField);
        utilityMethods.populateField(titleTextField, title);
    }

    private void changeEnabled(String enabled) throws InterruptedException {
        utilityMethods.uncheckDefaultValue(enabledDropDown);
        utilityMethods.chooseOptionFromDropDown(enabledDropDown, enabled);
    }

    private void changePaymentAction(String paymentAction) throws InterruptedException {
        utilityMethods.uncheckDefaultValue(paymentActionDropDown);
        utilityMethods.chooseOptionFromDropDown(paymentActionDropDown, paymentAction);
    }

    private void changeIntegrationMethod(String integrationMethod) throws InterruptedException {
        utilityMethods.uncheckDefaultValue(integrationMethodDropDown);
        utilityMethods.chooseOptionFromDropDown(integrationMethodDropDown, integrationMethod);
    }

    public void changeBasicConvergePaymentSettings(String enabled, String title, String paymentAction, String integrationMethod) throws InterruptedException {
        scrollToAuthorizeNetDropDown();
        utilityMethods.clickAnElement(basicConvergePaymentSettings);
        changeEnabled(enabled);
        changeTitle(title);
        changePaymentAction(paymentAction);
        changeIntegrationMethod(integrationMethod);
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