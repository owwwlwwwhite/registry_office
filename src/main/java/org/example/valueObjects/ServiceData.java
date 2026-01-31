package org.example.valueObjects;

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

    public String getDeathDateField() {
        return deathDateField;
    }

    public String getDeathPlaceField() {
        return deathPlaceField;
    }

    public String getBirthPlaceField() {
        return birthPlaceField;
    }

    public String getMotherField() {
        return motherField;
    }

    public String getFatherField() {
        return fatherField;
    }

    public String getGrandMotherField() {
        return grandMotherField;
    }

    public String getGrandFatherField() {
        return grandFatherField;
    }

    public String getRegistrationDateField() {
        return registrationDateField;
    }

    public String getNewSurnameField() {
        return newSurnameField;
    }

    public String getSpouseSurnameField() {
        return spouseSurnameField;
    }

    public String getSpouseNameField() {
        return spouseNameField;
    }

    public String getSpousePatronymicField() {
        return spousePatronymicField;
    }

    public String getSpouseBirthDateField() {
        return spouseBirthDateField;
    }

    public String getSpousePassportNumberField() {
        return spousePassportNumberField;
    }

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
