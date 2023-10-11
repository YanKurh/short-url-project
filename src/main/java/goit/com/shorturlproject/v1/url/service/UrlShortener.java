package goit.com.shorturlproject.v1.url.service;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.exceptions.UrlNotValidException;
import goit.com.shorturlproject.v1.user.dto.User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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
        if (urlValidator.isValidURL(longURL)) {
            Optional<UrlLink> urlLinkByLongUrl = urlService.findUrlLinkByLongUrl(longURL);
            if (urlLinkByLongUrl.isEmpty()) {
                String key = urlGenerator.getKey();
                return urlService.saveAndFlush(key, longURL, user);
            } else {
                if (Objects.equals(urlLinkByLongUrl.get().getUser().getId(), user.getId())) {
                    return urlLinkByLongUrl.get();
                }
            }
        }
        throw new UrlNotValidException(longURL);
    }
}
