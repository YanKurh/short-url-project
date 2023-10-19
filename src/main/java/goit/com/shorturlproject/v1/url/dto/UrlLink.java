package goit.com.shorturlproject.v1.url.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import goit.com.shorturlproject.v1.user.dto.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "url")
public class UrlLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String longUrl;

    private String shortUrl;

    private LocalDateTime createdAt;

    private int clickTimes;

    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UrlLink(Long id, String longUrl, String shortUrl, LocalDateTime createdAt, int clickTimes, LocalDateTime expirationDate) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.createdAt = createdAt;
        this.clickTimes = clickTimes;
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlLink urlLink = (UrlLink) o;
        return clickTimes == urlLink.clickTimes &&
                Objects.equals(id, urlLink.id) &&
                Objects.equals(longUrl, urlLink.longUrl) &&
                Objects.equals(shortUrl, urlLink.shortUrl) &&
                Objects.equals(createdAt, urlLink.createdAt) &&
                Objects.equals(expirationDate, urlLink.expirationDate) &&
                Objects.equals(user, urlLink.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longUrl, shortUrl, createdAt, clickTimes, expirationDate, user);
    }
}
