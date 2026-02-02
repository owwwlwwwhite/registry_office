package org.example.pages;

import org.example.elements.Button;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private final Button enterAsUserButton;
    private final Button enterAsAdminButton;

    public MainPage(WebDriver driver) {
        enterAsUserButton = new Button(driver, "Войти как пользователь");
        enterAsAdminButton = new Button(driver, "Войти как администратор");
    }

    public void enterAsUser() {
        enterAsUserButton.click();
    }
    public void enterAsAdmin() {
        enterAsAdminButton.click();
    }
}
