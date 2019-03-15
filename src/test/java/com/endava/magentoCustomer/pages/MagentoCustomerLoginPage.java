package com.endava.magentoCustomer.pages;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MagentoCustomerLoginPage {

    private WebDriver driver;
    @FindBy(id = "email")
    private WebElement emailField;
    @FindBy(id = "pass")
    private WebElement passwordField;
    @FindBy(id = "send2")
    private WebElement signInButton;

    private String invalidLogInMessageCssSelector = ".messages > div > div";

    private UtilityMethods utilityMethods;

    MagentoCustomerLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public void fillLoginFields(String email, String password) throws InterruptedException {
        utilityMethods.populateField(emailField, email);
        utilityMethods.populateField(passwordField, password);
    }

    public MagentoCustomerMyDashboardPage pressSignInButton() throws InterruptedException {
        utilityMethods.clickAnElement(signInButton);
        return new MagentoCustomerMyDashboardPage(driver);
    }

    public boolean logInErrorMessageIsDisplayed() {
        if (utilityMethods.elementIsVisible(By.cssSelector(invalidLogInMessageCssSelector))) {
            WebElement message = driver.findElement(By.cssSelector(invalidLogInMessageCssSelector));
            return (message.getText().equals("Invalid login or password."));
        }
        return false;
    }

    public boolean isOpened() {
        return "Customer Login".equals(driver.getTitle());
    }
}