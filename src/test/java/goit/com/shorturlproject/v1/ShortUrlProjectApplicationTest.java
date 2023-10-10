package goit.com.shorturlproject.v1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("prod")
@PropertySource("classpath:application-prod.properties")
class ShortUrlProjectApplicationTest {
    @Test
    void mainTest() {
        ShortUrlProjectApplication.main(new String[]{});
    }
}