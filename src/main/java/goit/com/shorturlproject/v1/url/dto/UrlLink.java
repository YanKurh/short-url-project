package goit.com.shorturlproject.v1.url.dto;

import goit.com.shorturlproject.v1.user.dto.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
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
