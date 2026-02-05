package org.example.pages;

import io.qameta.allure.Step;
import org.example.elements.Table;
import org.openqa.selenium.WebDriver;


public class ApplicationsAdministrationPage {

    private final Table table;

    public ApplicationsAdministrationPage(WebDriver driver) {
        this.table = new Table(driver);
    }

    @Step("Получить номер первой заявки в таблице администрирования заявок")
    public String getApplicationNumberFromTable() {
        return table.getCellText(1, 1);
    }
}