package org.example.pages;

import org.example.elements.Table;
import org.openqa.selenium.WebDriver;


public class ApplicationsAdministrationPage {

    private final Table table;

    public ApplicationsAdministrationPage(WebDriver driver) {
        this.table = new Table(driver);
    }

    public boolean isApplicationNumberEqualsExpected(String applicationNumber) {
        System.out.println(table.getCellText(1, 1));
        return table.getCellText(1, 1).equals(applicationNumber);
    }
}