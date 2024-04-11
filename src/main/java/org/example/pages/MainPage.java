package org.example.pages;

import org.junit.Assert;
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

    @FindBy(xpath = "//button[contains(@class,'catalog')]")
    private WebElement catalogButton;

    @FindBy(xpath = "//*[@class='head-dropdown-catalog__category']")
    private List<WebElement> menuOfCatalog1;

    @FindBy(xpath = "//*[@class='head-dropdown-catalog__subcat-list']/a")
    private List<WebElement> menuOfCatalog2;

    @FindBy(xpath = "//*[@class='head-dropdown-catalog__subsubcat d-block']//a")
    private List<WebElement> menuOfCatalog3;

    @FindBy(xpath = "//button[@class='btn btn-primary w-100']")
    private WebElement privacyPolicyButton;

    @FindBy(xpath = "//input[@placeholder='поиск в каталоге']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class='head-search__btn']")
    private WebElement searchButton;

    public void checkMainPageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(catalogButton));
        Assert.assertTrue("Кнопка Каталога не отображается", catalogButton.isDisplayed());
    }

    public void goToCatalog(String menu1, String menu2, String menu3) {
        catalogButton.click();
        wait.until(ExpectedConditions.visibilityOf(menuOfCatalog1.get(0)));

        WebElement foundMenu1 = menuOfCatalog1.stream()
                                              .filter(element -> element.getText().equals(menu1))
                                              .findFirst()
                                              .orElseThrow(() -> new NoSuchElementException("Меню1 не найдено: " + menu1));

        new Actions(driver).moveToElement(foundMenu1).perform();

        WebElement foundMenu2 = menuOfCatalog2.stream()
                                              .filter(element -> element.getText().equals(menu2))
                                              .findFirst()
                                              .orElseThrow(() -> new NoSuchElementException("Меню2 не найдено: " + menu2));

        new Actions(driver).moveToElement(foundMenu2).perform();

        WebElement foundMenu3 = menuOfCatalog3.stream()
                                              .filter(element -> element.getText().equals(menu3))
                                              .findFirst()
                                              .orElseThrow(() -> new NoSuchElementException("Меню3 не найдено: " + menu3));

        foundMenu3.click();
    }

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

    public void hidePrivacyPolicy() {
        privacyPolicyButton.click();
    }

    public void searchItem(String item){
        searchField.sendKeys(item);
        searchButton.click();
    }
}
