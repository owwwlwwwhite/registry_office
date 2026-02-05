package org.example.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputField {
    private WebElement element;

    public InputField(WebDriver driver, String fieldName){
        this.element = driver.findElement(new By.ByXPath("//label[contains(text(),'" + fieldName + "')]/../../input"));
    }

    public void enterText(String text) {
        element.sendKeys(text);
    }
}
