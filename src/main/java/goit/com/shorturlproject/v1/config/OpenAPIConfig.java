package goit.com.shorturlproject.v1.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("URL Shortener API")
                        .version("v1")
                        .description("This is the URL Shortener project implemented with Spring Boot and Java 17. " + "\n" +
                                "This project is aimed at providing a URL shortening service, which allows users to convert long URLs into shorter, more user-friendly links."));
    }
}
