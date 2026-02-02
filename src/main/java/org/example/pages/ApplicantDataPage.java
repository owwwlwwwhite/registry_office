package org.example.pages;

import org.example.elements.Button;
import org.example.elements.InputField;
import org.example.valueObjects.Applicant;
import org.openqa.selenium.WebDriver;

public class ApplicantDataPage {

    private final InputField surnameField;
    private final InputField nameField;
    private final InputField patronymicField;
    private final InputField phoneNumberField;
    private final InputField passportNumberField;
    private final InputField applicantAddressField;

    private final Button proceedButton;

    public ApplicantDataPage(WebDriver driver) {
        this.surnameField = new InputField(driver, "Фамилия");
        this.nameField = new InputField(driver, "Имя");
        this.patronymicField = new InputField(driver, "Отчество");
        this.phoneNumberField = new InputField(driver, "Телефон");
        this.passportNumberField = new InputField(driver, "Номер паспорта");
        this.applicantAddressField = new InputField(driver, "Адрес прописки");

        this.proceedButton = new Button(driver, "Далее");
    }

    public void fillFormAndSubmit(Applicant applicant) {
        surnameField.enterText(applicant.getSurnameField());
        nameField.enterText(applicant.getNameField());
        patronymicField.enterText(applicant.getPatronymicField());
        phoneNumberField.enterText(applicant.getPhoneNumberField());
        passportNumberField.enterText(applicant.getPassportNumberField());
        applicantAddressField.enterText(applicant.getApplicantAddressField());
        proceedButton.click();
    }
}
