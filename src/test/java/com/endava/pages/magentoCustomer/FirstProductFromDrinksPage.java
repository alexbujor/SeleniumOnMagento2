package com.endava.pages.magentoCustomer;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FirstProductFromDrinksPage {

    private WebDriver driver;
    @FindBy(id = "qty")
    private WebElement quantityTextField;
    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartButton;

    private String firstProductPageTitle;

    private UtilityMethods utilityMethods;

    public FirstProductFromDrinksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public FirstProductFromDrinksPage(WebDriver driver, String firstProductPageTitle) {
        this.driver = driver;
        this.firstProductPageTitle = firstProductPageTitle;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public void addProductToCart(String quantity) throws InterruptedException {
        utilityMethods.waitForElementVisibility(quantityTextField);
        utilityMethods.populateField(quantityTextField, quantity);
        utilityMethods.waitForElementVisibility(addToCartButton);
        utilityMethods.clickAnElement(addToCartButton);
        Thread.sleep(2000);
    }

    public boolean isOpened() {
        return firstProductPageTitle.equals(driver.getTitle());
    }
}