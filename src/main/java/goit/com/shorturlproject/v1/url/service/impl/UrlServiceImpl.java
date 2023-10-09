package goit.com.shorturlproject.v1.url.service.impl;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.exceptions.UrlNotFoundException;
import goit.com.shorturlproject.v1.url.repository.UrlRepository;
import goit.com.shorturlproject.v1.url.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    @Override
    public Set<UrlLink> findAllLinksByUserId(Long userId) {
        return urlRepository.findAllByUserId(userId);
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
    public void save(UrlLink urlLink) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusHours(24);
        urlLink.setCreatedAt(now);
        urlLink.setExpirationDate(expirationDate);
        urlRepository.save(urlLink);
    }

    @Override
    public List<UrlLink> getAllLinks() {
        return urlRepository.findAll();
    }

    @Override
    public Set<String> findAllShortLinks() {
        return urlRepository.findAllShortUrlLinks();
    }

    @Override
    public String findLongUrlByShort(String shortUrl) {
        Optional<String> longUrlByShortUrl = urlRepository.findLongUrlByShortUrl(shortUrl);
        if (longUrlByShortUrl.isEmpty()) {
            throw new UrlNotFoundException(String.format("url not fount %s", shortUrl));
        }
        return longUrlByShortUrl.get();
    }
}

