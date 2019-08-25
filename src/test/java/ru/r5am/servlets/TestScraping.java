package ru.r5am.servlets;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TestScraping {

    private Scraping scrap = new Scraping();

    @Test
    public void testGetMedianPrice() {

        // Тестовые данные
        ArrayList<Integer> testData = new ArrayList<>();
        testData.add(10);
        testData.add(20);
        testData.add(30);
        testData.add(40);
        testData.add(50);
        testData.add(60);
        testData.add(70);
        testData.add(80);
        testData.add(90);

        // Ожидаемый резульат
        Integer expectedPrice = 50;

        // Фактический результат
        Integer actualPrice = scrap.getMedianPrice(testData);

        // Проверка
        Assert.assertEquals(actualPrice, expectedPrice);

    }

}

