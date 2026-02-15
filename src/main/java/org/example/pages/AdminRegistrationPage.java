package org.example.pages;

import io.qameta.allure.Step;
import org.example.elements.Button;
import org.example.elements.InputField;
import org.example.valueObjects.Admin;
import org.openqa.selenium.WebDriver;

import static org.example.Constants.*;

public class AdminRegistrationPage {
    private final InputField surnameField;
    private final InputField nameField;
    private final InputField patronymicField;
    private final InputField phoneNumberField;
    private final InputField passportNumberField;
    private final InputField birthDate;
    private final Button proceedButton;

    public AdminRegistrationPage(WebDriver driver) {
        this.surnameField = new InputField(driver, SURNAME);
        this.nameField = new InputField(driver, NAME);
        this.patronymicField = new InputField(driver, PATRONYMIC);
        this.phoneNumberField = new InputField(driver, PHONE_NUMBER);
        this.passportNumberField = new InputField(driver, PASSPORT_NUMBER);
        this.birthDate = new InputField(driver, BIRTH_DATE);

        this.proceedButton = new Button(driver, PROCEED_BUTTON);
    }

    @Step("Заполнить форму Данные регистрации админа данными и нажать кнопку продолжения")
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
