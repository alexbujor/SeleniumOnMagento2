package com.endava.pages.magentoAdmin;

import com.endava.utils.UtilityMethods;
import org.junit.Assert;
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
        utilityMethods.waitForElementVisibility(signInButton);
        utilityMethods.clickAnElement(signInButton);
        return new MagentoAdminDashboardPage(driver);
    }

    protected void load() {
        driver.get("http://172.17.186.107/magento225/admin_page/admin/index/index");
    }

    public boolean isOpened() {
        return "Magento Admin".equals(driver.getTitle());
    }

    protected void isLoaded() throws Error {
        Assert.assertEquals("Magento Admin", driver.getTitle());
    }
}