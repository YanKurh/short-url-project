package goit.com.shorturlproject.v1.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import goit.com.shorturlproject.v1.registration.validation.StrongPassword;
import goit.com.shorturlproject.v1.url.dto.UrlLink;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "\"user\"")
@JsonIgnoreProperties(value = {
        "links",
        "confirmPassword",
        "password",
        "role"
})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    private String firstName;

    @Size(max = 45)
    private String lastName;

    private String email;

    private String password;

    @Column(length = 60)
    private String confirmPassword;

    @Column(name = "userName", length = 60)
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UrlLink> links;

    public User() {
        links = new HashSet<>();
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String firstName, String lastName, String email, String userName, String password, Role role) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return userName;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}