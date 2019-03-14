package com.endava.magentoCustomer.pages;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MagentoCustomerPaymentsCheckoutPage {

    private WebDriver driver;
    @FindBy(css = ".converge_place_order")
    private WebElement placeOrderButton;
    @FindBy(id = "converge_checkoutjs_cc_number")
    private WebElement checkoutJSCreditCardNumberField;
    @FindBy(id = "converge_checkoutjs_exp_month")
    private WebElement checkoutJSCardExpirationMonth;
    @FindBy(id = "converge_checkoutjs_exp_year")
    private WebElement checkoutJSCardExpirationYear;
    @FindBy(id = "converge_checkoutjs_exp_cvv")
    private WebElement checkoutJSCardCVV;
    @FindBy(id = "converge_gateway_enable_vault")
    private WebElement saveForLaterUseCheckbox;

    private String paymentMethodsCssSelector = ".items.payment-methods > div > div";
    private String transactionDeclinedMessageCssSelector = ".modal-content > div";

    private UtilityMethods utilityMethods;

    //4159282222222221

    MagentoCustomerPaymentsCheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public void selectPaymentMethod(String paymentMethod) throws InterruptedException {
        utilityMethods.clickElementFromList(paymentMethodsCssSelector, paymentMethod);
    }

    public void enterCreditCardDetails(String cardNumber, String expirationMonth, String expirationYear, String CVV, Boolean saveForLaterUse) throws InterruptedException {
        utilityMethods.scrollToAnElement(placeOrderButton);
        utilityMethods.changeTextField(checkoutJSCreditCardNumberField, cardNumber);
        utilityMethods.changeTextField(checkoutJSCardExpirationMonth, expirationMonth);
        utilityMethods.changeTextField(checkoutJSCardExpirationYear, expirationYear);
        utilityMethods.changeTextField(checkoutJSCardCVV, CVV);
        if (!saveForLaterUse)
            utilityMethods.clickAnElement(saveForLaterUseCheckbox);
    }

    public MagentoCustomerSuccessPage placeTheOrder() throws InterruptedException {
        utilityMethods.waitForElementVisibility(placeOrderButton);
        utilityMethods.clickAnElement(placeOrderButton);
        return new MagentoCustomerSuccessPage(driver);
    }

    public boolean transactionDeclinedMessageDisplayed() {
        if (utilityMethods.elementIsVisible(transactionDeclinedMessageCssSelector)) {
            WebElement message = driver.findElement(By.cssSelector(transactionDeclinedMessageCssSelector));
            return (message.getText().equals("Transaction declined."));
        }
        return false;
    }

    public boolean isOpened() {
        return ("Checkout".equals(driver.getTitle()) && placeOrderButton.isDisplayed());
    }
}