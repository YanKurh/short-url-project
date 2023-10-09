package goit.com.shorturlproject.v1.url.service;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface UrlService {
    Set<UrlLink> findAllLinksByUserId(Long userId);

    Optional<UrlLink> findUrlLinkByLongUrl(String longUrl);

    UrlLink findUrlLinkByShortUrl(String shortUrl);

    void updateByClick(UrlLink urlLink);

    void save(UrlLink urlLink);

    List<UrlLink> getAllLinks();

    Set<String> findAllShortLinks();

    String findLongUrlByShort(String shortUrl);
}
