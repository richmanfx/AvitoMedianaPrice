package ru.r5am;

import com.codeborne.selenide.Configuration;

public class SelenideSetUp {

    public void selenideStart() {
        Configuration.pageLoadStrategy = "normal";
        Configuration.timeout = 1000 * 7;           // из секунд в мс
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

}
