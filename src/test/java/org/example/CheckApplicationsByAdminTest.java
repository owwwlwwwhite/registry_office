package org.example;

import io.qameta.allure.Step;
import org.example.pages.ApplicationStatusPage;
import org.example.utils.DataFactoryUtil;
import org.example.valueObjects.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Раздел Администратора")
public class CheckApplicationsByAdminTest extends BaseTest {

    @Step("Найти заявку по номеру '{applicationNumber}'")
    public String findApplicationNumber(String applicationNumber) {
        driver.get(testProperties.getProperty("url"));

        mainPage.enterAsAdmin();
        adminRegistrationPage.fillFormAndSubmit(DataFactoryUtil.getAdminData());
        return applicationsAdministrationPage.getApplicationNumberFromTable();
    }

    @Step("Создать заявку типа '{mode}' данными '{serviceData}'")
    public String createApplication(Mode mode, ServiceData serviceData) {
        mainPage.enterAsUser();
        applicantDataPage.fillFormAndSubmit(DataFactoryUtil.getApplicant());
        choiceOfServicePage.selectRegistration(mode);
        citizenDataPage.fillFormAndSubmit(DataFactoryUtil.getCitizen());
        switch (mode) {
            case MARRIAGE:
                marriageServiceDataPage.fillFormAndSubmit(serviceData);
                break;
            case DEATH:
                deathServiceDataPage.fillFormAndSubmit(serviceData);
                break;
            case BIRTH:
                birthServiceDataPage.fillFormAndSubmit(serviceData);
                break;
        }
        return new ApplicationStatusPage(driver).getApplicationNumber();
    }

    @Test
    @DisplayName("Проверка отображения заявки на регистрацию брака в панели администратора")
    void checkMarriageApplicationCreation() {
        String applicationNumber = createApplication(Mode.MARRIAGE, DataFactoryUtil.getServiceMarriageData());

        assertEquals(applicationNumber, findApplicationNumber(applicationNumber));
    }

    @Test
    @DisplayName("Проверка отображения заявки на регистрацию смерти в панели администратора")
    void checkDeathApplicationCreation() {
        String applicationNumber = createApplication(Mode.DEATH, DataFactoryUtil.getServiceDeathData());

        assertEquals(applicationNumber, findApplicationNumber(applicationNumber));
    }

    @Test
    @DisplayName("Проверка отображения заявки на регистрацию рождения в панели администратора")
    void checkBirthApplicationCreation() {
        String applicationNumber = createApplication(Mode.BIRTH, DataFactoryUtil.getServiceBirthData());

        assertEquals(applicationNumber, findApplicationNumber(applicationNumber));
    }
}
