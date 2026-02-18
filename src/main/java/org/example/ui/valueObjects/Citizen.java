package org.example.ui.valueObjects;

import lombok.Getter;

@Getter
public class Citizen {
    String surnameField;
    String nameField;
    String patronymicField;
    String birthDateField;
    String sexField;
    String passportNumberField;
    String registrationAddressField;

    public Citizen(
            String surnameField,
            String nameField,
            String patronymicField,
            String birthDateField,
            String sexField,
            String passportNumberField,
            String registrationAddressField
    ) {
        this.surnameField = surnameField;
        this.nameField = nameField;
        this.patronymicField = patronymicField;
        this.birthDateField = birthDateField;
        this.sexField = sexField;
        this.passportNumberField = passportNumberField;
        this.registrationAddressField = registrationAddressField;
    }


}
