package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.valueObjects.Admin;
import org.example.valueObjects.Applicant;
import org.example.valueObjects.Citizen;
import org.example.valueObjects.ServiceData;

public class DataFactoryUtil {
    private static final Logger dataLogger = LogManager.getLogger(DataFactoryUtil.class);

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
        dataLogger.info("Get Applicant value object");
        return applicant;
    }

    public static Citizen getCitizen() {
        dataLogger.info("Get Citizen value object");
        return citizen;
    }

    public static ServiceData getServiceDeathData() {
        dataLogger.info("Get service Death data value object");
        return serviceDeathData;
    }

    public static ServiceData getServiceMarriageData() {
        dataLogger.info("Get service Marriage data value object");
        return serviceMarriageData;
    }

    public static ServiceData getServiceBirthData() {
        dataLogger.info("Get service Birth data value object");
        return serviceBirthData;
    }

    public static Admin getAdminData() {
        dataLogger.info("Get Admin value object");
        return adminData;
    }
}
