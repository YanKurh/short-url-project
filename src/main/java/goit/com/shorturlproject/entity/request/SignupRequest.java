package goit.com.shorturlproject.entity.request;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import goit.com.shorturlproject.validation.PasswordMatching;
import goit.com.shorturlproject.validation.StrongPassword;
// need delete
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//
import lombok.Getter;
import lombok.Setter;
@Entity
@PasswordMatching(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "Password and Confirm Password must be matched!"
)
  @Getter
  @Setter
  public class SignupRequest {
    // need delete
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //

    @NotBlank
    @Size(max = 45)
    private String surname;

    @NotBlank
    @Size(max = 30)
    private String name ;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @StrongPassword
    private String password;

    private String confirmPassword;

    private boolean isEnabled;
  }








