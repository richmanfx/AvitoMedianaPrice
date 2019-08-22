package ru.r5am.pageobjects;


import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница результатов поиска объектов
 */
public class ResultPage extends BasePage {


    /**
     * Выбрать отображение арендной платы в "₽ в месяц за м²"
     */
    public void rentTypeDisplaySet() {
        $(By.xpath("//form//span[contains(text(),'Снять')]")).click();
    }

    /**
     * Вернуть количество объектов на странице
     * @return количество объектов
     */
    public int getObjectsOnPage() {
        String objectXpath = "//div[contains(@class,'item item_table')]";
        return $$(By.xpath(objectXpath)).size();
    }
}
