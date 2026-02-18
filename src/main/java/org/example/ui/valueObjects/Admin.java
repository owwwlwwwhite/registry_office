package org.example.ui.valueObjects;

import lombok.Getter;

@Getter
public class Admin {
    private final String surnameField;
    private final String nameField;
    private final String patronymicField;
    private final String phoneNumberField;
    private final String passportNumberField;
    private final String birthDate;

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
