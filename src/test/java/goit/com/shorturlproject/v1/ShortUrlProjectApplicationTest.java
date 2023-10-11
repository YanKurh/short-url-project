package goit.com.shorturlproject.v1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("prod")
class ShortUrlProjectApplicationTest implements ITestContainer {
    @Test
    void mainTest() {
        ShortUrlProjectApplication.main(new String[]{});
    }
}