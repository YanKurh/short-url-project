package goit.com.shorturlproject.v1.url.service;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.exceptions.UrlExistException;
import goit.com.shorturlproject.v1.url.exceptions.UrlNotValidException;
import goit.com.shorturlproject.v1.user.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UrlShortener {
    private final UrlValidator urlValidator;
    private final UrlService urlService;
    private final ShortUrlGenerator urlGenerator;


    public UrlShortener(UrlValidator urlValidator, UrlService urlService, ShortUrlGenerator urlGenerator) {
        this.urlValidator = urlValidator;
        this.urlService = urlService;
        this.urlGenerator = urlGenerator;
    }


    public UrlLink shortenURL(String longURL, User user) {
        if (!urlValidator.isValidURL(longURL)) {
            throw new UrlNotValidException(longURL);
        }

        List<UrlLink> urlLinksByLongUrl = urlService.findUrlLinkByLongUrl(longURL);

        for (UrlLink urlLink : urlLinksByLongUrl) {
            if (Objects.equals(urlLink.getUser().getId(), user.getId())) {
                String shortUrl = urlLink.getShortUrl();
                UrlLink cachedUrlLink = urlService.findUrlLinkByShortUrl(shortUrl);
                if (cachedUrlLink != null) {
                    throw new UrlExistException(longURL);
                }
            }
        }
        String key = urlGenerator.getKey();
        return urlService.saveAndFlush(key, longURL, user);
    }
}