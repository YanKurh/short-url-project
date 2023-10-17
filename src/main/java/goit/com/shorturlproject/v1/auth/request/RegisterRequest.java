package goit.com.shorturlproject.v1.auth.request;

import goit.com.shorturlproject.v1.registration.validation.PasswordMatching;
import goit.com.shorturlproject.v1.registration.validation.StrongPassword;
import goit.com.shorturlproject.v1.registration.validation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@PasswordMatching(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "Password and Confirm Password must be matched!"
)
@Getter
@Setter
public class RegisterRequest {

    private String firstName;

    @NotBlank
    @NotEmpty
    @Size(max = 45)
    private String lastName;

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


    private String userName;

}
