package com.endava.magentoCustomer.pages;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MagentoCustomerShippingCheckoutPage {

    private WebDriver driver;
    @FindBy(css = ".field-tooltip-action > span")
    private WebElement shippingPolicyButton;
    @FindBy(css = ".button.action.continue.primary")
    private WebElement nextButton;

    private String shippingMethodsCssSelector = ".form.methods-shipping > div:nth-of-type(1) > table > tbody > tr";
    private String shippingMethodRadioButtonCssSelector = ".col.col-method";
    private String shippingMethodPriceCssSelector = ".col.col-price > span > span";
    private String shippingMethodTitleCssSelector = ".col.col-method";
    private String shippingMethodCarrierTitleCssSelector = ".col.col-carrier";

    private UtilityMethods utilityMethods;

    MagentoCustomerShippingCheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public MagentoCustomerPaymentsCheckoutPage goToPaymentsCheckoutPage() throws InterruptedException {
        utilityMethods.clickAnElement(nextButton);
        return new MagentoCustomerPaymentsCheckoutPage(driver);
    }

    public boolean selectShippingMethod(String price, String title, String carrierTitle) throws InterruptedException {
        WebElement shippingMethodPrice;
        WebElement shippingMethodTitle;
        WebElement shippingMethodCarrierTitle;
        WebElement shippingMethodRadioButton;
        List<WebElement> shippingMethodsList = driver.findElements(By.cssSelector(shippingMethodsCssSelector));
        for (WebElement shippingMethod : shippingMethodsList) {
            shippingMethodPrice = shippingMethod.findElement(By.cssSelector(shippingMethodPriceCssSelector));
            shippingMethodTitle = shippingMethod.findElement(By.cssSelector(shippingMethodTitleCssSelector));
            shippingMethodCarrierTitle = shippingMethod.findElement(By.cssSelector(shippingMethodCarrierTitleCssSelector));
            shippingMethodRadioButton = shippingMethod.findElement(By.cssSelector(shippingMethodRadioButtonCssSelector));
            System.out.println(shippingMethodPrice.getText().substring(1));
            System.out.println(shippingMethodTitle.getText());
            System.out.println(shippingMethodCarrierTitle.getText());
            if (shippingMethodPrice.getText().substring(1).equals(price) && shippingMethodTitle.getText().equals(title) && shippingMethodCarrierTitle.getText().equals(carrierTitle)) {
                utilityMethods.clickAnElement(shippingMethodRadioButton);
                return true;
            }
        }
        return false;
    }

    public boolean isOpened() throws InterruptedException {
        utilityMethods.waitForElementVisibility(nextButton);
        utilityMethods.waitSomeMillis(3000);
        System.out.println(shippingPolicyButton.isDisplayed());
        return ("Checkout".equals(driver.getTitle()) && shippingPolicyButton.isDisplayed());
    }
}