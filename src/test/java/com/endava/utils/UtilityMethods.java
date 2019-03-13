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
        return (driver.findElements(By.cssSelector(cssSelector)).size() != 0);
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

    private void chooseOptionFromDropDown(WebElement dropDown, String option) throws InterruptedException {
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

    private boolean fieldUsesDefaultValue(WebElement element) {
        WebElement useDefaultCheckBox = driver.findElement(By.id(element.getAttribute("id") + "_inherit"));
        return (useDefaultCheckBox.getAttribute("checked") != null);
    }

    private void uncheckDefaultValue(WebElement element) throws InterruptedException {
        WebElement useDefaultCheckBox = driver.findElement(By.id(element.getAttribute("id") + "_inherit"));
        if (fieldUsesDefaultValue(element)) {
            waitForElementVisibility(useDefaultCheckBox);
            clickAnElement(useDefaultCheckBox);
        }
    }

    public void checkDefaultValue(WebElement element) throws InterruptedException {
        WebElement useDefaultCheckBox = driver.findElement(By.id(element.getAttribute("id") + "_inherit"));
        if (!fieldUsesDefaultValue(element)) {
            waitForElementVisibility(useDefaultCheckBox);
            clickAnElement(useDefaultCheckBox);
        }
    }

    public void changeTextField(WebElement field, String valueToChange) throws InterruptedException {
        if (fieldUsesDefaultValue(field)) {
            uncheckDefaultValue(field);
        }
        populateField(field, valueToChange);
    }

    public void changeDropDownField(WebElement field, String valueToChange) throws InterruptedException {
        if (fieldUsesDefaultValue(field)) {
            uncheckDefaultValue(field);
        }
        chooseOptionFromDropDown(field, valueToChange);
    }

    public boolean elementIsInList(String cssSelector, String elementToFind) {
        List<WebElement> elementsList = driver.findElements(By.cssSelector(cssSelector));
        for (WebElement element : elementsList) {
            if (element.getText().equals(elementToFind))
                return true;
        }
        return false;
    }

    public void clickElementFromList(String cssSelector, String elementToClick) throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> listOfElements = driver.findElements(By.cssSelector(cssSelector));
        for (WebElement elementFromList : listOfElements) {
            if (elementFromList.getText().equals(elementToClick)) {
                waitForElementVisibility(elementFromList);
                clickAnElement(elementFromList);
                break;
            }
        }
    }

    public void clickElementWithChildFromList(String parentCssSelector, String childCssSelector, String elementToClick) throws InterruptedException {
        Thread.sleep(1000);
        Integer childNumber = 0;
        List<WebElement> listOfElements = driver.findElements(By.cssSelector(parentCssSelector));
        for (WebElement elementFromList : listOfElements) {
            childNumber++;
            if (elementIsVisible(parentCssSelector + ":nth-of-type(" + childNumber.toString() + ") > " + childCssSelector)) {
                WebElement childOfElementFromList = elementFromList.findElement(By.cssSelector(childCssSelector));
                if (childOfElementFromList.getText().equals(elementToClick)) {
                    waitForElementVisibility(elementFromList);
                    clickAnElement(elementFromList);
                    break;
                }
            }
        }
    }
}