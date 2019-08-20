package ru.r5am;

import ru.r5am.pageobjects.FindPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class DirectScraping {

    private FindPage findPage = new FindPage();

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

        // Закрыть браузер
        close();
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
