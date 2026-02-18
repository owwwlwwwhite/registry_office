package org.example.db.DBTableRecords;

import java.time.LocalDate;

public record MerrigeCertificatesTableRecord(
        LocalDate dateofmerrige,
        String surnameofspouse,
        String newsurnameofspouse,
        String nameofspouse,
        String middlenameofspouse,
        String passportnumberofspouse,
        LocalDate dateofbirthofspouse
) {}