package goit.com.shorturlproject.url;

import lombok.*;

import java.time.LocalDateTime;

@Builder
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

    public static UrlLinkDTO fromUrlLink(UrlLink urlLink){

        return UrlLinkDTO.builder()
                .longUrl(urlLink.getLongUrl())
                .shortUrl(urlLink.getShortUrl())
                .createdAt(urlLink.getCreatedAt())
                .clickTimes(urlLink.getClickTimes())
                .expirationDate(urlLink.getExpirationDate())
                .build();
    }

    public static UrlLink fromUrlLinkDTO(UrlLinkDTO urlLinkDto){

        UrlLink url = new UrlLink();
        url.setLongUrl(urlLinkDto.getLongUrl());
        url.setShortUrl(urlLinkDto.getShortUrl());
        url.setCreatedAt(urlLinkDto.getCreatedAt());
        url.setClickTimes(urlLinkDto.getClickTimes());
        url.setExpirationDate(urlLinkDto.getExpirationDate());

        return url;

    }

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
