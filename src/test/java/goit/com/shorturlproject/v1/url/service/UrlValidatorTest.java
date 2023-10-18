package goit.com.shorturlproject.v1.url.service;

import goit.com.shorturlproject.v1.ITestContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UrlValidatorTest implements ITestContainer {

    private UrlValidator urlValidator;

    @BeforeEach
    public void setUp() {
        urlValidator = new UrlValidator();
    }

    @Test
    void testValidURL() {
        String validURL = "https://www.example.com";
        assertTrue(urlValidator.isValidURL(validURL));
    }

    @Test
    void testInvalidURL() {
        String invalidURL = "not_a_valid_url";
        assertFalse(urlValidator.isValidURL(invalidURL));
    }

    @Test
    void testNullURL() {
        assertFalse(urlValidator.isValidURL(null));
    }

    @Test
    void testEmptyURL() {
        String emptyURL = "";
        assertFalse(urlValidator.isValidURL(emptyURL));
    }
}
