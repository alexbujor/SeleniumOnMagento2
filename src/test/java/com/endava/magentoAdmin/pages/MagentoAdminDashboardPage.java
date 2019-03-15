package com.endava.magentoAdmin.pages;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MagentoAdminDashboardPage {

    private WebDriver driver;
    @FindBy(css = "div.modal-inner-wrap > header > button ")
    private WebElement popUpCloseButton;
    @FindBy(id = "menu-magento-backend-stores")
    private WebElement stores;
    @FindBy(css = "li#menu-magento-backend-stores > div > ul > li:nth-of-type(1) > ul > li:nth-of-type(1) > div > ul > li:nth-of-type(2)")
    private WebElement configurationButton;

    private UtilityMethods utilityMethods;

    MagentoAdminDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public void closePopUp() throws InterruptedException {
        if (popUpCloseButton.isDisplayed())
            utilityMethods.clickAnElement(popUpCloseButton);
    }

    public MagentoAdminConfigurationPage goToConfigPage() throws InterruptedException {
        utilityMethods.clickAnElement(stores);
        utilityMethods.clickAnElement(configurationButton);
        return new MagentoAdminConfigurationPage(driver);
    }

    public boolean isOpened() {
        return "Dashboard / Magento Admin".equals(driver.getTitle());
    }
}