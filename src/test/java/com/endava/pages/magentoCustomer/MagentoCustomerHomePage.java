package com.endava.pages.magentoCustomer;

import com.endava.utils.UtilityMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

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
    @FindBy(css = ".action-primary.action-accept")
    private WebElement acceptCartItemRemoval;
    @FindBy(css = ".action.showcart > span > span")
    private WebElement cartProductNumbers;
    @FindBy(id = "top-cart-btn-checkout")
    private WebElement proceedToCheckoutButton;

    private String productsCategoriesMenuCssSelector = "ul#ui-id-2 > li";
    private String storeViewCssSelector = ".dropdown.switcher-dropdown > li";
    private String productsFromCartCssSelector = "ol#mini-cart > li";
    private String productNameCssSelector = "div > div > strong > a";
    private String productPriceCssSelector = "div > div > div:nth-of-type(1) > div:nth-of-type(1) > span > span > span > span";
    private String productQuantityCssSelector = "div > div > div:nth-of-type(1) > div:nth-of-type(2) > input";
    private String productRemoveButtonCssSelector = "div > div > div:nth-of-type(2) > div:nth-of-type(2) > a";
    private String firstRemoveButtonCssSelector = ".minicart-items > li:nth-of-type(1) > div > div > div:nth-of-type(2) > div:nth-of-type(2) > a";

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
        if (!getCurrentStoreView().equals(storeView)) {
            utilityMethods.clickElementWithChildFromList(storeViewCssSelector, "a > span", storeView);
        } else utilityMethods.clickAnElement(storeViewSelector);
    }

    public MagentoCustomerProductsCategoryPage goToCategoryOfProducts(String category) throws InterruptedException {
        utilityMethods.clickElementFromList(productsCategoriesMenuCssSelector, category);
        return new MagentoCustomerProductsCategoryPage(driver, category);
    }

    public boolean cartIsEmpty() {
        return (cartProductNumbers.getText().equals(""));
    }

    public void clearTheCart() throws InterruptedException {
        if (!cartIsEmpty()) {
            utilityMethods.waitForElementVisibility(miniCartButton);
            utilityMethods.clickAnElement(miniCartButton);
            while (utilityMethods.elementIsVisible(firstRemoveButtonCssSelector)) {
                utilityMethods.clickAnElement(driver.findElement(By.cssSelector(firstRemoveButtonCssSelector)));
                utilityMethods.waitForElementVisibility(acceptCartItemRemoval);
                utilityMethods.clickAnElement(acceptCartItemRemoval);
            }
            utilityMethods.waitForElementVisibility(closeMiniCart);
            utilityMethods.clickAnElement(closeMiniCart);
        }
    }

    public boolean checkProductInCartAndRemove(String name, String price, String quantity, Boolean removeProduct) throws InterruptedException {
        utilityMethods.waitForElementVisibility(miniCartButton);
        utilityMethods.clickAnElement(miniCartButton);
        WebElement productName;
        WebElement productPrice;
        WebElement productQuantity;
        WebElement productRemoveButton;
        if (!cartIsEmpty()) {
            List<WebElement> productsFromCartList = driver.findElements(By.cssSelector(productsFromCartCssSelector));
            for (WebElement productFromCart : productsFromCartList) {
                productName = productFromCart.findElement(By.cssSelector(productNameCssSelector));
                productPrice = productFromCart.findElement(By.cssSelector(productPriceCssSelector));
                productQuantity = productFromCart.findElement(By.cssSelector(productQuantityCssSelector));
                productRemoveButton = productFromCart.findElement(By.cssSelector(productRemoveButtonCssSelector));
                if (productName.getText().equals(name) && productPrice.getText().substring(1).equals(price) && productQuantity.getAttribute("data-item-qty").equals(quantity)) {
                    if (removeProduct) {
                        utilityMethods.clickAnElement(productRemoveButton);
                        utilityMethods.waitForElementVisibility(acceptCartItemRemoval);
                        utilityMethods.clickAnElement(acceptCartItemRemoval);
                        utilityMethods.waitForElementVisibility(closeMiniCart);
                        utilityMethods.clickAnElement(closeMiniCart);
                        return true;
                    }
                    utilityMethods.waitForElementVisibility(closeMiniCart);
                    utilityMethods.clickAnElement(closeMiniCart);
                    return true;
                }
            }
        }
        return false;
    }

    protected void load() {
        driver.get("http://172.17.186.107/magento21/");
    }

    public boolean isOpened() {
        return "Home page".equals(driver.getTitle());
    }

    protected void isLoaded() throws Error {
        Assert.assertEquals("Home page", driver.getTitle());
    }
}