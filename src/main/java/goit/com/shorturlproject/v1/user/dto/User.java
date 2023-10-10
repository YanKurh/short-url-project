package goit.com.shorturlproject.v1.user.dto;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String login;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UrlLink> links;

    public User() {
        links = new HashSet<>();
    }

    public User(String firstName, String lastName, String email, String login, String password) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
