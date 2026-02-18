package org.example.ui.elements;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

@Log4j2
public class Table {
    private final By.ByCssSelector rows = new By.ByCssSelector("thead ~ tr");
    private final WebDriver driver;

    public Table(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Взять значение из таблицы по координатам строка: '{row}', стобец: '{col}'")
    public String getCellText(int row, int col) {
        WaitUtil.waitForVisible(driver, rows, Duration.ofSeconds(5));
        log.info("Find table");
        List<WebElement> rowsElement = driver.findElements(rows);
        WebElement cell = rowsElement.get(row).findElement(By.xpath("//td[" + (col) + "]"));
        log.info("Find cell 1 1: {}", cell);
        log.info("Cell text is: {}", cell.getText());
        return cell.getText();
    }
}
