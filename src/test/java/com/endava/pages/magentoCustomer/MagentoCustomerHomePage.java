package com.endava.pages.magentoCustomer;

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
    @FindBy(css = ".action.showcart")
    private WebElement miniCartButton;
    @FindBy(id = "btn-minicart-close")
    private WebElement closeMiniCart;
    @FindBy(css = ".minicart-items > li:nth-of-type(1) > div > div > strong > a")
    private WebElement firstItemFromCartName;
    @FindBy(css = ".minicart-items > li:nth-of-type(1) > div > div > div:nth-of-type(1) > div:nth-of-type(1) > span > span > span > span")
    private WebElement firstItemFromCartPrice;
    @FindBy(css = ".minicart-items > li:nth-of-type(1) > div > div > div:nth-of-type(1) > div:nth-of-type(2) > input")
    private WebElement firstItemFromCartQuantity;
    @FindBy(css = ".minicart-items > li:nth-of-type(1) > div > div > div:nth-of-type(2) > div:nth-of-type(2) > a")
    private WebElement deleteItemFromCartButton;
    @FindBy(css = ".action-primary.action-accept")
    private WebElement acceptCartItemRemoval;
    @FindBy(css = ".action.showcart > span > span")
    private WebElement cartProductNumbers;

    private UtilityMethods utilityMethods;

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

    public void clearTheCart() throws InterruptedException {
        utilityMethods.waitForElementVisibility(miniCartButton);
        utilityMethods.clickAnElement(miniCartButton);
        while (driver.findElements(By.cssSelector(".minicart-items > li:nth-of-type(1) > div > div > div:nth-of-type(2) > div:nth-of-type(2) > a")).size() != 0) {
            utilityMethods.clickAnElement(deleteItemFromCartButton);
            utilityMethods.clickAnElement(acceptCartItemRemoval);
        }
        utilityMethods.waitForElementVisibility(closeMiniCart);
        utilityMethods.clickAnElement(closeMiniCart);
    }

    public boolean cartIsEmpty() {
        return (cartProductNumbers.getText().equals(""));
    }

    public boolean checkTheCart(String name, String price, String quantity) throws InterruptedException {
        utilityMethods.waitForElementVisibility(miniCartButton);
        utilityMethods.clickAnElement(miniCartButton);
        utilityMethods.waitForElementVisibility(firstItemFromCartName);
        if (firstItemFromCartName.getText() != name) return false;
        if (firstItemFromCartPrice.getText() != price) return false;
        if (firstItemFromCartQuantity.getAttribute("data-item-qty") != quantity) return false;
        System.out.println(firstItemFromCartName.getText());
        System.out.println(firstItemFromCartPrice.getText());
        System.out.println(firstItemFromCartQuantity.getAttribute("data-item-qty"));
        utilityMethods.waitForElementVisibility(closeMiniCart);
        utilityMethods.clickAnElement(closeMiniCart);
        return true;
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