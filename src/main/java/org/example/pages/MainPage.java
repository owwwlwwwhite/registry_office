package org.example.pages;

import org.example.elements.Button;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private final WebDriver driver;
    private final Button enterAsUserButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        enterAsUserButton = new Button(driver, "Войти как пользователь");
    }

    public void enterAsUser() {
        enterAsUserButton.click();
    }
}
