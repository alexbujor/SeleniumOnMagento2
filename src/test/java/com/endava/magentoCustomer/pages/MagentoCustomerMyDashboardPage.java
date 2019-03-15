package com.endava.magentoCustomer.pages;

import com.endava.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MagentoCustomerMyDashboardPage {

    private WebDriver driver;

    private UtilityMethods utilityMethods;

    MagentoCustomerMyDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utilityMethods = new UtilityMethods(driver);
    }

    public boolean isOpened() {
        return "My Account".equals(driver.getTitle());
    }
}