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

    public void waitSomeMillis(Integer millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public void waitForElementVisibility(WebElement element) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }

    public boolean elementIsVisible(By selector) {
        return (driver.findElements(selector).size() != 0);
    }

    public void clickAnElement(WebElement element) throws InterruptedException {
        waitForElementVisibility(element);
        waitSomeMillis(1000);
        element.click();
    }

    public void populateField(WebElement textField, String input) throws InterruptedException {
        waitForElementVisibility(textField);
        waitSomeMillis(1000);
        textField.clear();
        textField.sendKeys(input);
    }

    private void chooseOptionFromDropDown(WebElement dropDown, String option) throws InterruptedException {
        waitForElementVisibility(dropDown);
        waitSomeMillis(1000);
        Select dropDownSelect;
        dropDownSelect = new Select(dropDown);
        dropDownSelect.selectByVisibleText(option);
    }

    public void scrollToAnElement(WebElement element) throws InterruptedException {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.perform();
        waitSomeMillis(1000);
    }

    private boolean fieldUsesDefaultValue(WebElement element) {
        WebElement useDefaultCheckBox = driver.findElement(By.id(element.getAttribute("id") + "_inherit"));
        return (useDefaultCheckBox.getAttribute("checked") != null);
    }

    private void uncheckDefaultValue(WebElement element) throws InterruptedException {
        WebElement useDefaultCheckBox = driver.findElement(By.id(element.getAttribute("id") + "_inherit"));
        if (fieldUsesDefaultValue(element)) {
            clickAnElement(useDefaultCheckBox);
        }
    }

    public void checkDefaultValue(WebElement element) throws InterruptedException {
        WebElement useDefaultCheckBox = driver.findElement(By.id(element.getAttribute("id") + "_inherit"));
        if (!fieldUsesDefaultValue(element)) {
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

    public boolean clickElementFromListIfFound(String cssSelector, String elementToClick) throws InterruptedException {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
        List<WebElement> listOfElements = driver.findElements(By.cssSelector(cssSelector));
        for (WebElement elementFromList : listOfElements) {
            if (elementFromList.getText().equals(elementToClick)) {
                clickAnElement(elementFromList);
                return true;
            }
        }
        return false;
    }

    public boolean clickElementWithChildFromListIfFound(String parentCssSelector, String childCssSelector, String elementToClick) throws InterruptedException {
        Integer childNumber = 0;
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parentCssSelector)));
        List<WebElement> listOfElements = driver.findElements(By.cssSelector(parentCssSelector));
        for (WebElement elementFromList : listOfElements) {
            childNumber++;
            if (elementIsVisible(By.cssSelector(parentCssSelector + ":nth-of-type(" + childNumber.toString() + ") > " + childCssSelector))) {
                WebElement childOfElementFromList = elementFromList.findElement(By.cssSelector(childCssSelector));
                if (childOfElementFromList.getText().equals(elementToClick)) {
                    clickAnElement(elementFromList);
                    return true;
                }
            }
        }
        return false;
    }
}