package tests;

import org.example.pages.CartPage;
import org.example.pages.DigmaPage;
import org.example.pages.MainPage;
import org.example.pages.SearchPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

//    protected WebDriver driver;
    protected MainPage mainPage;
    protected DigmaPage digmaPage;
    protected CartPage cartPage;
    protected SearchPage searchPage;

    private RemoteWebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserName", "chrome");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        try {
            driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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