package com.endava.magentoAdmin.pages;

import com.endava.utils.UtilityMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class MagentoAdminLoginPage extends LoadableComponent<MagentoAdminLoginPage> {

    private WebDriver driver;
    @FindBy(id = "username")
    private WebElement usernameField;
    @FindBy(id = "login")
    private WebElement passwordField;
    @FindBy(css = ".action-login.action-primary")
    private WebElement signInButton;

    private String logInErrorMessageCssSelector = ".message.message-error.error > div";

    private UtilityMethods utilityMethods;

    public MagentoAdminLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
        load();
    }

    public void fillLoginFields(String user, String password) throws InterruptedException {
        utilityMethods.populateField(usernameField, user);
        utilityMethods.populateField(passwordField, password);
    }

    public MagentoAdminDashboardPage pressSignInButton() throws InterruptedException {
        utilityMethods.clickAnElement(signInButton);
        return new MagentoAdminDashboardPage(driver);
    }

    public boolean invalidCredentialsErrorMessageIsDisplayed() {
        if (utilityMethods.elementIsVisible(By.cssSelector(logInErrorMessageCssSelector))) {
            WebElement message = driver.findElement(By.cssSelector(logInErrorMessageCssSelector));
            return (message.getText().equals("You did not sign in correctly or your account is temporarily disabled."));
        }
        return false;
    }

    public boolean invalidFormKeyErrorMessageIsDisplayed() {
        if (utilityMethods.elementIsVisible(By.cssSelector(logInErrorMessageCssSelector))) {
            WebElement message = driver.findElement(By.cssSelector(logInErrorMessageCssSelector));
            return (message.getText().equals("Invalid Form Key. Please refresh the page."));
        }
        return false;
    }

    protected void load() {
        driver.get("http://172.17.186.107/magento21/admin_page/admin/index/index");
    }

    public boolean isOpened() {
        return "Magento Admin".equals(driver.getTitle());
    }

    protected void isLoaded() throws Error {
        Assert.assertEquals("Magento Admin", driver.getTitle());
    }
}