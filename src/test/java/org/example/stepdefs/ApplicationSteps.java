package org.example.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import lombok.extern.log4j.Log4j2;
import org.example.test_data.DataFactory;
import org.example.ui.DriverManager;
import org.example.ui.pages.*;
import org.example.ui.valueObjects.Mode;
import org.example.utils.PropertyLoaderUtil;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.Properties;

@Log4j2
public class ApplicationSteps {
    private final WebDriver driver = DriverManager.getInstance().getDriver();
    private final Properties testProp = PropertyLoaderUtil.loadProperty("test_auth.properties");
    private MainPage mainPage;
    private ApplicantDataPage applicantDataPage;
    private ApplicationStatusPage applicationStatusPage;
    private ChoiceOfServicePage choiceOfServicePage;
    private CitizenDataPage citizenDataPage;
    private ServiceDataPage deathServiceDataPage;

    @Given("пользователь зашел на сайт ЗАГСа")
    public void enterSite() {
        WebDriverManager.chromedriver().setup();
        driver.get(Objects.requireNonNull(testProp).getProperty("url"));
        log.info("Setup driver");
        mainPage = new MainPage(driver);
        applicantDataPage = new ApplicantDataPage(driver);
        applicationStatusPage = new ApplicationStatusPage(driver);
        choiceOfServicePage = new ChoiceOfServicePage(driver);
        citizenDataPage = new CitizenDataPage(driver);
        deathServiceDataPage = new ServiceDataPage(driver, Mode.DEATH);

        log.info("Initialized all pages");
    }

    @When("зашел как пользователь")
    public void enterAsUser() {
        mainPage.enterAsUser();
    }

    @And("заполнил форму Данные заявителя валидными данными, нажал кнопку Далее")
    public void fillApplicantDataWithValidDataAndPressProceedButton() {
        applicantDataPage.fillFormAndSubmit(DataFactory.getApplicant());
    }

    @And("выбрал услугу {string}")
    public void chooseDeathRegistrationService(String mode) {
        switch (mode) {
            case "Регистрация смерти":
                choiceOfServicePage.selectRegistration(Mode.DEATH);
                break;
            case "Регистрация рождения":
                choiceOfServicePage.selectRegistration(Mode.BIRTH);
                break;
            case "Регистрация брака":
                choiceOfServicePage.selectRegistration(Mode.MARRIAGE);
                break;
        }
    }

    @And("заполнил форму Данные гражданина валидными данными, нажал кнопку Далее")
    public void fillCitizenDataWithValidDataAndPressProceedButton() {
        citizenDataPage.fillFormAndSubmit(DataFactory.getCitizen());
    }

    @And("заполнил форму Данные услуги валидными данными, нажимает кнопку Далее")
    public void fillServiceDataWithValidDataAndPressProceedButton() {
        deathServiceDataPage.fillFormAndSubmit(DataFactory.getServiceDeathData());
    }

    @Then("на странице отображается номер заявки")
    public void successMessageAndApplicationNumber() {
        Assertions.assertNotEquals("0", applicationStatusPage.getApplicationNumber());
        DriverManager.getInstance().closeDriver();
    }
}
