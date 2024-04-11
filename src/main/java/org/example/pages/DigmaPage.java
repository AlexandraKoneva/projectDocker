package org.example.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DigmaPage extends BasePage {

    public DigmaPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[@class='mb-md-5 mb-4'")
    private WebElement header;

    @FindBy(xpath = "//button[contains(@class,'rs-to-cart')]")
    private WebElement addItemToCartButton;

    @FindBy(xpath = "//*[text()='Товары в корзине']//ancestor::div[@class='modal-content']")
    private WebElement modalWindow;

    @FindBy(xpath = "//*[@class='modal-body']//*[contains(@class,'btn btn-primary')]")
    private WebElement goToCartButton;

    public void pageIsOpened(String pageName) {
        wait.until(ExpectedConditions.visibilityOf(header));
        Assert.assertEquals("Заголовок страницы отличается от ожидаемого", header.getText(), pageName);
    }

    public void addItemToCart() {
        wait.until(ExpectedConditions.visibilityOf(addItemToCartButton));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", addItemToCartButton);
        wait.until(ExpectedConditions.visibilityOf(modalWindow));
        Assert.assertTrue(modalWindow.isDisplayed());

        wait.until(ExpectedConditions.visibilityOf(goToCartButton));
        goToCartButton.click();
    }
}