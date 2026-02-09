package org.example.elements;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Button {

    private static final Logger btnLogger = LogManager.getLogger(Button.class);

    private final String label;
    private static final String xpath = "//button[contains(text(),'%s')]";
    private final WebDriver driver;

    public Button(WebDriver driver, String fieldName){
        this.driver = driver;
        label = fieldName;
    }

    @Step("Кликнуть на кнопку")
    public void click() {
        WaitUtil.waitForVisible(driver, getElement(), Duration.ofSeconds(10));
        btnLogger.info("Find button {}", getElement());
        getElement().click();
        btnLogger.info("Click on button");
    }

    public String getText() {
        WaitUtil.waitForVisible(driver, getElement(), Duration.ofSeconds(10));
        btnLogger.info("Find button {} text is {}", getElement(), getElement().getText());
        return getElement().getText();
    }

    private WebElement getElement() {
        return driver.findElement(By.xpath(String.format(xpath, label)));
    }
}
