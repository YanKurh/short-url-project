package goit.com.shorturlproject.v1.user.dto;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import jakarta.persistence.*;
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
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password",length = 60)
    private String password;

    @Column(name = "confirm_password",length = 60)
    private String confirmPassword;

    @Column(name = "userName" , length = 60)
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private transient Set<UrlLink> links;

    public User() {
        links = new HashSet<>();
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String firstName, String lastName, String email, String userName, String password) {
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
