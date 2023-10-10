package goit.com.shorturlproject.dto;

import goit.com.shorturlproject.url.UrlLink;
import goit.com.shorturlproject.validation.PasswordMatching;
import goit.com.shorturlproject.validation.StrongPassword;
import goit.com.shorturlproject.validation.ValidEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@PasswordMatching(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "Password and Confirm Password must be matched!"
)
@Getter
@Setter
public class UserDto {


    @NotBlank
    @NotEmpty
    @Size(max = 45)
    private String lastName;

    @NotBlank
    @NotEmpty
    @Size(max = 30)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @ValidEmail
    @NotEmpty(message = "Email can not be empty")
    private String email;

    @StrongPassword
    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    private boolean isEnabled;
}