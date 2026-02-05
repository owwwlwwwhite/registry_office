package org.example;

import org.example.pages.*;
import org.example.utils.DataProviderUtil;
import org.example.valueObjects.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckApplicationsByAdminTest extends BaseTest {
    private String createApplication(Mode mode, ServiceData serviceData) {
        mainPage.enterAsUser();
        new ApplicantDataPage(driver).fillFormAndSubmit(DataProviderUtil.getApplicant());
        new ChoiceOfServicePage(driver).selectRegistration(mode);
        new CitizenDataPage(driver).fillFormAndSubmit(DataProviderUtil.getCitizen());
        new ServiceDataPage(driver, mode).fillFormAndSubmit(serviceData);

        return new ApplicationStatusPage(driver).getApplicationNumber();
    }

    private boolean applicationNumberIsExist(String applicationNumber) {
        driver.get(testProperties.getProperty("url"));

        new MainPage(driver).enterAsAdmin();
        new AdminRegistrationPage(driver).fillFormAndSubmit(DataProviderUtil.getAdminData());
        return new ApplicationsAdministrationPage(driver).isApplicationNumberEqualsExpected(applicationNumber);
    }

    @Test
    void checkMarriageApplicationCreation() {
        String applicationNumber = createApplication(Mode.MARRIAGE, DataProviderUtil.getServiceMarriageData());

        assertTrue(applicationNumberIsExist(applicationNumber));
    }

    @Test
    void checkDeathApplicationCreation() {
        String applicationNumber = createApplication(Mode.DEATH, DataProviderUtil.getServiceDeathData());

        assertTrue(applicationNumberIsExist(applicationNumber));
    }

    @Test
    void checkBirthApplicationCreation() {
        String applicationNumber = createApplication(Mode.BIRTH, DataProviderUtil.getServiceBirthData());

        assertTrue(applicationNumberIsExist(applicationNumber));
    }
}
