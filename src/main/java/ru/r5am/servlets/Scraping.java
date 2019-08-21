package ru.r5am.servlets;


import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;
import com.google.common.collect.Maps;

import ru.r5am.DirectScraping;
import ru.r5am.SelenideSetUp;
import ru.r5am.templater.Templater;


public class Scraping extends HttpServlet {

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {


        // Сконфигурировать Selenide
        SelenideSetUp selenideSetUp = new SelenideSetUp();
        selenideSetUp.selenideStart();

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
        Map<String, Object> scrapResult = Maps.newHashMap();
        DirectScraping directScraping = new DirectScraping();
        directScraping.scraping(forScrapingData, scrapResult);


        // Вывод результата
        response.setContentType("text/html");
        Templater templater = new Templater();
        try {
            PrintWriter printWriter = response.getWriter();
            forScrapingData.put("Error", "Ошибки нет");
            printWriter.println(templater.getPage("scraping-result.ftl", forScrapingData));
            printWriter.close();
        } catch (IOException ex) {
            forScrapingData.put("Error", ex.toString());
        }

    }

}