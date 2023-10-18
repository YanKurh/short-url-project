package goit.com.shorturlproject.v1.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UrlLinkRequest {
    private Long userId;
    private String longUrl;

    @Override
    public String toString() {
        return "UrlLinkRequest{" +
                "userId=" + userId +
                ", longUrl='" + longUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlLinkRequest that = (UrlLinkRequest) o;
        return Objects.equals(userId, that.userId) && Objects.equals(longUrl, that.longUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, longUrl);
    }
}
