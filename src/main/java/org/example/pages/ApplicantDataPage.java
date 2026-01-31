package org.example.pages;

import org.example.elements.InputField;
import org.example.valueObjects.Applicant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicantDataPage {

    private final WebDriver driver;

    private final InputField surnameField;
    private final InputField nameField;
    private final InputField patronymicField;
    private final InputField phoneNumberField;
    private final InputField passportNumberField;
    private final InputField applicantAddressField;

    @FindBy(xpath = "//button[contains(text(),'Далее')]")
    private WebElement proceedButton;

    public ApplicantDataPage(WebDriver driver) {
        this.driver = driver;

        this.surnameField = new InputField(driver, "Фамилия");
        this.nameField = new InputField(driver, "Имя");
        this.patronymicField = new InputField(driver, "Отчество");
        this.phoneNumberField = new InputField(driver, "Телефон");
        this.passportNumberField = new InputField(driver, "Номер паспорта");
        this.applicantAddressField = new InputField(driver, "Адрес прописки");

        PageFactory.initElements(driver, this);
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
