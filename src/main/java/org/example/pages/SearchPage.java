package org.example.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[@class='mb-md-5 mb-4']")
    private WebElement header;

    @FindBy(xpath = "//*[@class='item-card__title rs-to-product']")
    private List<WebElement> productName;

    public void checkFoundElement(String headerPage, String item) {
        Assert.assertEquals("Заголовок страницы другой", header.getText(), headerPage);
        for (int i = 0; i < productName.size(); i++) {
            Assert.assertTrue("Результаты поиска некорректны", productName.get(i).getText().contains(item));
        }
    }
}