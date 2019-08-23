package ru.r5am.pageobjects;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    /**
     * Нажать кнопку поиска
     */
    public void searchButtonPress() {
        $(By.xpath("//button[contains(@data-marker,'search-filters')]")).click();
    }

}
