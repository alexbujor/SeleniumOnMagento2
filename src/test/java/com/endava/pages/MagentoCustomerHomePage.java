package com.endava.pages;

import com.endava.utils.UtilityMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class MagentoCustomerHomePage extends LoadableComponent<MagentoCustomerHomePage> {

    private WebDriver driver;
    @FindBy(css = ".authorization-link")
    private WebElement signInButton;
    @FindBy(css = ".action.toggle.switcher-trigger")
    private WebElement storeViewSelector;
    @FindBy(css = ".view-A13265419.switcher-option")
    private WebElement alexTestStoreView;
    @FindBy(css = ".level0.nav-1.first.level-top.ui-menu-item")
    private WebElement drinksProducts;
    @FindBy(css = ".level0.nav-2.level-top.ui-menu-item")
    private WebElement booksProducts;
    @FindBy(css = ".level0.nav-3.level-top.ui-menu-item")
    private WebElement catFoodProducts;

    UtilityMethods utilityMethods;

    public MagentoCustomerHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
        load();
    }

    public MagentoCustomerLoginPage pressSignInButton() throws InterruptedException {
        utilityMethods.waitForElementVisibility(signInButton);
        utilityMethods.clickAnElement(signInButton);
        return new MagentoCustomerLoginPage(driver);
    }

    public String getCurrentStoreView() {
        return driver.findElement(By.cssSelector("div#switcher-language-trigger > strong > span")).getText();
    }

    public void changeToAlexStoreView() throws InterruptedException {
        utilityMethods.waitForElementVisibility(storeViewSelector);
        utilityMethods.clickAnElement(storeViewSelector);
        utilityMethods.waitForElementVisibility(alexTestStoreView);
        utilityMethods.clickAnElement(alexTestStoreView);
    }

    public MagentoCustomerDrinksPage goToDrinksProducts() throws InterruptedException {
        utilityMethods.waitForElementVisibility(drinksProducts);
        utilityMethods.clickAnElement(drinksProducts);
        return new MagentoCustomerDrinksPage(driver);
    }

    protected void load() {
        driver.get("http://172.17.186.107/magento225/");
    }

    public boolean isOpened() {
        return "Home page".equals(driver.getTitle());
    }

    protected void isLoaded() throws Error {
        Assert.assertEquals("Home page", driver.getTitle());
    }
}