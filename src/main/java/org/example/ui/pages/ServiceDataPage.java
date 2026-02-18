package org.example.ui.pages;

import io.qameta.allure.Step;
import org.example.ui.valueObjects.Mode;
import org.example.ui.elements.Button;
import org.example.ui.elements.InputField;
import org.example.ui.valueObjects.ServiceData;
import org.openqa.selenium.WebDriver;

public class ServiceDataPage {
    private final Mode mode;
    private InputField deathDateField;
    private InputField deathPlaceField;
    private InputField birthPlaceField;
    private InputField motherField;
    private InputField fatherField;
    private InputField grandMotherField;
    private InputField grandFatherField;
    private InputField registrationDateField;
    private InputField newSurnameField;
    private InputField spouseSurnameField;
    private InputField spouseNameField;
    private InputField spousePatronymicField;
    private InputField spouseBirthDateField;
    private InputField spousePassportNumberField;

    private final Button submitButton;

    public ServiceDataPage(WebDriver driver, Mode mode) {
        this.mode = mode;
        switch (mode) {
            case DEATH:
                this.deathDateField = new InputField(driver, "Дата смерти");
                this.deathPlaceField = new InputField(driver, "Место смерти");
                break;
            case BIRTH:
                this.birthPlaceField = new InputField(driver, "Место рождения");
                this.motherField = new InputField(driver, "Мать");
                this.fatherField = new InputField(driver, "Отец");
                this.grandMotherField = new InputField(driver, "Бабушка");
                this.grandFatherField = new InputField(driver, "Дедушка");
                break;
            case MARRIAGE:
                this.registrationDateField = new InputField(driver, "Дата регистрации");
                this.newSurnameField = new InputField(driver, "Новая фамилия");
                this.spouseSurnameField = new InputField(driver, "Фамилия супруга/и");
                this.spouseNameField = new InputField(driver, "Имя супруга/и");
                this.spousePatronymicField = new InputField(driver, "Отчество супруга/и");
                this.spouseBirthDateField = new InputField(driver, "Дата рождения супруга/и");
                this.spousePassportNumberField = new InputField(driver, "Номер паспорта супруга/и");
                break;
        }
        this.submitButton = new Button(driver, "Завершить");
    }

    @Step("Заполнить форму Данные услуги данными '{serviceData}' и нажать кнопку продолжения")
    public void fillFormAndSubmit(ServiceData serviceData) {
        switch (mode) {
            case DEATH:
                deathDateField.enterText(serviceData.getDeathDateField());
                deathPlaceField.enterText(serviceData.getDeathPlaceField());

                submitButton.click();
                break;
            case BIRTH:
                birthPlaceField.enterText(serviceData.getBirthPlaceField());
                motherField.enterText(serviceData.getMotherField());
                fatherField.enterText(serviceData.getFatherField());
                grandMotherField.enterText(serviceData.getGrandMotherField());
                grandFatherField.enterText(serviceData.getGrandFatherField());

                submitButton.click();
                break;
            case MARRIAGE:
                registrationDateField.enterText(serviceData.getRegistrationDateField());
                newSurnameField.enterText(serviceData.getNewSurnameField());
                spouseSurnameField.enterText(serviceData.getSpouseSurnameField());
                spouseNameField.enterText(serviceData.getSpouseNameField());
                spousePatronymicField.enterText(serviceData.getSpousePatronymicField());
                spouseBirthDateField.enterText(serviceData.getSpouseBirthDateField());
                spousePassportNumberField.enterText(serviceData.getSpousePassportNumberField());

                submitButton.click();
                break;
        }
    }
}
