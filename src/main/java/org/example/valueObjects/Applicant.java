package org.example.valueObjects;

public class Applicant {
    public String getSurnameField() {
        return surnameField;
    }

    public void setSurnameField(String surnameField) {
        this.surnameField = surnameField;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getPatronymicField() {
        return patronymicField;
    }

    public void setPatronymicField(String patronymicField) {
        this.patronymicField = patronymicField;
    }

    public String getPhoneNumberField() {
        return phoneNumberField;
    }

    public void setPhoneNumberField(String phoneNumberField) {
        this.phoneNumberField = phoneNumberField;
    }

    public String getPassportNumberField() {
        return passportNumberField;
    }

    public void setPassportNumberField(String passportNumberField) {
        this.passportNumberField = passportNumberField;
    }

    public String getApplicantAddressField() {
        return applicantAddressField;
    }

    public void setApplicantAddressField(String applicantAddressField) {
        this.applicantAddressField = applicantAddressField;
    }

    private String surnameField;

    private String nameField;

    private String patronymicField;

    private String phoneNumberField;

    private String passportNumberField;

    private String applicantAddressField;

    public Applicant(String surnameField,
                     String nameField,
                     String patronymicField,
                     String phoneNumberField,
                     String passportNumberField,
                     String applicantAddressField) {
        this.surnameField = surnameField;
        this.nameField = nameField;
        this.patronymicField = patronymicField;
        this.phoneNumberField = phoneNumberField;
        this.passportNumberField = passportNumberField;
        this.applicantAddressField = applicantAddressField;
    }
}
