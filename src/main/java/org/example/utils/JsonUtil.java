package org.example.utils;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class JsonUtil {
    @Step("Достать значение из поля {field} из запроса {json}")
    public static String extractValueBodyRequest(String field, String json) {
        String regex = "\"" + field + "\"\\s*:\\s*\"?([^\"\\n,}]*)\"?";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            log.info("Извлечено значение из поля {}={} из запроса {}", field, matcher.group(1).trim(), json);
            return matcher.group(1).trim();
        }
        return null;
    }
}
