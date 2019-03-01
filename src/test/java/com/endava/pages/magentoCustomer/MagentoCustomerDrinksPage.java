package com.endava.pages.magentoCustomer;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MagentoCustomerDrinksPage {

    private WebDriver driver;
    @FindBy(css = ".products.list.items.product-items > li:nth-of-type(1) > div > div > strong > a")
    private WebElement theFirstProductOnThePage;
    @FindBy(css = ".toolbar-sorter.sorter > select")
    private WebElement sortDropDown;
    @FindBy(css = ".toolbar-sorter.sorter > a")
    private WebElement sortButton;
    @FindBy(css = ".action.subscribe.primary")
    private WebElement subscribeButton;

    private UtilityMethods utilityMethods;

    public MagentoCustomerDrinksPage(WebDriver driver) {
        this.driver = driver;
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

    public FirstProductFromDrinksPage clickOnTheFirstProduct() throws InterruptedException {
        utilityMethods.waitForElementVisibility(theFirstProductOnThePage);
        utilityMethods.scrollToAnElement(subscribeButton);
        String firstProductPageTitle = theFirstProductOnThePage.getText();
        utilityMethods.clickAnElement(theFirstProductOnThePage);
        return new FirstProductFromDrinksPage(driver, firstProductPageTitle);
    }

    public boolean isOpened() {
        return "Drinks".equals(driver.getTitle());
    }
}