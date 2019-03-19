package com.endava.magentoCustomer.pages;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MagentoCustomerSuccessPage {

    private WebDriver driver;
    @FindBy(css = ".action.primary.continue")
    private WebElement continueShoppingButton;
    @FindBy(id = "send2")
    private WebElement signInButton;

    private String invalidLogInMessageCssSelector = "div#message-error > div";

    private UtilityMethods utilityMethods;

    MagentoCustomerSuccessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public boolean isOpened() {
        return "Success Page".equals(driver.getTitle());
    }
}