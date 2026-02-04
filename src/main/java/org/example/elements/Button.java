package org.example.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Button {
    private WebElement element;

    public Button(WebDriver driver, String fieldName){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.element = driver.findElement(new By.ByXPath("//button[contains(text(),'" + fieldName + "')]"));
    }

    public void click() {
        element.click();
    }

    public WebElement getWebElement() {
        return element;
    }
}
