package org.example.pages;

import org.example.elements.Table;
import org.openqa.selenium.WebDriver;


public class ApplicationsAdministrationPage {

    private final Table table;

    public ApplicationsAdministrationPage(WebDriver driver) {
        this.table = new Table(driver);
    }

    public boolean isApplicationNumberEqualsExpected(String applicationNumber) {
        int row = table.findRowByColumnValue(1, applicationNumber);
        return table.getCellText(row, 1).equals(applicationNumber);
    }
}