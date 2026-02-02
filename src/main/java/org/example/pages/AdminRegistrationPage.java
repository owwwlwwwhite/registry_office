package org.example.pages;

import org.example.elements.Button;
import org.example.elements.InputField;
import org.example.valueObjects.Admin;
import org.openqa.selenium.WebDriver;

public class AdminRegistrationPage {
    private final WebDriver driver;

    private final InputField surnameField;
    private final InputField nameField;
    private final InputField patronymicField;

    private final InputField phoneNumberField;
    private final InputField passportNumberField;
    private final InputField birthDate;

    private final Button proceedButton;

    public AdminRegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.surnameField = new InputField(driver, "Фамилия");
        this.nameField = new InputField(driver, "Имя");
        this.patronymicField = new InputField(driver, "Отчество");
        this.phoneNumberField = new InputField(driver, "Телефон");
        this.passportNumberField = new InputField(driver, "Номер паспорта");
        this.birthDate = new InputField(driver, "Дата рождения");

        this.proceedButton = new Button(driver, "Далее");
    }

    public void fillFormAndSubmit(Admin adminData) {
        surnameField.enterText(adminData.getSurnameField());
        nameField.enterText(adminData.getNameField());
        patronymicField.enterText(adminData.getPatronymicField());
        phoneNumberField.enterText(adminData.getPhoneNumberField());
        passportNumberField.enterText(adminData.getPassportNumberField());
        birthDate.enterText(adminData.getBirthDate());
        proceedButton.click();
    }
}
