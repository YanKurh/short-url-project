package goit.com.shorturlproject.url;

import goit.com.shorturlproject.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@RequiredArgsConstructor
public class UrlLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(name = "long_URL")
    @Getter
    @Setter
    private String longUrl;

    @Column(name = "short_URL")
    @Getter
    @Setter
    private String shortUrl;

    @Column(name = "created_at")
    @Getter
    @Setter
    private LocalDateTime createdAt;

    @Column(name = "click_times")
    @Getter
    @Setter
    private int clickTimes;

    @Column(name = "expiration_date")
    @Getter
    @Setter
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UrlLink)) return false;
        UrlLink urlLink = (UrlLink) o;
        return longUrl.equals(urlLink.longUrl) && shortUrl.equals(urlLink.shortUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longUrl, shortUrl);
    }
}
