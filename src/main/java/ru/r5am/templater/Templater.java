package ru.r5am.templater;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.hubspot.jinjava.Jinjava;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class Templater {

    /**
     * Возвращает HTML страницу на основе темплейта и словаря с данными
     * @param templateName Имя файла темплейта
     * @param data Словать с данными
     * @return Сгенерированная HTML страница
     */
    public String getPage(String templateName, Map<String, Object> data) throws IOException {

        String templatesDir = "templates";
        Jinjava jinjava = new Jinjava();
        Map<String, Object> context = Maps.newHashMap();
        context.put("data", data);


        URL url = getClass().getResource(File.separator + templatesDir + File.separator + templateName);
        String template = IOUtils.toString(url, Charsets.UTF_8);

        return jinjava.render(template, context);
    }


}
