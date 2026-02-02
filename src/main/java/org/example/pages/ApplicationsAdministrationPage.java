package org.example.pages;

import org.example.elements.Table;
import org.openqa.selenium.WebDriver;


public class ApplicationsAdministrationPage {

    private Table table;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ApplicationsAdministrationPage(WebDriver driver) {

        this.table = new Table(driver);
    }


    public boolean findApplicationByNumber(String applicationNumber) {
        int row = table.findRowByColumnValue(1, applicationNumber);
        return table.getCellText(row, 1).equals(applicationNumber);
    }
}