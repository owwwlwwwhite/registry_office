package org.example.pages;

import io.qameta.allure.Step;
import org.example.valueObjects.Mode;
import org.example.elements.Button;
import org.openqa.selenium.WebDriver;

import static org.example.Constants.*;

public class ChoiceOfServicePage {
    private final Button deathRegistrationBtn;
    private final Button birthRegistrationBtn;
    private final Button marriageRegistrationBtn;

    public ChoiceOfServicePage(WebDriver driver) {
        deathRegistrationBtn = new Button(driver, DEATH_REGISTRATION_BTN);
        marriageRegistrationBtn = new Button(driver, MARRIAGE_REGISTRATION_BTN);
        birthRegistrationBtn = new Button(driver, BIRTH_REGISTRATION_BTN);
    }

    @Step("Выбрать услугу '{reg}' на странице Выбор услуги")
    public void selectRegistration(Mode reg) {
        switch (reg) {
            case DEATH:
                deathRegistrationBtn.click();
                break;
            case BIRTH:
                birthRegistrationBtn.click();
                break;
            case MARRIAGE:
                marriageRegistrationBtn.click();
                break;
        }
    }
}
