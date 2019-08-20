package ru.r5am.pageobjects;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class FindPage extends BasePage {


    /**
     * Выбрать закладку "Снять"
     */
    public void rentSet() {
        $(By.xpath("//form//span[contains(text(),'Снять')]")).click();
    }

    /**
     * Выставить чекбокс для типа объекта
     * @param checkboxLabel Лэйбл чекбокса
     */
    public void typeObjectCheckboxSet(String checkboxLabel) {
        $(By.xpath(String.format("//form//span[contains(text(),'%s')]", checkboxLabel))).click();
    }

    /**
     * Ввести минимальную площадь
     * @param area Значение площади
     */
    public void minAreaInput(String area) {
        areaInput("от", area);
    }

    /**
     * Ввести максимальную площадь
     * @param area Значение площади
     */
    public void maxAreaInput(String area) {
        areaInput("до", area);
    }

    /**
     * Ввести площадь объекта
     * @param placeholder Значение атрибута "placeholder" тега "input"
     * @param area Значение площади
     */
    private void areaInput(String placeholder, String area) {
        $(By.xpath(String.format(
                "//span[text()='Площадь, м²']/../../..//input[contains(@class,'input-input') and @placeholder='%s']",
                placeholder)
        )).setValue(area);
    }

    /**
     * Нажать кнопку поиска
     */
    public void searchButtonPress() {
        $(By.xpath("//button[contains(@data-marker,'search-filters')]")).click();
    }

}
