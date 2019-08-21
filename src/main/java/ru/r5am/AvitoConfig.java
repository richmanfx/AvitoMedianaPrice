package ru.r5am;

import org.aeonbits.owner.Config;

@Config.Sources({ "file:app.config" })
public interface AvitoConfig extends Config {

    // Использование удалённых браузеров, таких как "Selenium Grid" или "Solenoid"
    @DefaultValue("false")
    Boolean remoteBrowserFlag();
    @DefaultValue("127.0.0.1")
    String remoteSeleniumHub();
    @DefaultValue("4444")
    String remoteSeleniumHubPort();

    // Размер окна браузера
    @DefaultValue("1920x1080")
    String browserSize();

}
