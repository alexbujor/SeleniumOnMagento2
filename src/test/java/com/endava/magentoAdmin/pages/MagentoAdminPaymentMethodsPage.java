package com.endava.magentoAdmin.pages;

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
        utilityMethods.clickAnElement(storeViewSelector);
        if (!getCurrentStoreView().equals(storeView)) {
            utilityMethods.clickElementWithChildFromListIfFound(storeViewCssSelector, "a", storeView);
            utilityMethods.clickAnElement(confirmStoreViewChange);
        } else utilityMethods.clickAnElement(storeViewSelector);
    }

    public void changeBasicConvergePaymentSettings(String enabled, String title, String paymentAction, String integrationMethod) throws InterruptedException {
        utilityMethods.scrollToAnElement(authorizeNetDropDown);
        utilityMethods.clickAnElement(basicConvergePaymentSettings);
        utilityMethods.changeDropDownField(enabledDropDown, enabled);
        utilityMethods.changeTextField(titleTextField, title);
        utilityMethods.changeDropDownField(paymentActionDropDown, paymentAction);
        utilityMethods.changeDropDownField(integrationMethodDropDown, integrationMethod);
    }

    public void saveTheConfiguration() throws InterruptedException {
        utilityMethods.clickAnElement(saveConfigButton);
    }

    public boolean isSavedSuccessfully() {
        return successfulSaveMessage.isDisplayed();
    }

    public boolean isOpened() {
        return ("Configuration / Settings / Stores / Magento Admin".equals(driver.getTitle()) && terminalSetup.isDisplayed());
    }
}