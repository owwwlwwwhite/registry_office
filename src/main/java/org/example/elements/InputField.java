package org.example.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputField {
    private final By.ByXPath element;
    private final WebDriver driver;

    public InputField(WebDriver driver, String fieldName) {
        this.driver = driver;
        this.element = new By.ByXPath("//label[contains(text(),'" + fieldName + "')]/../../input");
    }

    @Step("Ввести в поле текст '{text}'")
    public void enterText(String text) {
        driver.findElement(element).sendKeys(text);
    }
}
