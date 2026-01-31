package org.example.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button {
    private WebElement element;

    public Button(WebDriver driver, String fieldName){
        this.element = driver.findElement(new By.ByXPath("//button[contains(text(),'" + fieldName + "')]"));
    }

    public void click() {
        element.click();
    }

    public WebElement getWebElement() {
        return element;
    }
}
