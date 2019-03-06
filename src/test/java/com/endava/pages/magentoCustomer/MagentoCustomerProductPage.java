package com.endava.pages.magentoCustomer;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MagentoCustomerProductPage {

    private WebDriver driver;
    @FindBy(id = "qty")
    private WebElement quantityTextField;
    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartButton;
    @FindBy(css = ".price-box.price-final_price > span > span > span")
    private WebElement price;

    private String successMessageCssSelector = ".page.messages > div > div > div > div";

    private String productPageTitle;
    private String productPrice;

    private UtilityMethods utilityMethods;

    public MagentoCustomerProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    MagentoCustomerProductPage(WebDriver driver, String productPageTitle, String productPrice) {
        this.driver = driver;
        this.productPageTitle = productPageTitle;
        this.productPrice = productPrice;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public void addProductToCart(String quantity) throws InterruptedException {
        utilityMethods.waitForElementVisibility(quantityTextField);
        utilityMethods.populateField(quantityTextField, quantity);
        utilityMethods.waitForElementVisibility(addToCartButton);
        utilityMethods.clickAnElement(addToCartButton);
        Thread.sleep(1000);
    }

    public boolean successfullyAddedToCartMessageDisplayed() {
        if (utilityMethods.elementIsVisible(successMessageCssSelector)) {
            WebElement message = driver.findElement(By.cssSelector(successMessageCssSelector));
            return (message.getText().equals("You added " + productPageTitle + " to your shopping cart."));
        }
        return false;
    }

    public boolean isOpened() {
        return (driver.getTitle().equals(productPageTitle) && price.getText().substring(1).equals(productPrice));
    }
}