package goit.com.shorturlproject.user;

import goit.com.shorturlproject.url.UrlLink;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String password;

    @OneToMany(mappedBy = "user")
    private List<UrlLink> links;

    public void setLinks(List<UrlLink> links) {
        this.links = links;
        for (UrlLink link : links) {
            link.setUser(this);
        }
    }
}
