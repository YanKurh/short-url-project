package goit.com.shorturlproject.dtoTest;

import goit.com.shorturlproject.url.UrlLinkDTO;
import goit.com.shorturlproject.url.UrlLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class UrlLinkDTOTest {

    @Test
    void testFromUrlLinkDTO() {
        // Створюємо UrlLinkDTO
        UrlLinkDTO urlLinkDTO = new UrlLinkDTO();
        urlLinkDTO.setLongUrl("https://example.com");
        urlLinkDTO.setCreatedAt(LocalDateTime.now());
        urlLinkDTO.setClickTimes(6);

        // Викликаємо метод для перетворення
        UrlLink urlLink = UrlLinkDTO.fromUrlLinkDTO(urlLinkDTO);

        // Перевіряємо, чи дані були перетворені коректно
        Assertions.assertEquals(0, urlLink.getId());
        Assertions.assertEquals("https://example.com", urlLink.getLongUrl());
        Assertions.assertTrue(urlLinkDTO.getClickTimes() >= 0);
    }

    @Test
    void testClickTimesNotNegative() {
        // Створюємо UrlLinkDTO
        UrlLinkDTO urlLinkDTO = new UrlLinkDTO();

        // Встановлюємо від'ємне значення для clickTimes
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> urlLinkDTO.setClickTimes(-1)
        );

        // Перевіряємо, чи виникла очікувана помилка
        String expectedMessage = "clickTimes не може бути від'ємним";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

        @Test
        void testFromUrlLink() {
            // Створюємо об'єкт UrlLink
            UrlLink urlLink = new UrlLink();
            urlLink.setLongUrl("https://example.com");
            urlLink.setCreatedAt(LocalDateTime.now());
            urlLink.setClickTimes(4);

            // Викликаємо метод для перетворення
            UrlLinkDTO urlLinkDTO = UrlLinkDTO.fromUrlLink(urlLink);

            // Перевіряємо, чи дані були перетворені коректно
            Assertions.assertNotNull(urlLinkDTO);
            Assertions.assertEquals("https://example.com", urlLinkDTO.getLongUrl());
            Assertions.assertTrue(urlLinkDTO.getClickTimes() >= 0);
        }

        @Test
        void testUrlIsValid(){
            // Створюємо UrlLinkDTO
            UrlLinkDTO urlLinkDTO = new UrlLinkDTO();

            // Встановлюємо невалідне значення URL
            IllegalArgumentException exception = Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> urlLinkDTO.setLongUrl("this_is_not_a_valid_url")
            );

            // Перевіряємо, чи виникла очікувана помилка
            String expectedMessage = "This URL-link does not exist";
            String actualMessage = exception.getMessage();
            Assertions.assertTrue(actualMessage.contains(expectedMessage));
        }
}
