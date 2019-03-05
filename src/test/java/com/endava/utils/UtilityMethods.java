package com.endava.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class UtilityMethods {

    private WebDriver driver;

    public UtilityMethods(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementVisibility(WebElement element) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
    }

    public boolean elementIsVisible(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector)).size() != 0;
    }

    public void clickAnElement(WebElement element) throws InterruptedException {
        element.click();
        Thread.sleep(1000);
    }

    public void populateField(WebElement element, String input) throws InterruptedException {
        element.clear();
        element.sendKeys(input);
        Thread.sleep(1000);
    }

    public void chooseOptionFromDropDown(WebElement dropDown, String option) throws InterruptedException {
        Select dropDownSelect;
        dropDownSelect = new Select(dropDown);
        dropDownSelect.selectByVisibleText(option);
        Thread.sleep(1000);
    }

    public void scrollToAnElement(WebElement element) throws InterruptedException {
        waitForElementVisibility(element);
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.perform();
        Thread.sleep(1000);
    }

//    public boolean elementIsInList(String cssSelector, String elementToFind) {
//        List<WebElement> elementsList = driver.findElements(By.cssSelector(cssSelector));
//        for (WebElement element : elementsList) {
//            if (element.getText().equals(elementToFind))
//                return true;
//        }
//        return false;
//    }

    public void clickElementFromList(String cssSelector, String elementToClick) throws InterruptedException {
        List<WebElement> elementsList = driver.findElements(By.cssSelector(cssSelector));
        for (WebElement elementFromList : elementsList) {
            if (elementFromList.getText().equals(elementToClick)) {
                waitForElementVisibility(elementFromList);
                clickAnElement(elementFromList);
                break;
            }
        }
    }

    public void clickElementWithChildFromList(String parentCssSelector, String childCssSelector, String elementToClick) throws InterruptedException {
        List<WebElement> elementsList = driver.findElements(By.cssSelector(parentCssSelector));
        for (WebElement elementFromList : elementsList) {
            WebElement childOfElementFromList = elementFromList.findElement(By.cssSelector(childCssSelector));
            System.out.println(childOfElementFromList.getText());
            if (childOfElementFromList.getText().equals(elementToClick)) {
                waitForElementVisibility(elementFromList);
                clickAnElement(elementFromList);
                break;
            }
        }
    }
}