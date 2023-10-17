package goit.com.shorturlproject.v1.user.dto;

import goit.com.shorturlproject.v1.ITestContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest

public class UserEntityTest implements ITestContainer {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testUserEntity() {
        User user = new User("John", "Doe", "john@example.com",
                "john_doe", "T1234567");

        User savedUser = entityManager.persistAndFlush(user);

        User retrievedUser = entityManager.find(User.class, savedUser.getId());

        assertEquals(savedUser, retrievedUser);
    }
}
