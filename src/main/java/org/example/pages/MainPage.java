package org.example.pages;

import io.qameta.allure.Step;
import org.example.elements.Button;
import org.openqa.selenium.WebDriver;

import static org.example.GlobalConstants.ENTER_AS_ADMIN_BUTTON;
import static org.example.GlobalConstants.ENTER_AS_USER_BUTTON;

public class MainPage {

    private final Button enterAsUserButton;
    private final Button enterAsAdminButton;

    public MainPage(WebDriver driver) {
        enterAsUserButton = new Button(driver, ENTER_AS_USER_BUTTON);
        enterAsAdminButton = new Button(driver, ENTER_AS_ADMIN_BUTTON);
    }

    @Step("Войти как пользователь")
    public void enterAsUser() {
        enterAsUserButton.click();
    }
    @Step("Войти как Администратор")
    public void enterAsAdmin() {
        enterAsAdminButton.click();
    }
}
