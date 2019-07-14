package ru.r5am.servlets;


import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;
import com.google.common.collect.Maps;

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

        Map<String, Object> data = Maps.newHashMap();
        data.put("objectType", objectType);
        data.put("minArea", minArea);
        data.put("maxArea", maxArea);

        response.setContentType("text/html");
        Templater templater = new Templater();
        try {
            PrintWriter printWriter = response.getWriter();
            data.put("Error", "Ошибки нет");
            printWriter.println(templater.getPage("template1.ftl", data));
            printWriter.close();
        } catch (IOException ex) {
            data.put("Error", ex);
        }

    }

}