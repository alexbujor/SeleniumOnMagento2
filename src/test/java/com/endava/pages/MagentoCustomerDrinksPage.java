package com.endava.pages;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MagentoCustomerDrinksPage {

    private WebDriver driver;
    @FindBy(id = "email")
    private WebElement emailField;
    @FindBy(id = "pass")
    private WebElement passwordField;
    @FindBy(id = "send2")
    private WebElement signInButton;

    UtilityMethods utilityMethods;

    public MagentoCustomerDrinksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public void fillLoginFields(String user, String password) throws InterruptedException {
        utilityMethods.populateField(emailField, user);
        utilityMethods.populateField(passwordField, password);
    }

    public MagentoCustomerHomePage pressSignInButton() throws InterruptedException {
        utilityMethods.waitForElementVisibility(signInButton);
        utilityMethods.clickAnElement(signInButton);
        return new MagentoCustomerHomePage(driver);
    }

    public boolean isOpened() {
        return "Customer Login".equals(driver.getTitle());
    }
}