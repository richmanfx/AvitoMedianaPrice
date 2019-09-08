package ru.r5am.pageobjects;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница результатов поиска объектов
 */
public class ResultPage extends BasePage {

    private static final Logger log = LogManager.getLogger();
    private String nextPageXpath = "//div[contains(@class,'pagination-nav')]/a[contains(text(),'Следующая страница')]";

    /**
     * Выбрать отображение арендной платы в "₽ в месяц за м²"
     * @param priceType Тип отображения цены
     */
    public void rentTypeDisplaySet(String priceType) {

        String selector = "//span[contains(text(),'Арендная плата')]/../../following-sibling::div//select";
        $(By.xpath(selector)).scrollTo();
        $(By.xpath(selector)).click();

        String fullXpath = selector + String.format("/option[contains(text(),'%s')]", priceType);
        $(By.xpath(fullXpath)).click();

        // Кнопку нажать
        searchButtonPress();

    }

    /**
     * Вернуть количество объектов на странице
     * @return количество объектов
     */
    public int getObjectsOnPage() {
        String objectXpath = "//div[contains(@class,'item item_table')]";
        return $$(By.xpath(objectXpath)).size();
    }

    /**
     * Количество найденных объектов
     */
    public Integer getAllObjectsQuantity() {
        String counterXpath = "//span[@data-marker='page-title/count']";
        return Integer.valueOf($(By.xpath(counterXpath)).text().trim().replace(" ", ""));
    }

    /**
     * Получить цены объектов на странице
     * @param prices Список цен
     */
    public void getPrices(List<Integer> prices) {
        String priceXpath = "//div[contains(@class,'item item_table')]//span[contains(@class,'price')]";
        ElementsCollection pricesElements = $$(By.xpath(priceXpath));

        for (SelenideElement element: pricesElements) {

            String priceContent = element.getAttribute("content");

            if (null != priceContent) {     // Рекламные вставки без цены встречаются
                Integer price = Integer.parseInt(priceContent);
                prices.add(price);
            }

        }
    }

    /**
     * Проверить существует ли следующая страница
     * @return true - страница существует, иначе - false
     */
    public boolean existNextPage() {
        boolean result = false;
        if($(By.xpath(nextPageXpath)).exists()) {
            result = true;
        }
        return result;
    }

    /**
     * Перейти на следующую страницу
     */
    public void goToNextPage() {
        $(By.xpath(nextPageXpath)).click();
    }

}
