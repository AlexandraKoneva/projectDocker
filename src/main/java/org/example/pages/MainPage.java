package org.example.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='d-flex col-2 col-xl-3 justify-content-end d-flex']//following-sibling::span[@class='ms-2']")
    private WebElement personalCabinetButton;

    @FindBy(xpath = "//div[@class='head-bar']//div[contains(@class,'content-end')]//li/a|//div[@class='head-bar']//div[contains(@class," +
            "'content-end')]//li//span")
    private List<WebElement> personalMenuButtons;

    @FindBy(xpath = "//input[@id='input-auth1']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='input-auth2']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit' and text()='Войти']")
    private WebElement enterButton;

    @FindBy(xpath = "//*[contains(@class,'lk-logout')]")
    private WebElement exitButton;

    public void logIn(String login, String password) {
        wait.until(ExpectedConditions.visibilityOf(personalCabinetButton));
        personalCabinetButton.click();

        chooseButtonInMainPersonalCabinet("Вход");

        wait.until(ExpectedConditions.visibilityOf(loginField));
        loginField.clear();
        loginField.sendKeys(login);

        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);

        enterButton.click();
    }

    public void checkLogIn() {
        try {
            personalCabinetButton.click();
        } catch (StaleElementReferenceException e) {
            new Actions(driver).moveToElement(personalCabinetButton).click().perform();
        }

        wait.until(ExpectedConditions.visibilityOf(exitButton));
        chooseButtonInMainPersonalCabinet("Выход");

        personalCabinetButton.click();
        chooseButtonInMainPersonalCabinet("Вход");
    }

    private void chooseButtonInMainPersonalCabinet(String nameButton) {
        boolean found = false;
        for (WebElement element : personalMenuButtons) {
            if (element.getText().equals(nameButton)) {
                element.click();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new NoSuchElementException("Кнопка с названием '" + nameButton + "' не найдена");
        }
    }
}
