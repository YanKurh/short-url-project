package goit.com.shorturlproject.v1.registration.validation;

import goit.com.shorturlproject.v1.registration.validation.impl.StrongPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {
  String message() default "Password must be 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
