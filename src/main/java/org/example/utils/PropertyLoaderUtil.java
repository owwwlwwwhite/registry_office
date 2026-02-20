package org.example.utils;

import lombok.extern.log4j.Log4j2;
import org.example.App;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class PropertyLoaderUtil {
    public static Properties loadProperty(String propertyName) {

        InputStream inputStream = App.class
                .getClassLoader()
                .getResourceAsStream(propertyName);

        if (inputStream == null) {
            log.fatal("Файл " + propertyName + " не найден в src/test/resources/");
            throw new RuntimeException("Файл " + propertyName + " не найден в src/test/resources/");
        }

        try {
            Properties prop = new Properties();
            prop.load(inputStream);
            inputStream.close();
            return prop;
        } catch (IOException e) {
            log.fatal("io error");
            return null;
        }
    }
}
