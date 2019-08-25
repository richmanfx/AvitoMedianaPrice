package ru.r5am.servlets;


import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;
import com.google.common.collect.Maps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.r5am.DirectScraping;
import ru.r5am.SelenideSetUp;
import ru.r5am.templater.Templater;


public class Scraping extends HttpServlet {

    private static final Logger log = LogManager.getLogger();


    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {


        // Сконфигурировать Selenide
        SelenideSetUp.selenideStart();

        // Параметры из формы
        String objectType = request.getParameter("object_type");
        String minArea = request.getParameter("min_area");
        String maxArea = request.getParameter("max_area");
        String metro = request.getParameter("metro");

        Map<String, String> forScrapingData = Maps.newHashMap();
        forScrapingData.put("objectType", objectType);
        forScrapingData.put("minArea", minArea);
        forScrapingData.put("maxArea", maxArea);
        forScrapingData.put("metro", metro);

        // Скрапинг
        ArrayList<Integer> scrapResult = new ArrayList<>();
        DirectScraping directScraping = new DirectScraping();
        directScraping.scraping(forScrapingData, scrapResult);

        // Максимальную и минимальную цены удалить
        directScraping.minMaxRemove(scrapResult);

        // Рассчитать медианную цену
        Integer medianPrice = getMedianPrice(scrapResult);
        forScrapingData.put("Медиана цены", medianPrice.toString());

        // Вывод результата
        response.setContentType("text/html");
        Templater templater = new Templater();

        Map<String, String> resultData = Maps.newHashMap();
        resultData.put("medianPrice", medianPrice.toString());
        resultData.putAll(forScrapingData);

        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.println(templater.getPage("scraping-result.ftl", resultData));
            printWriter.close();
        } catch (IOException ex) {
            log.error("IO Error: {0}", ex);
        }

    }

    /**
     * Расчёт медианной цены
     */
    Integer getMedianPrice(List<Integer> prices) {

        Integer mediana;

        // Сортировать
        prices.sort(Integer::compareTo);
        log.info("Отсортированные цены {}", prices);

        int elementsQuantity = prices.size();
        log.info("Количество элементов с ценами {}", elementsQuantity);

        if (0 != (elementsQuantity % 2)){      // Для нечётного количества
            mediana = prices.get(((elementsQuantity - 1) / 2));
        } else {
            mediana = (prices.get((elementsQuantity / 2) - 1) + prices.get(elementsQuantity / 2)) / 2;
        }

        log.info("Медианное значение цены {}", mediana);
        return mediana;
    }

}