package ru.r5am;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.r5am.pageobjects.FindPage;
import ru.r5am.pageobjects.ResultPage;

import java.util.ArrayList;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class DirectScraping {

    private static final Logger log = LogManager.getLogger();
    private FindPage findPage = new FindPage();
    private ResultPage resultPage = new ResultPage();

    /**
     * Непосредственно скрапинг
     * @param forScrapingData Параметры для скрапинга, введённые через форму в index.html
     * @param scrapResult Результаты скрапинга
     */
    public void scraping(Map<String, String> forScrapingData, Map<String, Object> scrapResult) {


        // Открыть сайт
        open("https://www.avito.ru/moskva/kommercheskaya_nedvizhimost");

        // Выставить фильтры поиска
        setSearchFilters(forScrapingData);

        // Искать
        searching();

        // Собрать результаты со всех страниц
        allObjectsResultsCollect(scrapResult);

        // Закрыть браузер
        close();
    }

    /**
     * Собрать результаты по объектам на всех страницах
     * @param scrapResult Результат скрапинга
     */
    private void allObjectsResultsCollect(Map<String, Object> scrapResult) {

        // Собрать иформацию об объектах на текущей странице
        ArrayList<Integer> prices = new ArrayList<>();
        getOnePageObjectsPrice(prices);
        log.info("Цены: {}", prices);

        // Добавить к основной коллекци


        // Есть ли следующая страница





    }

    /**
     * Собрать цены на одной странице
     * @param prices Цена
     */
    private void getOnePageObjectsPrice(ArrayList<Integer> prices) {

        // Количество объектов только на данной странице
        int onPageObjectsQuantity = resultPage.getObjectsOnPage();
        log.debug("Objects quantity on page: {}", onPageObjectsQuantity);

        // Выставить цены в рублях в месяц за квадратный метр
        resultPage.rentTypeDisplaySet("в месяц за м");

        // Цены
        resultPage.getPrices(prices);

    }

    /**
     * Поиск объектов
     */
    private void searching() {
        // Нажать кнопку поиска объектов
        findPage.searchButtonPress();
    }

    /**
     * Выставить фильтры поиска
     * @param forScrapingData Параметры для скрапинга
     */
    private void setSearchFilters(Map<String, String> forScrapingData) {

        // Выбрать станции метро
        if (!"".equals(forScrapingData.get("metro"))) {
            findPage.metroSet(forScrapingData.get("metro"));
        }

        // Выбрать аренду
        findPage.rentSet();

        // Выставить чекбокс с Видом объекта
        findPage.typeObjectCheckboxSet(forScrapingData.get("objectType"));

        // Ввести минимальную площадь
        findPage.minAreaInput(forScrapingData.get("minArea"));

        // Ввести максимальную площадь
        findPage.maxAreaInput(forScrapingData.get("maxArea"));

    }

}
