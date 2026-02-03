package org.example.pages;

import org.example.GlobalConstants;
import org.example.elements.Button;
import org.example.elements.InputField;
import org.example.valueObjects.Applicant;
import org.openqa.selenium.WebDriver;

public class ApplicantDataPage extends GlobalConstants {

    private final InputField surnameField;
    private final InputField nameField;
    private final InputField patronymicField;
    private final InputField phoneNumberField;
    private final InputField passportNumberField;
    private final InputField applicantAddressField;

    private final Button proceedButton;

    public ApplicantDataPage(WebDriver driver) {
        this.surnameField = new InputField(driver, SURNAME);
        this.nameField = new InputField(driver, NAME);
        this.patronymicField = new InputField(driver, PATRONYMIC);
        this.phoneNumberField = new InputField(driver, PHONE_NUMBER);
        this.passportNumberField = new InputField(driver, PASSPORT_NUMBER);
        this.applicantAddressField = new InputField(driver, REGISTRATION_ADDRESS);

        this.proceedButton = new Button(driver, PROCEED_BUTTON);
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
