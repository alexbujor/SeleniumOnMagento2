package com.endava.magentoAdmin.pages;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MagentoAdminConfigurationPage {

    private WebDriver driver;
    @FindBy(css = "div#system_config_tabs div:nth-of-type(4)")
    private WebElement salesTabCollapsed;
    @FindBy(css = "div#system_config_tabs div:nth-of-type(1) > div")
    private WebElement generalTabExpanded;
    @FindBy(css = "div#system_config_tabs div:nth-of-type(4) > ul li:nth-of-type(10)")
    private WebElement paymentMethods;
    @FindBy(css = "div#system_config_tabs div:nth-of-type(7)")
    private WebElement advancedTabCollapsed;

    private UtilityMethods utilityMethods;

    MagentoAdminConfigurationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public void collapseGeneralTab() throws InterruptedException {
        utilityMethods.clickAnElement(generalTabExpanded);
    }

    public MagentoAdminPaymentMethodsPage goToPaymentMethods() throws InterruptedException {
        utilityMethods.clickAnElement(salesTabCollapsed);
        utilityMethods.scrollToAnElement(advancedTabCollapsed);
        utilityMethods.clickAnElement(paymentMethods);
        return new MagentoAdminPaymentMethodsPage(driver);
    }

    public boolean isOpened() {
        return "Configuration / Settings / Stores / Magento Admin".equals(driver.getTitle());
    }
}