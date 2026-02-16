package org.example.pages;

import io.qameta.allure.Step;
import org.example.elements.Button;
import org.example.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.example.FieldNamesConstants.CREATE_NEW_APPLICATION_BUTTON;

public class ApplicationStatusPage {
    private final WebDriver driver;

    @Step("Достать номер заявки из страницы статуса заявки")
    public String getApplicationNumber() {
        WaitUtil.waitForPatternConsists(driver, By.xpath("//span[contains(text(), 'отправлена на рассмотрение')]"), ".*\\d+.*", Duration.ofSeconds(10));
        WebElement spanWithApplicationNumber = driver.findElement(By.xpath("//span[contains(text(), 'отправлена на рассмотрение')]"));
        return spanWithApplicationNumber.getText().split(" ")[3];
    }

    public ApplicationStatusPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Получить текст кнопки")
    public String getCreateNewApplicationBtnText() {
        return new Button(driver, CREATE_NEW_APPLICATION_BUTTON).getText();
    }
}
