package goit.com.shorturlproject.v1.registration.validation;
import goit.com.shorturlproject.v1.registration.validation.impl.PasswordMatchingValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PasswordMatchingValidatorTest {

    private PasswordMatchingValidator validator;
    private PasswordMatching passwordMatching;
    private ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach
    void setUp() {
        validator = new PasswordMatchingValidator();
        passwordMatching = mock(PasswordMatching.class);
        when(passwordMatching.password()).thenReturn("password");
        when(passwordMatching.confirmPassword()).thenReturn("confirmPassword");
        validator.initialize(passwordMatching);
        constraintValidatorContext = mock(ConstraintValidatorContext.class);
    }

    @Test
    void testIsValidWhenPasswordsMatch() {
        TestObject testObject = new TestObject("Password123", "Password123");
        boolean valid = validator.isValid(testObject, constraintValidatorContext);
        assertTrue(valid);
    }

    @Test
    void testIsValidWhenPasswordsDoNotMatch() {
        TestObject testObject = new TestObject("Password123", "differentPassword");
        boolean valid = validator.isValid(testObject, constraintValidatorContext);
        assertFalse(valid);
    }
}
