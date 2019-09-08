package ru.r5am.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GetCompletedWork extends HttpServlet {

    private static final Logger log = LogManager.getLogger();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.debug("Start method: {}", Thread.currentThread().getStackTrace()[1].getMethodName());

        // Общее количество найденных объектов

        // Количество уже обработанных объектов



        // TODO: Так и не придумал как выдавать из одного сервлета инфу через другой...
        // TODO: Будем выдавать "пургу" случайную  чтобы клиент не ушёл :-)
        int min = 10;
        int max = 90;
        String completeWorkPercent = Integer.toString(rnd(min, max));

        log.debug("completeWorkPercent: {}", completeWorkPercent);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.write(completeWorkPercent);
        }
    }

    /**
     * Вернуть случайное целое из диапазона
     * @param min Минимальное значение диапазона
     * @param max Максимальное значение диапазона
     * @return Случайное целое
     */
    private int rnd(int min, int max)
    {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
