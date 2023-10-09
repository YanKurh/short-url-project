package goit.com.shorturlproject.dto;

import goit.com.shorturlproject.url.UrlLink;
import goit.com.shorturlproject.validation.PasswordMatching;
import goit.com.shorturlproject.validation.StrongPassword;
import goit.com.shorturlproject.validation.ValidEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Size(max = 45)
    private String lastName;

    @NotBlank
    @Size(max = 30)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @ValidEmail
    private String email;

    @StrongPassword
    private String password;

    private String confirmPassword;

    private boolean isEnabled;
}