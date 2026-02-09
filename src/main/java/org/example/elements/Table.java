package org.example.elements;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class Table {

    private final static Logger tableLogger = LogManager.getLogger(Table.class);

    private final By.ByCssSelector rows = new By.ByCssSelector("thead ~ tr");
    private final WebDriver driver;

    public Table(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Взять значение из таблицы по координатам строка: '{row}', стобец: '{col}'")
    public String getCellText(int row, int col) {
        WaitUtil.waitForVisible(driver, rows, Duration.ofSeconds(5));
        tableLogger.info("Find table");
        List<WebElement> rowsElement = driver.findElements(rows);
        WebElement cell = rowsElement.get(row).findElement(By.xpath("//td[" + (col) + "]"));
        tableLogger.info("Find cell 1 1: {}", cell);
        tableLogger.info("Cell text is: {}", cell.getText());
        return cell.getText();
    }
}
