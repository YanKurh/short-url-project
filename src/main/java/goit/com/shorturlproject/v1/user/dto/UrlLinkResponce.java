package goit.com.shorturlproject.v1.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UrlLinkResponce {
    private String shortUrl;
    private String longUrl;
    private String username;

    public UrlLinkResponce(String shortUrl, String longUrl, String username) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.username = username;
    }

    public UrlLinkResponce() {
    }
}
