package org.example.utils;

import org.example.valueObjects.Admin;
import org.example.valueObjects.Applicant;
import org.example.valueObjects.Citizen;
import org.example.valueObjects.ServiceData;

public class DataFactoryUtil {
    protected final static Applicant applicant = new Applicant("Фа", "Им", "Отчес", "1234567", "123456", "абвг");
    protected final static Citizen citizen = new Citizen("Фа", "Им", "Отчес", "01062026", "муж", "123456", "абвг");
    protected final static ServiceData serviceDeathData = new ServiceData
            .Builder()
            .setDeathData("01.01.2000", "абвг")
            .build();
    protected final static ServiceData serviceMarriageData = new ServiceData
            .Builder()
            .setMarriageData("01012000",
                    "фф",
                    "фф",
                    "фф",
                    "ффыыв",
                    "01011995",
                    "123456")
            .build();
    protected final static ServiceData serviceBirthData = new ServiceData
            .Builder()
            .setBirthData("абвг", "аa", "аа", "аа", "аа")
            .build();
    protected final static Admin adminData = new Admin(
            "фф",
            "фф",
            "ффыыв",
            "1234567",
            "123456",
            "01012000");

    public static Applicant getApplicant() {
        return applicant;
    }

    public static Citizen getCitizen() {
        return citizen;
    }

    public static ServiceData getServiceDeathData() {
        return serviceDeathData;
    }

    public static ServiceData getServiceMarriageData() {
        return serviceMarriageData;
    }

    public static ServiceData getServiceBirthData() {
        return serviceBirthData;
    }

    public static Admin getAdminData() {
        return adminData;
    }
}
