package org.example.db.DBTableRecords;

import java.time.LocalDate;

public record ApplicationsTableRecord(
        int applicationid,
        int applicantid,
        int citizenid,
        LocalDate dateofapplication,
        String kindofapplication
) {}
