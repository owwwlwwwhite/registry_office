package org.example.ui.valueObjects;

import lombok.Getter;

@Getter
public class Applicant {
    private final String surnameField;
    private final String nameField;
    private final String patronymicField;
    private final String phoneNumberField;
    private final String passportNumberField;
    private final String applicantAddressField;

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
