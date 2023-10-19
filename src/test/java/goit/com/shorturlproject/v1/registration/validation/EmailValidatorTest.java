package goit.com.shorturlproject.v1.registration.validation;
import goit.com.shorturlproject.v1.registration.validation.impl.EmailValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class EmailValidatorTest {

    private EmailValidator validator;
    private ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach
    void setUp() {
        validator = new EmailValidator();
        constraintValidatorContext = mock(ConstraintValidatorContext.class);
    }

    @Test
    void testIsValidWhenValidEmail() {
        assertTrue(validator.isValid("example@example.com", constraintValidatorContext));
        assertTrue(validator.isValid("test123@gmail.com", constraintValidatorContext));
    }

    @Test
    void testIsValidWhenInvalidEmail() {
        assertFalse(validator.isValid("example.com", constraintValidatorContext));
        assertFalse(validator.isValid("user@my-website", constraintValidatorContext));
        assertFalse(validator.isValid("test123@gmail", constraintValidatorContext));
        assertFalse(validator.isValid("user@website..com", constraintValidatorContext));
    }
}
