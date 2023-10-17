package goit.com.shorturlproject.v1.url.service.impl;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.repository.UrlRepository;
import goit.com.shorturlproject.v1.url.service.UrlService;
import goit.com.shorturlproject.v1.user.dto.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    private final ValueOperations<String, UrlLink> valueOperations;


    public UrlServiceImpl(UrlRepository urlRepository, RedisTemplate<String, UrlLink> redisTemplate) {
        this.urlRepository = urlRepository;
        this.valueOperations = redisTemplate.opsForValue();
    }


    @Override
    public UrlLink saveAndFlush(String shortUrl, String longUrl, User user) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusHours(24);
        UrlLink urlLink = new UrlLink();
        urlLink.setShortUrl(shortUrl);
        urlLink.setLongUrl(longUrl);
        urlLink.setUser(user);
        urlLink.setCreatedAt(now);
        urlLink.setExpirationDate(expirationDate);
        UrlLink urlLink1 = urlRepository.saveAndFlush(urlLink);
        saveToRedis(shortUrl, urlLink1);
        return urlLink1;
    }

    @Override
    public List<UrlLink> findUrlLinkByLongUrl(String longUrl) {
        return urlRepository.findUrlLinkByLongUrl(longUrl);
    }
    @Override
    public UrlLink findUrlLinkByShortUrl(String shortUrl) {
        return valueOperations.get(shortUrl);
    }

    @Transactional
    @Override
    public void updateByClick(UrlLink urlLink) {
        urlRepository.updateClickTimes(urlLink.getShortUrl(), urlLink.getClickTimes() + 1);
    }

    @Override
    public Set<String> findAllShortLinks() {
        return urlRepository.findAllShortUrlLinks();
    }

    @Override
    public Set<UrlLink> findAllShortLinksByUserID(Long id) {
        return urlRepository.findAllByUserId(id);
    }

    @Transactional
    @Override
    public int deleteUrlById(Long userId, Long linkId) {
        try {
            return urlRepository.deleteUrlLinkById(userId, linkId);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Set<UrlLink> getAllLinksFromRedis() {
        Set<String> keys = valueOperations.getOperations().keys("*");
        Set<UrlLink> urlLinks = new HashSet<>();

        for (String key : Objects.requireNonNull(keys)) {
            UrlLink urlLink = valueOperations.get(key);
            if (urlLink != null) {
                urlLinks.add(urlLink);
            }
        }

        return urlLinks;
    }

    private void saveToRedis(String shortUrl, UrlLink urlLink) {
        valueOperations.set(shortUrl, urlLink);
        valueOperations.getAndExpire(shortUrl, 1, TimeUnit.DAYS);
    }
}