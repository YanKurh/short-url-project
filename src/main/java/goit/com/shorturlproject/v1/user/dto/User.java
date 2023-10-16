package goit.com.shorturlproject.v1.user.dto;

import goit.com.shorturlproject.v1.registration.validation.PasswordMatching;
import goit.com.shorturlproject.v1.registration.validation.StrongPassword;
import goit.com.shorturlproject.v1.registration.validation.ValidEmail;
import goit.com.shorturlproject.v1.url.dto.UrlLink;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotEmpty
    @Size(max = 30)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @NotEmpty
    @Size(max = 45)
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @ValidEmail
    @NotEmpty(message = "Email can not be empty")
    @Column(name = "email")
    private String email;

    @StrongPassword
    @NotEmpty
    @Column(name = "password",length = 60)
    private String password;

    @NotEmpty
    @Column(name = "confirm password",length = 60)
    private String confirmPassword;


    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UrlLink> links;

    public User() {
        links = new HashSet<>();
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String firstName, String lastName, String email, String userName, String password,Role role) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
