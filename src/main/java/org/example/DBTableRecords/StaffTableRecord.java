package org.example.DBTableRecords;

import java.time.LocalDate;

public record StaffTableRecord(
        String surname,
        String name,
        String middlename,
        LocalDate dateofbirth,
        String passportnumber,
        String phonenumber
) {}