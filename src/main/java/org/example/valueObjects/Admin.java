package org.example.valueObjects;

import org.example.elements.InputField;

public class Admin {

    private String surnameField;
    private String nameField;
    private String patronymicField;

    private String phoneNumberField;
    private String passportNumberField;
    private String birthDate;

    public String getSurnameField() {
        return surnameField;
    }

    public String getNameField() {
        return nameField;
    }

    public String getPatronymicField() {
        return patronymicField;
    }

    public String getPhoneNumberField() {
        return phoneNumberField;
    }

    public String getPassportNumberField() {
        return passportNumberField;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Admin(
            String surnameField,
            String nameField,
            String patronymicField,
            String phoneNumberField,
            String passportNumberField,
            String birthDate
    ) {
        this.surnameField = surnameField;
        this.nameField = nameField;
        this.patronymicField = patronymicField;
        this.phoneNumberField = phoneNumberField;
        this.passportNumberField = passportNumberField;
        this.birthDate = birthDate;
    }
}
