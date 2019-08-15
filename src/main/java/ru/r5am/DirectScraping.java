package ru.r5am;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class DirectScraping {

    /**
     * Непосредственно скрапинг
     * @param forScrapingData Параметры для скрапинга, введённые через форму в index.html
     * @param scrapResult Результаты скрапинга
     */
    public void scraping(Map<String, Object> forScrapingData, Map<String, Object> scrapResult) {


        // Открыть сайт
        open("https://www.avito.ru/moskva");

        // Выставить фильтры поиска




        // Искать
        sleep(5 * 1000L);

        // Закрыть бразер
        close();
    }

}
