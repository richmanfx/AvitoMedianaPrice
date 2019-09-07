package ru.r5am;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.r5am.pageobjects.FindPage;
import ru.r5am.pageobjects.ResultPage;

import java.util.ArrayList;
import java.util.List;
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
    public void scraping(Map<String, String> forScrapingData, List<Integer> scrapResult) {


        // Открыть сайт
        open("https://www.avito.ru/moskva/kommercheskaya_nedvizhimost");

        // Выставить фильтры поиска
        setSearchFilters(forScrapingData);

        // Искать
        searching();

        // Выставить цены в рублях в месяц за квадратный метр
        resultPage.rentTypeDisplaySet("в месяц за м");

        // Собрать результаты со всех страниц
        allObjectsResultsCollect(scrapResult);
        log.info("Цены всех страниц: {}", scrapResult);

        // Закрыть браузер
        close();
    }

    /**
     * Собрать результаты по объектам на всех страницах
     * @param scrapResult Результат скрапинга
     */
    private void allObjectsResultsCollect(List<Integer> scrapResult) {

        // Собрать иформацию об объектах на текущей странице
        ArrayList<Integer> prices = new ArrayList<>();
        getOnePageObjectsPrice(prices);
        log.debug("Цены одной страницы: {}", prices);

        // Добавить к основной коллекци
        scrapResult.addAll(prices);

        // *******************************************************************************
        // Для отладки: скрапить только первую страницу - закомментировать дальше
        // *******************************************************************************

        log.debug("Есть следующая страница? {}", resultPage.existNextPage());

        if(resultPage.existNextPage()) {    // Условие выхода из рекурсии - нет следующей страницы

            // Перейти на следующую страницу
            resultPage.goToNextPage();

            // Рекурсия
            allObjectsResultsCollect(scrapResult);
        }

        // *******************************************************************************
        // Для отладки: скрапить только первую страницу - закомментировать до этого места
        // *******************************************************************************

    }

    /**
     * Собрать цены на одной странице
     * @param prices Цена
     */
    private void getOnePageObjectsPrice(ArrayList<Integer> prices) {

        // Количество объектов только на данной странице
        int onPageObjectsQuantity = resultPage.getObjectsOnPage();
        log.debug("Objects quantity on page: {}", onPageObjectsQuantity);

//        // Выставить цены в рублях в месяц за квадратный метр
//        resultPage.rentTypeDisplaySet("в месяц за м");

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
