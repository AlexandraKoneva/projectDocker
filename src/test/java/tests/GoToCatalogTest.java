package tests;

import org.junit.Test;

public class GoToCatalogTest extends BaseTest {

    @Test
    public void goToCatalog() {
        mainPage.goToCatalog("Электроника","Планшеты","Digma");
        digmaPage.pageIsOpened("Digma");
    }
}