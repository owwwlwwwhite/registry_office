package org.example.pages;

import org.example.elements.Table;
import org.openqa.selenium.WebDriver;


public class ApplicationsAdministrationPage {

    private final Table table;

    public ApplicationsAdministrationPage(WebDriver driver) {
        this.table = new Table(driver);
    }

    public String getApplicationNumberFromTable(String applicationNumber) {
        return table.getCellText(1, 1);
    }
}