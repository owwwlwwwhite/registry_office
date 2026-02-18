package org.example.ui.elements;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class InputField {
    private final String locator = "//label[contains(text(),'%s')]/../../input";
    private final String fieldName;
    private final WebDriver driver;

    public InputField(WebDriver driver, String fieldName) {
        this.driver = driver;
        this.fieldName = fieldName;
    }

    @Step("Ввести в поле текст '{text}'")
    public void enterText(String text) {
        getElement().sendKeys(text);
        log.info("Find field {} and input text: {}", fieldName, text);
    }

    private WebElement getElement() {
        return driver.findElement(By.xpath(String.format(locator, fieldName)));
    }
}
