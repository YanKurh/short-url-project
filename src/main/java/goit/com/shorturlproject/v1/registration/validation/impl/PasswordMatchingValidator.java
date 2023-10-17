package goit.com.shorturlproject.v1.registration.validation.impl;

import goit.com.shorturlproject.v1.registration.validation.PasswordMatching;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Objects;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, Object> {

  private String password;
  private String confirmPassword;

  @Override
  public void initialize(PasswordMatching matching) {
    this.password = matching.password();
    this.confirmPassword = matching.confirmPassword();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
    Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);

    return Objects.equals(passwordValue, confirmPasswordValue);
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}