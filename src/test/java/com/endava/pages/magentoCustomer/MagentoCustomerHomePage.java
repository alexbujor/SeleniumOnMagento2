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
    @FindBy(id = "switcher-language-trigger")
    private WebElement storeViewSelector;


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

    private String productsCategoriesMenuCssSelector = "ul#ui-id-2 > li";
    private String storeViewCssSelector = ".dropdown.switcher-dropdown > li";

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
        return storeViewSelector.findElement(By.cssSelector("strong > span")).getText();
    }

    public void changeTheStoreView(String storeView) throws InterruptedException {
        utilityMethods.waitForElementVisibility(storeViewSelector);
        utilityMethods.clickAnElement(storeViewSelector);
        if (!getCurrentStoreView().equals(storeView))
            utilityMethods.clickElementWithChildFromList(storeViewCssSelector, "a", storeView);
        else utilityMethods.clickAnElement(storeViewSelector);
    }

    public MagentoCustomerProductsCategoryPage goToCategoryOfProducts(String category) throws InterruptedException {
        utilityMethods.clickElementFromList(productsCategoriesMenuCssSelector, category);
        return new MagentoCustomerProductsCategoryPage(driver, category);
    }

    public boolean cartIsEmpty() {
        return (cartProductNumbers.getText().equals(""));
    }

    public void clearTheCart() throws InterruptedException {
        if (cartIsEmpty()) {
            utilityMethods.waitForElementVisibility(miniCartButton);
            utilityMethods.clickAnElement(miniCartButton);
            while (utilityMethods.elementIsVisible(".minicart-items > li:nth-of-type(1) > div > div > div:nth-of-type(2) > div:nth-of-type(2) > a")) {
                utilityMethods.clickAnElement(deleteItemFromCartButton);
                utilityMethods.clickAnElement(acceptCartItemRemoval);
            }
            utilityMethods.waitForElementVisibility(closeMiniCart);
            utilityMethods.clickAnElement(closeMiniCart);
        }
    }

    public boolean checkTheCart(String name, String price, String quantity) throws InterruptedException {
        utilityMethods.waitForElementVisibility(miniCartButton);
        utilityMethods.clickAnElement(miniCartButton);
        utilityMethods.waitForElementVisibility(firstItemFromCartName);
        if (!firstItemFromCartName.getText().equals(name)) return false;
        if (!firstItemFromCartName.getText().equals(price)) return false;
        if (!firstItemFromCartQuantity.getAttribute("data-item-qty").equals(quantity)) return false;
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