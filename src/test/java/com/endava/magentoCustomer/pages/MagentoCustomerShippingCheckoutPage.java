package com.endava.magentoCustomer.pages;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MagentoCustomerShippingCheckoutPage {

    private WebDriver driver;
    @FindBy(css = ".action.action-show-popup")
    private WebElement newShippingAddressButton;
    @FindBy(css = ".button.action.continue.primary")
    private WebElement nextButton;

    private UtilityMethods utilityMethods;

    MagentoCustomerShippingCheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public MagentoCustomerPaymentsCheckoutPage goToPaymentsCheckoutPage() throws InterruptedException {
        utilityMethods.clickAnElement(nextButton);
        return new MagentoCustomerPaymentsCheckoutPage(driver);
    }

    public boolean isOpened() {
        return ("Checkout".equals(driver.getTitle()) && newShippingAddressButton.isDisplayed());
    }
}