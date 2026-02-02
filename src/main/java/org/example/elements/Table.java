package org.example.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Table {
    @FindBy(tagName = "table")  // основная таблица
    private WebElement table;

    @FindBy(xpath = "thead > tr")  // заголовки колонок
    private WebElement headers;

    private final List<WebElement> rows;

    public Table(WebDriver driver) {
        rows = driver.findElements(new By.ByCssSelector("thead ~ tr"));
        PageFactory.initElements(driver, this);
    }

    public String getCellText(int row, int col) {
        WebElement cell = rows.get(row).findElement(By.xpath("//td[" + (col) + "]"));
        return cell.getText();
    }

    public int findRowByColumnValue(int col, String value) {
        for (int i = 0; i < rows.size(); i++) {
            if (getCellText(i, col).equals(value)) {
                return i+1;
            }
        }
        return -1;
    }
}
