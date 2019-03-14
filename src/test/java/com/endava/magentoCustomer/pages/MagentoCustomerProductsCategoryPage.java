package com.endava.magentoCustomer.pages;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MagentoCustomerProductsCategoryPage {

    private WebDriver driver;
    @FindBy(css = ".toolbar-sorter.sorter > select")
    private WebElement sortDropDown;
    @FindBy(css = ".toolbar-sorter.sorter > a")
    private WebElement sortButton;
    @FindBy(css = ".action.subscribe.primary")
    private WebElement subscribeButton;
    @FindBy(css = ".logo")
    private WebElement logoButton;

    private String productsListCssSelector = ".products.list.items.product-items > li";
    private String productNameCssSelector = "div > div > strong > a";
    private String productPriceCssSelector = "div > div > div:nth-of-type(1) > span > span > span";

    private String categoryPageTitle;

    private UtilityMethods utilityMethods;

    public MagentoCustomerProductsCategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    MagentoCustomerProductsCategoryPage(WebDriver driver, String categoryPageTitle) {
        this.driver = driver;
        this.categoryPageTitle = categoryPageTitle;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public void sortDescending() throws InterruptedException {
        utilityMethods.waitForElementVisibility(sortButton);
        utilityMethods.scrollToAnElement(subscribeButton);
        utilityMethods.clickAnElement(sortButton);
    }

    public void sortAscending() throws InterruptedException {
        utilityMethods.waitForElementVisibility(sortButton);
        utilityMethods.scrollToAnElement(subscribeButton);
        utilityMethods.clickAnElement(sortButton);
    }

    public MagentoCustomerProductPage goToProductPage(String product, String price) throws InterruptedException {
        utilityMethods.scrollToAnElement(subscribeButton);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement productName;
        WebElement productPrice;
        List<WebElement> productsList = driver.findElements(By.cssSelector(productsListCssSelector));
        for (WebElement productFromList : productsList) {
            productName = productFromList.findElement(By.cssSelector(productNameCssSelector));
            productPrice = productFromList.findElement(By.cssSelector(productPriceCssSelector));
            if (productName.getText().equals(product) && productPrice.getText().substring(1).equals(price)) {
                utilityMethods.waitForElementVisibility(productFromList);
                utilityMethods.clickAnElement(productFromList);
                break;
            }
        }
        return new MagentoCustomerProductPage(driver, product, price);
    }

    public MagentoCustomerHomePage pressHomeButton() throws InterruptedException {
        utilityMethods.waitForElementVisibility(logoButton);
        utilityMethods.clickAnElement(logoButton);
        return new MagentoCustomerHomePage(driver);
    }

    public boolean isOpened() {
        return categoryPageTitle.equals(driver.getTitle());
    }
}