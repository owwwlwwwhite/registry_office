package org.example.utils;

import lombok.Getter;

public class DataAPIFactoryUtil {
    @Getter
    public static final String sendUserRequestWedding = """
            {
                "mode": "wedding",
                "personalLastName": "Валерьевич",
                "personalFirstName": "Иван",
                "personalMiddleName": "Сахарицкий",
                "personalPhoneNumber": "37529235957",
                "personalNumberOfPassport": "АВ123456",
                "personalAddress": "г. Москва, ул. Тверская, д. 10, корп. 2, кв. 15",
                "citizenLastName": "Андреевич",
                "citizenFirstName": "Валерий",
                "citizenMiddleName": "Сахарицкий",
                "citizenBirthDate": "2000-12-04",
                "citizenNumberOfPassport": "АВ654321",
                "citizenGender": "Мужской",
                "citizenAddress": "г. Москва, ул. Тверская, д. 10, корп. 2, кв. 1000",
                "dateOfMarriage": "2025-12-04",
                "newLastName": "Сахарицкая",
                "anotherPersonLastName": "Леонтьевна",
                "anotherPersonFirstName": "Марина",
                "anotherPersonMiddleName": "Кудесникова",
                "birth_of_anotoherPerson": "1999-12-04",
                "anotherPersonPassport": "АВ111111",
                "birth_place": "something",
                "birth_mother": "something",
                "birth_father": "something",
                "birth_grandpa": "something",
                "birth_grandma": "something",
                "death_dateOfDeath": "something",
                "death_placeOfDeath": "something"
            }
        """;

    @Getter
    private static final String sendAdminRequest = """
            {
                "personalLastName": "Вакубич",
                "personalFirstName": "Виктор",
                "personalMiddleName": "Витальевич",
                "personalPhoneNumber": "37529204448",
                "personalNumberOfPassport": "АВ123456",
                "dateofbirth": "2025-10-29"
            }
            """;

    @Getter
    public static final String changeApplStatus = """
            {
                "applId": 57093,
                "staffid": 32097,
                "action": "approved"
            }
            """;
}
