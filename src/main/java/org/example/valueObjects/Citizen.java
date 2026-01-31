package org.example.valueObjects;

public class Citizen {
    String surnameField;
    String nameField;
    String patronymicField;
    String birthDateField;
    String sexField;
    String passportNumberField;
    String registrationAddressField;

    public String getSurnameField() {
        return surnameField;
    }

    public String getNameField() {
        return nameField;
    }

    public String getPatronymicField() {
        return patronymicField;
    }

    public String getBirthDateField() {
        return birthDateField;
    }

    public String getSexField() {
        return sexField;
    }

    public String getPassportNumberField() {
        return passportNumberField;
    }

    public String getRegistrationAddressField() {
        return registrationAddressField;
    }

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
