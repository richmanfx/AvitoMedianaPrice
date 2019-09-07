package ru.r5am.pageobjects;

import org.openqa.selenium.By;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

/**
 * Страница ввода данных для поиска объектов
 */
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
        String checkboxXpath = String.format("//form//span[contains(text(),'%s')]", checkboxLabel);
        $(By.xpath(checkboxXpath)).scrollTo().click();
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

        String inputXpath = String.format(
                "//span[text()='Площадь, м²']/../../..//input[contains(@class,'input-input') and @placeholder='%s']",
                placeholder);

        $(By.xpath(inputXpath)).scrollTo().setValue(area);
    }

    /**
     * Выбрать требуемые станции метро
     * @param metro Название станции метро
     */
    public void metroSet(String metro) {

        // Нажать ссылку для выбора станций метро
        $(By.xpath("//div[@data-current-tab='metro']")).click();

        // Перебрать все введённые станции метро
        String[] metroList = metro.split(",");
        for (String station: metroList) {
            SelenideElement element = $(By.xpath(String.format("//*[@class='label' and text()='%s']", station.trim())));
            element.getCoordinates().inViewPort();        // Скролить до элемента
            element.click();
            sleep(1000);
        }

        // Кнопка
        $(By.xpath("//button[contains(@data-marker,'popup-location/save-button')]")).click();
    }

}
