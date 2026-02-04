package org.example.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class Table {
    private final List<WebElement> rows;

    public Table(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        rows = driver.findElements(new By.ByCssSelector("thead ~ tr"));
    }

    public String getCellText(int row, int col) {
        WebElement cell = rows.get(row).findElement(By.xpath("//td[" + (col) + "]"));
        return cell.getText();
    }
}
