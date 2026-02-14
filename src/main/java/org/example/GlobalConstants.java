package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.example.utils.RestAssuredCustomLogger;

import java.net.URI;

public class GlobalConstants {
    public static final String SURNAME = "Фамилия";
    public static final String NAME = "Имя";
    public static final String PATRONYMIC = "Отчество";
    public static final String PHONE_NUMBER = "Телефон";
    public static final String PASSPORT_NUMBER = "Номер паспорта";
    public static final String BIRTH_DATE = "Дата рождения";
    public static final String PROCEED_BUTTON = "Далее";
    public static final String REGISTRATION_ADDRESS = "Адрес прописки";

    public static final String DEATH_REGISTRATION_BTN = "Регистрация смерти";
    public static final String MARRIAGE_REGISTRATION_BTN = "Регистрация брака";
    public static final String BIRTH_REGISTRATION_BTN = "Регистрация рождения";

    public static final String SEX = "Пол";

    public static final String ENTER_AS_USER_BUTTON = "Войти как пользователь";
    public static final String ENTER_AS_ADMIN_BUTTON = "Войти как администратор";

    public static final String CREATE_NEW_APPLICATION_BUTTON = "Создать новую заявку";


    public static final RequestSpecification BASE_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri(URI.create("https://regoffice.senla.eu/"))
            .setAuth(RestAssured.basic("user", "senlatest"))
            .setContentType("application/json")
            .addHeader("Accept", "*/*")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Connection", "keep-alive")
            .addFilter(new RestAssuredCustomLogger())
            .build();
    public static final String SEND_USER_REQUEST_ENDPOINT = "/sendUserRequest";
    public static final String GET_APPLICATION_BY_STATUS_ENDPOINT = "/getApplStatus/";
    public static final String GET_APPLICATIONS_ENDPOINT = "/getApplStatus";
    public static final String SEND_ADMIN_REQUEST_ENDPOINT = "/sendAdminRequest";
    public static final String REQUEST_PROCESS_ENDPOINT = "/requestProcess";
}
