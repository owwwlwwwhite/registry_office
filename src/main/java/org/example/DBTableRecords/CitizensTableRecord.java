package org.example.DBTableRecords;

import java.time.LocalDate;

public record CitizensTableRecord(
        String surname,
        String name,
        String middlename,
        String passportnumber,
        LocalDate dateofbirth,
        String gender,
        String registration_address
) {}