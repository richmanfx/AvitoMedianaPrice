package ru.r5am.servlets;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Scraping extends HttpServlet {

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Параметры из формы
        String objectType = request.getParameter("object_type");
        String minArea = request.getParameter("min_area");
        String maxArea = request.getParameter("max_area");


        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.printf("Тип объекта: %s<br>", objectType);
        printWriter.printf("Минимальная площадь: %s<br>", minArea);
        printWriter.printf("Максимальная площадь: %s<br>", maxArea);
        printWriter.close();
    }

}