package goit.com.shorturlproject.user;

import goit.com.shorturlproject.url.UrlLink;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;

@Entity
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    @Column(name = "user_name")
    private String userName;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "login")
    private String login;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    @Getter
    @Setter
    private HashSet<UrlLink> links;

}
