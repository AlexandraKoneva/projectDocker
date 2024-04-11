package org.example.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='cart-checkout-item__title']")
    private WebElement itemTitleInfo;

    @FindBy(xpath = "//*[@class='fw-bold']")
    private WebElement sumInfo;

    @FindBy(xpath = "//div[@class='cart-checkout-item__info']//*[contains(@class,'remove')]")
    private WebElement deleteButton;

    public void checkInfoAboutItem(String itemTitle, String sum) {
        Assert.assertEquals("В корзине расположен другой товар", itemTitleInfo.getText(), itemTitle);
        Assert.assertEquals("У товара указана другая сумма", sumInfo.getText(), sum);
    }

    public void deleteItem(){
        deleteButton.click();
    }
}