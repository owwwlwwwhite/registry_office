package org.example;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.DataFactoryUtil;
import org.example.valueObjects.Mode;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Раздел Пользователя")
public class CreateUserApplicationsTest extends BaseTest {

    private static final Logger testLogger = LogManager.getLogger(CreateUserApplicationsTest.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link(url = "https://app.qase.io/project/ZDYS?suite=3&tab=&previewMode=side&case=478")
    @DisplayName("Проверка успешного заполнения заявки на регистрацию смерти")
    void RegisterDeathApplication() {
        testLogger.info("Start RegisterDeathApplication test");
        mainPage.enterAsUser();
        testLogger.info("Entered as user");
        applicantDataPage.fillFormAndSubmit(DataFactoryUtil.getApplicant());
        testLogger.info("Filled and submitted applicant data form");
        choiceOfServicePage.selectRegistration(Mode.DEATH);
        testLogger.info("Selected death service");
        citizenDataPage.fillFormAndSubmit(DataFactoryUtil.getCitizen());
        testLogger.info("Filled and submitted citizen data form");
        deathServiceDataPage.fillFormAndSubmit(DataFactoryUtil.getServiceDeathData());
        testLogger.info("Filled and submitted death service form");
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtnText());
    }

    @Test
    @Link(url = "https://app.qase.io/project/ZDYS?suite=3&tab=&previewMode=side&case=477")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка успешного заполнения заявки на регистрацию рождения")
    void registerBirthApplication() {
        mainPage.enterAsUser();
        applicantDataPage.fillFormAndSubmit(DataFactoryUtil.getApplicant());
        choiceOfServicePage.selectRegistration(Mode.BIRTH);
        citizenDataPage.fillFormAndSubmit(DataFactoryUtil.getCitizen());
        birthServiceDataPage.fillFormAndSubmit(DataFactoryUtil.getServiceBirthData());
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtnText());
    }

    @Test
    @Link(url = "https://app.qase.io/project/ZDYS?suite=3&tab=properties&previewMode=side&case=476")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка успешного заполнения заявки на регистрацию брака")
    void registerMarriageApplication() {
        mainPage.enterAsUser();
        applicantDataPage.fillFormAndSubmit(DataFactoryUtil.getApplicant());
        choiceOfServicePage.selectRegistration(Mode.MARRIAGE);
        citizenDataPage.fillFormAndSubmit(DataFactoryUtil.getCitizen());
        marriageServiceDataPage.fillFormAndSubmit(DataFactoryUtil.getServiceMarriageData());
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtnText());
    }
}
