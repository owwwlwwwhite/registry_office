package org.example.elements;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputField {
    private static final Logger fieldLogger = LogManager.getLogger(InputField.class);
    private final By.ByXPath element;
    private final WebDriver driver;

    public InputField(WebDriver driver, String fieldName) {
        this.driver = driver;
        this.element = new By.ByXPath("//label[contains(text(),'" + fieldName + "')]/../../input");
    }

    @Step("Ввести в поле текст '{text}'")
    public void enterText(String text) {
        driver.findElement(element).sendKeys(text);
        fieldLogger.info("Find field {} and input text: {}", element, text);
    }
}
