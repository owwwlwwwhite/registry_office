package org.example.elements;

import io.qameta.allure.Step;
import org.example.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Button {
    private String label;
    private static final String xpath = "//button[contains(text(),'%s')]";
    private final WebDriver driver;

    public Button(WebDriver driver, String fieldName){
        this.driver = driver;
        label = fieldName;
    }

    @Step("Кликнуть на кнопку")
    public void click() {
        WaitUtil.waitForVisible(driver, getElement(), Duration.ofSeconds(10));
        getElement().click();
    }

    public String getText() {
        WaitUtil.waitForVisible(driver, getElement(), Duration.ofSeconds(10));
        return getElement().getText();
    }

    private WebElement getElement() {
        return driver.findElement(By.xpath(String.format(xpath, label)));
    }
}
