package goit.com.shorturlproject.url;

import lombok.*;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
public class UrlLinkDTO {

    @Getter
    private String longUrl;

    @Getter
    @Setter
    private String shortUrl;

    @Getter
    @Setter
    private LocalDateTime createdAt;

    @Getter
    private int clickTimes;

    @Getter
    @Setter
    private LocalDateTime expirationDate;

    public void setClickTimes(int clickTimes) {
        if (clickTimes < 0) {
            throw new IllegalArgumentException("clickTimes cannot be negative");
        }
        this.clickTimes = clickTimes;
    }

    public void setLongUrl(String longUrl) {
        if (!UrlValidator.isValidURL(longUrl)) {
            throw new IllegalArgumentException("This URL-link does not exist");
        }
        this.longUrl = longUrl;
    }

}
