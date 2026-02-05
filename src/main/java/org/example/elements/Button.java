package org.example.elements;

import org.example.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Button {
    private final WebElement element;

    public Button(WebDriver driver, String fieldName){
        WaitUtil.waitForVisible(driver, By.cssSelector("//button[contains(text(),'" + fieldName + "')]"), Duration.ofSeconds(5));
        this.element = driver.findElement(new By.ByXPath("//button[contains(text(),'" + fieldName + "')]"));
    }

    public void click() {
        element.click();
    }

    public String getText() {
        return element.getText();
    }
}
