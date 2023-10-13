package goit.com.shorturlproject.v1.registration.validation.impl;

import goit.com.shorturlproject.v1.registration.validation.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    return value.matches("^[A-Z].*\\d$");
  }

}
