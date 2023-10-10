package goit.com.shorturlproject.v1.user.dto;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter
    private long id;

    @Setter
    @Column(name = "user_name")
    private String userName;

    @Setter
    @Column(name = "email")
    private String email;

    @Setter
    @Column(name = "login")
    private String login;

    @Setter
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Setter
    private Set<UrlLink> links;

    public User() {
        links = new HashSet<>();
    }

    public User(String userName, String email, String login, String password) {
        this();
        this.userName = userName;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
