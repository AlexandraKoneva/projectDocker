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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    //protected WebDriver driver; //todo для локального запуска раскомментировать
    protected MainPage mainPage;
    protected DigmaPage digmaPage;
    protected CartPage cartPage;
    protected SearchPage searchPage;

    private RemoteWebDriver driver; //todo для удаленного запуска раскомментировать

    @Before
    public void setUp() {
        // todo ОТСЮДА для удаленного запуска
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserName", "chrome");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        try {
            driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), options);
        } catch (MalformedURLException e) {
        }
        // todo ДОСЮДА для удаленного запуска

        //WebDriverManager.chromedriver().setup(); //todo для локального запуска раскомментировать
        //driver = new ChromeDriver(); //todo для локального запуска раскомментировать


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