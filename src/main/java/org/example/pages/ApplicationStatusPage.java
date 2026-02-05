package org.example.pages;

import org.example.elements.Button;
import org.example.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ApplicationStatusPage {
    private final String applicationNumber;
    private final Button createNewApplicationBtn;

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public ApplicationStatusPage(WebDriver driver) {
        WaitUtil.waitForVisible(driver, By.xpath("//span[contains(text(), 'отправлена на рассмотрение')]"), Duration.ofSeconds(5));
        WebElement spanWithApplicationNumber = driver.findElement(By.xpath("//span[contains(text(), 'отправлена на рассмотрение')]"));
        this.applicationNumber = spanWithApplicationNumber.getText().split(" ")[3];
        this.createNewApplicationBtn = new Button(driver, "Создать новую заявку");
    }

    public Button getCreateNewApplicationBtn() {
        return createNewApplicationBtn;
    }
}
