package org.example.ui.valueObjects;

import lombok.Getter;

@Getter
public class ServiceData {
    String deathDateField;
    String deathPlaceField;
    String birthPlaceField;
    String motherField;
    String fatherField;
    String grandMotherField;
    String grandFatherField;
    String registrationDateField;
    String newSurnameField;
    String spouseSurnameField;
    String spouseNameField;
    String spousePatronymicField;
    String spouseBirthDateField;
    String spousePassportNumberField;

    private ServiceData(Builder builder) {
        this.deathDateField = builder.deathDateField;
        this.deathPlaceField = builder.deathPlaceField;
        this.birthPlaceField = builder.birthPlaceField;
        this.motherField = builder.motherField;
        this.fatherField = builder.fatherField;
        this.grandMotherField = builder.grandMotherField;
        this.grandFatherField = builder.grandFatherField;
        this.registrationDateField = builder.registrationDateField;
        this.newSurnameField = builder.newSurnameField;
        this.spouseSurnameField = builder.spouseSurnameField;
        this.spouseNameField = builder.spouseNameField;
        this.spousePatronymicField = builder.spousePatronymicField;
        this.spouseBirthDateField = builder.spouseBirthDateField;
        this.spousePassportNumberField = builder.spousePassportNumberField;
    }

    public static class Builder {
        String deathDateField;
        String deathPlaceField;
        String birthPlaceField;
        String motherField;
        String fatherField;
        String grandMotherField;
        String grandFatherField;
        String registrationDateField;
        String newSurnameField;
        String spouseSurnameField;
        String spouseNameField;
        String spousePatronymicField;
        String spouseBirthDateField;
        String spousePassportNumberField;

        public Builder setDeathData(String deathDate, String deathPlace) {
            this.deathDateField = deathDate;
            this.deathPlaceField = deathPlace;
            return this;
        }

        public Builder setBirthData(
                String birthPlaceField,
                String motherField,
                String fatherField,
                String grandMotherField,
                String grandFatherField
        ) {
            this.birthPlaceField = birthPlaceField;
            this.motherField = motherField;
            this.fatherField = fatherField;
            this.grandMotherField = grandMotherField;
            this.grandFatherField = grandFatherField;
            return this;
        }

        public Builder setMarriageData(
                String registrationDateField,
                String newSurnameField,
                String spouseSurnameField,
                String spouseNameField,
                String spousePatronymicField,
                String spouseBirthDateField,
                String spousePassportNumberField
        ) {
            this.registrationDateField = registrationDateField;
            this.newSurnameField = newSurnameField;
            this.spouseSurnameField = spouseSurnameField;
            this.spouseNameField = spouseNameField;
            this.spousePatronymicField = spousePatronymicField;
            this.spouseBirthDateField = spouseBirthDateField;
            this.spousePassportNumberField = spousePassportNumberField;
            return this;
        }

        public ServiceData build() {
            return new ServiceData(this);
        }
    }
}
