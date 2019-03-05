package com.endava.pages.magentoCustomer;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MagentoCustomerProductsCategoryPage {

    private WebDriver driver;
    @FindBy(css = ".toolbar-sorter.sorter > select")
    private WebElement sortDropDown;
    @FindBy(css = ".toolbar-sorter.sorter > a")
    private WebElement sortButton;
    @FindBy(css = ".action.subscribe.primary")
    private WebElement subscribeButton;

    private String productsListCssSelector = ".products.list.items.product-items";

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

    public MagentoCustomerProductPage goToProductPage(String product) throws InterruptedException {
        utilityMethods.scrollToAnElement(subscribeButton);
        utilityMethods.clickElementWithChildFromList(productsListCssSelector, "div > div > strong", product);
        return new MagentoCustomerProductPage(driver, product);
    }

    public boolean isOpened() {
        return categoryPageTitle.equals(driver.getTitle());
    }
}