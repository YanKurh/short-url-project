package goit.com.shorturlproject.v1.url.service.impl;

import goit.com.shorturlproject.v1.ITestContainer;
import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.exceptions.UrlNotFoundException;
import goit.com.shorturlproject.v1.url.repository.UrlRepository;
import goit.com.shorturlproject.v1.user.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SuppressWarnings("ALL")
class UrlServiceImplTest implements ITestContainer {

    private UrlServiceImpl urlService;

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private RedisTemplate<String, UrlLink> redisTemplate;

    @Mock
    private ValueOperations<String, UrlLink> valueOperations;

    @BeforeEach
    void setUp() {
        urlRepository = mock(UrlRepository.class);
        redisTemplate = mock(RedisTemplate.class);
        valueOperations = mock(ValueOperations.class);

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        urlService = new UrlServiceImpl(urlRepository, redisTemplate);
    }

    @Test
    void testSaveAndFlush() {
        String shortUrl = "abc123";
        String longUrl = "https://www.example.com";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusHours(24);
        User user = new User();
        UrlLink expectedUrlLink = new UrlLink();
        expectedUrlLink.setShortUrl(shortUrl);
        expectedUrlLink.setLongUrl(longUrl);
        expectedUrlLink.setCreatedAt(now);
        expectedUrlLink.setUser(user);
        expectedUrlLink.setExpirationDate(expirationDate);

        when(urlRepository.saveAndFlush(any(UrlLink.class))).thenReturn(expectedUrlLink);

        UrlLink result = urlService.saveAndFlush(shortUrl, longUrl, user);

        assertEquals(expectedUrlLink, result);
    }

    @Test
    void testFindUrlLinkByLongUrl() {
        String longUrl = "http://example.com";
        UrlLink urlLink = new UrlLink();
        List<UrlLink> urlLinks = List.of(urlLink); // Simulating the returned list

        when(urlRepository.findUrlLinkByLongUrl(longUrl)).thenReturn(urlLinks);

        List<UrlLink> foundUrlLinks = urlService.findUrlLinkByLongUrl(longUrl);

        assertEquals(urlLinks, foundUrlLinks);
    }


    @Test
    void testFindUrlLinkByShortUrl() {
        String shortUrl = "short-url";
        UrlLink urlLink = new UrlLink();

        when(valueOperations.get(shortUrl)).thenReturn(urlLink);

        UrlLink foundUrlLink = urlService.findUrlLinkByShortUrl(shortUrl);

        assertEquals(urlLink, foundUrlLink);
    }


    @Test
    void testUpdateByClick() {
        UrlLink urlLink = new UrlLink();
        urlLink.setShortUrl("dlalda");
        urlLink.setClickTimes(5);

        when(valueOperations.get(urlLink.getShortUrl())).thenReturn(urlLink);

        urlService.updateByClick(urlLink);

        verify(valueOperations).set(eq(urlLink.getShortUrl()), any(UrlLink.class));

        verify(urlRepository).updateClickTimes(eq(urlLink.getShortUrl()), eq(urlLink.getClickTimes()));
    }

    @Test
    void testFindAllShortLinks() {
        Set<String> shortLinks = new HashSet<>();
        shortLinks.add("abc123");
        shortLinks.add("def456");

        when(urlRepository.findAllShortUrlLinks()).thenReturn(shortLinks);

        Set<String> result = urlService.findAllShortLinks();

        assertEquals(shortLinks, result);
    }
}