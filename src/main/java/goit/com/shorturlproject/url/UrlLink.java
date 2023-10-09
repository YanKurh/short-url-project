package goit.com.shorturlproject.url;
import goit.com.shorturlproject.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class UrlLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "long_URL")
    private String longUrl;

    @Column(name = "short_URL")
    private String shortUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "click_times")
    private int clickTimes;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
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