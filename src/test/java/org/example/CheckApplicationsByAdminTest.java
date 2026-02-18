package org.example;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.example.ui.pages.ApplicationStatusPage;
import org.example.test_data.DataFactory;
import org.example.ui.valueObjects.Mode;
import org.example.ui.valueObjects.ServiceData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Раздел Администратора")
public class CheckApplicationsByAdminTest extends BaseTest {

    @Step("Найти заявку в таблице администрирования заявок")
    public String findApplicationNumber() {
        driver.get(testProperties.getProperty("url"));

        mainPage.enterAsAdmin();
        adminRegistrationPage.fillFormAndSubmit(DataFactory.getAdminData());
        return applicationsAdministrationPage.getApplicationNumberFromTable();
    }

    @Step("Создать заявку типа '{mode}' данными '{serviceData}'")
    public String createApplication(Mode mode, ServiceData serviceData) {
        mainPage.enterAsUser();
        applicantDataPage.fillFormAndSubmit(DataFactory.getApplicant());
        choiceOfServicePage.selectRegistration(mode);
        citizenDataPage.fillFormAndSubmit(DataFactory.getCitizen());
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
    @Severity(SeverityLevel.CRITICAL)
    @Link(url = "https://app.qase.io/project/ZDYS?suite=102&tab=&previewMode=side&case=480")
    @DisplayName("Проверка отображения заявки на регистрацию рождения в панели администратора")
    void checkBirthApplicationCreation() {
        String applicationNumber = createApplication(Mode.BIRTH, DataFactory.getServiceBirthData());

        assertEquals(applicationNumber, findApplicationNumber());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка отображения заявки на регистрацию брака в панели администратора")
    @Link(url = "https://app.qase.io/project/ZDYS?suite=102&tab=&previewMode=side&case=479")
    void checkMarriageApplicationCreation() {
        String applicationNumber = createApplication(Mode.MARRIAGE, DataFactory.getServiceMarriageData());

        assertEquals(applicationNumber, findApplicationNumber());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка отображения заявки на регистрацию смерти в панели администратора")
    @Link(url = "https://app.qase.io/project/ZDYS?suite=102&tab=&previewMode=side&case=481")
    void checkDeathApplicationCreation() {
        String applicationNumber = createApplication(Mode.DEATH, DataFactory.getServiceDeathData());

        assertEquals(applicationNumber, findApplicationNumber());
    }
}
