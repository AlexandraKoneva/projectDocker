package tests;

import org.junit.Test;

public class LoginTest extends BaseTest {

    @Test
    public void login() {
        mainPage.logIn("qwerty123@gmail.com", "1234");
        mainPage.checkLogIn();
    }
}