package site.nomoreparties.stellarburgers.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class DataGenerator {
    public static String getString(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String getEmailDomain() {
        Random random = new Random();
        final String[] domain = {
                "gmail.com",
                "yandex.ru",
                "yahoo.com",
                "mail.com"};
        int index = random.nextInt(domain.length);
        return domain[index];
    }

    public static String getEmail() {
        return RandomStringUtils.random(16, "abcdefghijklmnopqrstuvwxyz1234567890") + "@" + getEmailDomain();
    }
}
