package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.CartPage;
import org.example.pages.DigmaPage;
import org.example.pages.MainPage;
import org.example.pages.SearchPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected DigmaPage digmaPage;
    protected CartPage cartPage;
    protected SearchPage searchPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //страницы
        this.mainPage = new MainPage(driver);
        this.digmaPage = new DigmaPage(driver);
        this.cartPage = new CartPage(driver);
        this.searchPage = new SearchPage(driver);

        driver.manage().window().maximize();

        driver.get("https://mega.readyscript.ru/");

        mainPage.hidePrivacyPolicy();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}