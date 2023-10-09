package goit.com.shorturlproject.v1.url.dto;

import goit.com.shorturlproject.v1.user.dto.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "urls")
public class UrlLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private long id;

    @Column(name = "long_url")
    @Setter
    private String longUrl;

    @Column(name = "short_url")
    @Setter
    private String shortUrl;

    @Column(name = "created_at")
    @Setter
    private LocalDateTime createdAt;

    @Column(name = "click_times")
    @Setter
    private int clickTimes;

    @Column(name = "expiration_date")
    @Setter
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UrlLink urlLink)) return false;
        return longUrl.equals(urlLink.longUrl) && shortUrl.equals(urlLink.shortUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longUrl, shortUrl);
    }
}
