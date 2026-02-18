package org.example.ui.elements;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

@Log4j2
public class Button {
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
        log.info("Find button {}", getElement());
        getElement().click();
        log.info("Click on button");
    }

    public String getText() {
        WaitUtil.waitForVisible(driver, getElement(), Duration.ofSeconds(10));
        log.info("Find button {} text is {}", getElement(), getElement().getText());
        return getElement().getText();
    }

    private WebElement getElement() {
        return driver.findElement(By.xpath(String.format(xpath, label)));
    }
}
