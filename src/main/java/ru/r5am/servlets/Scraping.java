package ru.r5am.servlets;

import com.google.common.collect.Maps;
import ru.r5am.templater.Templater;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Scraping extends HttpServlet {

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {

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
        PrintWriter printWriter = response.getWriter();
        printWriter.println(templater.getPage("template1.ftl", data));

//        printWriter.printf("Тип объекта: %s<br>", objectType);
//        printWriter.printf("Минимальная площадь: %s<br>", minArea);
//        printWriter.printf("Максимальная площадь: %s<br>", maxArea);

        printWriter.close();
    }

}