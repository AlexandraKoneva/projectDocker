package tests;

import org.junit.Test;

public class AddingItemToCartTest extends BaseTest {

    @Test
    public void addingItemToCart() {
        mainPage.goToCatalog("Электроника","Планшеты","Digma");
        digmaPage.pageIsOpened("Digma");
        digmaPage.addItemToCart();
        cartPage.checkInfoAboutItem("Планшет Digma iDx10 8Gb", "9 680 р.");
        cartPage.deleteItem();
        mainPage.checkMainPageIsDisplayed();
    }
}