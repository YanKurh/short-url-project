package goit.com.shorturlproject.url;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UrlLinkDTO {

    private String longUrl;
    private LocalDateTime createdAt;
    private int clickTimes;

    public static UrlLinkDTO fromUrlLink(UrlLink urlLink){

        return UrlLinkDTO.builder()
                .longUrl(urlLink.getLongUrl())
                .createdAt(urlLink.getCreatedAt())
                .clickTimes(urlLink.getClickTimes())
                .build();
    }

    public static UrlLink fromUrlLinkDTO(UrlLinkDTO urlLinkDto){

        UrlLink url = new UrlLink();
        url.setLongUrl(urlLinkDto.getLongUrl());
        url.setCreatedAt(urlLinkDto.getCreatedAt());
        url.setClickTimes(urlLinkDto.getClickTimes());

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
