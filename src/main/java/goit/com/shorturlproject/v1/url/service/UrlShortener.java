package goit.com.shorturlproject.v1.url.service;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.exceptions.UrlNotValidException;
import org.springframework.stereotype.Service;

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


    public UrlLink shortenURL(String longURL) {
        if (urlValidator.isValidURL(longURL)) {
            Optional<UrlLink> urlLinkByLongUrl = urlService.findUrlLinkByLongUrl(longURL);
            if (urlLinkByLongUrl.isEmpty()) {
                String key = urlGenerator.getKey();
                return urlService.saveAndFlush(key, longURL);
            } else {
                return urlLinkByLongUrl.get();
            }
        }
        throw new UrlNotValidException(String.format("Url not valid %s", longURL));
    }
}
