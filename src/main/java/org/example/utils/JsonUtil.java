package org.example.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Step("Достать значение из поля {field} из запроса {json}")
    public static String extractValueBodyRequest(String field, String json) {
        try {
            JsonNode rootNode = mapper.readTree(json);
            JsonNode valueNode = rootNode.get(field);

            if (valueNode != null && !valueNode.isNull()) {
                String value = valueNode.asText();
                log.info("Извлечено значение из поля {}={} из запроса {}", field, value, json);
                return value;
            }
        } catch (Exception e) {
            log.error("Ошибка парсинга JSON для поля {}: {}", field, e.getMessage());
        }
        return null;
    }
}
