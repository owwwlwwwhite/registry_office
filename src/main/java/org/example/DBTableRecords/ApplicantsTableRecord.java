package org.example.DBTableRecords;

public record ApplicantsTableRecord(
        String surname,
        String name,
        String middlename,
        String passportnumber,
        String phonenumber,
        String registration_address
) {}