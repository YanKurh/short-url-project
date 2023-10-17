package goit.com.shorturlproject.v1.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import goit.com.shorturlproject.v1.registration.validation.PasswordMatching;
import goit.com.shorturlproject.v1.registration.validation.StrongPassword;
import goit.com.shorturlproject.v1.registration.validation.ValidEmail;
import goit.com.shorturlproject.v1.url.dto.UrlLink;
import jakarta.persistence.*;
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
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotEmpty
    @Size(max = 30)
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
    @Column(length = 60)
    private String password;

    @NotEmpty
    @Column(length = 60)
    private String confirmPassword;


    private String login;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UrlLink> links;

    public User() {
        links = new HashSet<>();
    }

    public User(String firstName, String lastName, String email, String login, String password, String confirmPassword) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
