package goit.com.shorturlproject.v1.swagger;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenAPITest {

    private static final String SWAGGER_SPEC_URL = "http://localhost:8080/api-docs";

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = SWAGGER_SPEC_URL;
    }

    @Test
    void testSwaggerSpecification() {
        // отримуємо специфікацію
        RequestSpecification request = given();

        Response response = request.get();
        //перевірка чи валідна url-специфікація
        assertEquals(200, response.getStatusCode(), "Помилка при отриманні Swagger-специфікації");

        // перевірка чи містить специфікація необхідні шляхи
        String responseBody = response.getBody().asString();
        assertEquals(true, responseBody.contains("v1/{shortUrl}"));
        assertEquals(true, responseBody.contains("v1/auth/user/createShortUrl"));
        assertEquals(true, responseBody.contains("v1/auth/user/{id}/allActiveLinks"));
        assertEquals(true, responseBody.contains("v1/auth/user/{id}/allLinks"));
        assertEquals(true, responseBody.contains("v1/auth/user/deleteLink/{id}"));

    }
}
