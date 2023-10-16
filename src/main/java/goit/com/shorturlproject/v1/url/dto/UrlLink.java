package goit.com.shorturlproject.v1.url.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import goit.com.shorturlproject.v1.user.dto.User;
import jakarta.persistence.*;
import lombok.*;

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
    @JsonIgnore
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
        if (!(o instanceof UrlLink urlLink)) return false;
        return longUrl.equals(urlLink.longUrl) && shortUrl.equals(urlLink.shortUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longUrl, shortUrl);
    }
}
