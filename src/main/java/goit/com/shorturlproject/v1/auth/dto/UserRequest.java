package goit.com.shorturlproject.v1.auth.dto;

import goit.com.shorturlproject.v1.registration.validation.PasswordMatching;
import goit.com.shorturlproject.v1.registration.validation.StrongPassword;
import goit.com.shorturlproject.v1.registration.validation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
@PasswordMatching(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "Password and Confirm Password must be matched!"
)
public class UserRequest {

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
