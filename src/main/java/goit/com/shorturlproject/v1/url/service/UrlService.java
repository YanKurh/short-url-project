package goit.com.shorturlproject.v1.url.service;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.user.dto.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public interface UrlService {
    UrlLink saveAndFlush(String shortUrl, String longUrl, User user);

    Optional<UrlLink> findUrlLinkByLongUrl(String longUrl);

    UrlLink findUrlLinkByShortUrl(String shortUrl);

    void updateByClick(UrlLink urlLink);

    Set<String> findAllShortLinks();
}