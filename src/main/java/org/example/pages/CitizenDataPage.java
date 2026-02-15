package org.example.pages;

import io.qameta.allure.Step;
import org.example.elements.Button;
import org.example.elements.InputField;
import org.example.valueObjects.Citizen;
import org.openqa.selenium.WebDriver;

import static org.example.Constants.*;

public class CitizenDataPage {

    private final InputField surnameField;
    private final InputField nameField;
    private final InputField patronymicField;
    private final InputField birthDate;
    private final InputField passportNumberField;
    private final InputField sexField;
    private final InputField registrationAddressField;

    private final Button proceedButton;

    public CitizenDataPage(WebDriver driver) {
        this.surnameField = new InputField(driver, SURNAME);
        this.nameField = new InputField(driver, NAME);
        this.patronymicField = new InputField(driver, PATRONYMIC);
        this.birthDate = new InputField(driver, BIRTH_DATE);
        this.sexField = new InputField(driver, SEX);
        this.passportNumberField = new InputField(driver, PASSPORT_NUMBER);
        this.registrationAddressField = new InputField(driver, REGISTRATION_ADDRESS);

        this.proceedButton = new Button(driver, PROCEED_BUTTON);
    }

    @Step("Заполнить форму Данные гражданина данными '{citizen}' и нажать кнопку продолжения")
    public void fillFormAndSubmit(Citizen citizen) {
        surnameField.enterText(citizen.getSurnameField());
        nameField.enterText(citizen.getNameField());
        patronymicField.enterText(citizen.getPatronymicField());
        birthDate.enterText(citizen.getBirthDateField());
        sexField.enterText(citizen.getSexField());
        passportNumberField.enterText(citizen.getPassportNumberField());
        registrationAddressField.enterText(citizen.getRegistrationAddressField());
        proceedButton.click();
    }
}
