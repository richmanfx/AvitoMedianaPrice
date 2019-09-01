package ru.r5am.servlets;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestScraping {

    private Scraping scrap = new Scraping();

    @DataProvider
    public static Object[][] testPriceData() {

        ArrayList<Integer> unevenTestData = new ArrayList<>();
        unevenTestData.add(80);
        unevenTestData.add(10);
        unevenTestData.add(60);
        unevenTestData.add(90);
        unevenTestData.add(20);
        unevenTestData.add(50);
        unevenTestData.add(40);
        unevenTestData.add(30);
        unevenTestData.add(70);

        ArrayList<Integer> evenTestData = new ArrayList<>();
        evenTestData.add(10);
        evenTestData.add(80);
        evenTestData.add(50);
        evenTestData.add(20);
        evenTestData.add(70);
        evenTestData.add(30);
        evenTestData.add(60);
        evenTestData.add(40);

        return new Object[][]{
                {unevenTestData, 50},
                {evenTestData, 45}
        };
    }

    @Test(dataProvider = "testPriceData")
    public void testGetMedianPrice(List<Integer> testData, Integer expectedPrice) {

        // Фактический результат
        Map<String, String> resultData = Maps.newHashMap();
        scrap.getMedianPrice(testData, resultData);

        // Проверка
        Assert.assertEquals(Integer.valueOf(resultData.get("medianPrice")), expectedPrice);

    }

}

