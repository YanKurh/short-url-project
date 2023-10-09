package goit.com.shorturlproject.v1.url.service.impl;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.exceptions.UrlNotFoundException;
import goit.com.shorturlproject.v1.url.repository.UrlRepository;
import goit.com.shorturlproject.v1.url.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;


    @Override
    public UrlLink saveAndFlush(String shortUrl, String longUrl) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusHours(24);
        UrlLink urlLink = new UrlLink();
        urlLink.setShortUrl(shortUrl);
        urlLink.setLongUrl(longUrl);
        urlLink.setCreatedAt(now);
        urlLink.setExpirationDate(expirationDate);
        return urlRepository.saveAndFlush(urlLink);
    }

    @Override
    public Optional<UrlLink> findUrlLinkByLongUrl(String longUrl) {
        return urlRepository.findUrlLinkByLongUrl(longUrl);
    }

    @Override
    public UrlLink findUrlLinkByShortUrl(String shortUrl) {
        Optional<UrlLink> urlLinkByShortUrl = urlRepository.findUrlLinkByShortUrl(shortUrl);
        if (urlLinkByShortUrl.isEmpty()) {
            throw new UrlNotFoundException(String.format("Url not found %s", shortUrl));
        }
        return urlLinkByShortUrl.get();
    }

    @Transactional
    @Override
    public void updateByClick(UrlLink urlLink) {
        urlRepository.updateClickTimes(urlLink.getId(), urlLink.getClickTimes() + 1);
    }

    @Override
    public Set<String> findAllShortLinks() {
        return urlRepository.findAllShortUrlLinks();
    }
}

