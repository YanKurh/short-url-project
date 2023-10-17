package goit.com.shorturlproject.v1.registration.validation;

import goit.com.shorturlproject.v1.registration.validation.impl.StrongPasswordValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StrongPasswordValidatorTest {

    private StrongPasswordValidator validator;
    private ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach
    void setUp() {
        validator = new StrongPasswordValidator();
        constraintValidatorContext = null;
    }

    @Test
    void testIsValidWhenPasswordStartsWithUppercaseAndEndsWithDigit() {
        String validPassword = "TestPassword1";
        boolean valid = validator.isValid(validPassword, constraintValidatorContext);
        assertTrue(valid);
    }

    @Test
    void testIsValidWhenPasswordDoesNotStartWithUppercase() {
        String invalidPassword = "testPassword1";
        boolean valid = validator.isValid(invalidPassword, constraintValidatorContext);
        assertFalse(valid);
    }

    @Test
    void testIsValidWhenPasswordDoesNotEndWithDigit() {
        String invalidPassword = "TestPassword";
        boolean valid = validator.isValid(invalidPassword, constraintValidatorContext);
        assertFalse(valid);
    }
}
