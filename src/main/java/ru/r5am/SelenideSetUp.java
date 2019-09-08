package ru.r5am;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SelenideSetUp {

    private static final Logger log = LogManager.getLogger();
    private static AvitoConfig avitoConfig = ConfigFactory.create(AvitoConfig.class);

    public static void selenideStart() {
        log.debug("Start method: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        Configuration.pageLoadStrategy = "normal";
        Configuration.timeout = 1000 * 7;           // из секунд в мс
        Configuration.browser = "chrome";
        Configuration.browserSize = avitoConfig.browserSize();
        Configuration.screenshots = false;      // Делать ли скриншот средсвами Selenide

        log.info("remoteBrowserFlag: {}", avitoConfig.remoteBrowserFlag());

        // Для работы через Selenium Grid
        if (avitoConfig.remoteBrowserFlag()) {

            String remoteSeleniumHub = avitoConfig.remoteSeleniumHub();
            log.debug("Remote Selenium Hub: {}", avitoConfig.remoteSeleniumHub());

            String remoteSeleniumHubPort = avitoConfig.remoteSeleniumHubPort();
            log.debug("Remote port of Selenium Hub: {}", remoteSeleniumHubPort);

            Configuration.remote = String.format("http://%s:%s/wd/hub", remoteSeleniumHub, remoteSeleniumHubPort);
            Configuration.browserCapabilities.setCapability("enableVNC", true);
        }

    }

}
