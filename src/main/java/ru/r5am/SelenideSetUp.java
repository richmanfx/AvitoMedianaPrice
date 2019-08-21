package ru.r5am;

import com.codeborne.selenide.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SelenideSetUp {

    private static final Logger log = LogManager.getLogger();

    public void selenideStart() {
        log.debug("===> Start method: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Configuration.pageLoadStrategy = "normal";
        Configuration.timeout = 1000 * 7;           // из секунд в мс
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.screenshots = false;      // Делать ли скриншот средсвами Selenide
    }

}
