package tests;

import org.junit.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchItem() {
        mainPage.searchItem("Digma");
        searchPage.checkFoundElement("Результаты поиска","Digma");
        System.out.println("Тест searchItem успешно выполнен");
    }
}