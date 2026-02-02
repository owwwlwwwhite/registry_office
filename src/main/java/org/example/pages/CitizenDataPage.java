package org.example.pages;

import org.example.elements.Button;
import org.example.elements.InputField;
import org.example.valueObjects.Applicant;
import org.example.valueObjects.Citizen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CitizenDataPage {
    private final WebDriver driver;

    private final InputField surnameField;
    private final InputField nameField;
    private final InputField patronymicField;

    private final InputField birthDate;
    private final InputField passportNumberField;
    private final InputField sexField;
    private final InputField registrationAddressField;

    private final Button proceedButton;

    public CitizenDataPage(WebDriver driver) {
        this.driver = driver;

        this.surnameField = new InputField(driver, "Фамилия");
        this.nameField = new InputField(driver, "Имя");
        this.patronymicField = new InputField(driver, "Отчество");
        this.birthDate = new InputField(driver, "Дата рождения");
        this.sexField = new InputField(driver, "Пол");
        this.passportNumberField = new InputField(driver, "Номер паспорта");
        this.registrationAddressField = new InputField(driver, "Адрес прописки");

        this.proceedButton = new Button(driver, "Далее");
        PageFactory.initElements(driver, this);
    }

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
