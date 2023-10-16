package goit.com.shorturlproject.v1.url.service.impl;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.exceptions.UrlNotFoundException;
import goit.com.shorturlproject.v1.url.repository.UrlRepository;
import goit.com.shorturlproject.v1.url.service.UrlService;
import goit.com.shorturlproject.v1.user.dto.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    private final RedisTemplate<String,UrlLink> template;

    public UrlServiceImpl(UrlRepository urlRepository, RedisTemplate<String, UrlLink> template) {
        this.urlRepository = urlRepository;
        this.template = template;
    }


    @Override
    public UrlLink saveAndFlush(String shortUrl, String longUrl, User user) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusHours(24);
        UrlLink urlLink = new UrlLink();
        urlLink.setShortUrl(shortUrl);
        urlLink.setLongUrl(longUrl);
        urlLink.setUser(user);
        template.opsForValue().set(shortUrl, urlLink);
        template.expire(shortUrl, 30, TimeUnit.SECONDS);
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
        UrlLink urlLink = template.opsForValue().get(shortUrl);
        if (urlLink == null) {
            throw new UrlNotFoundException(shortUrl);
        }
        return urlLink;
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

    @Override
    public Set<UrlLink> findAllShortLinksByUserID(Long id) {
        return urlRepository.findAllByUserId(id);
    }

    @Override
    public boolean deleteUrlById(Long id) {
        try {
            urlRepository.deleteUrlLinkById(id);
            return true; // Видалення відбулося успішно
        } catch (Exception e) {
            return false; // Помилка під час видалення
        }
    }
}

