package goit.com.shorturlproject.v1.url.service.impl;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.exceptions.UrlNotFoundException;
import goit.com.shorturlproject.v1.url.repository.UrlRepository;
import goit.com.shorturlproject.v1.user.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("rawtypes")
class UrlServiceImplTest {

    private UrlServiceImpl urlService;

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private RedisTemplate redisTemplate;

    @Mock
    private ValueOperations<String, UrlLink> valueOperations;

    @SuppressWarnings("unchecked")
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

        when(urlRepository.findUrlLinkByLongUrl(longUrl)).thenReturn(Optional.of(urlLink));

        Optional<UrlLink> foundUrlLink = urlService.findUrlLinkByLongUrl(longUrl);

        assertTrue(foundUrlLink.isPresent());
        assertEquals(urlLink, foundUrlLink.get());
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
    void testFindUrlLinkByShortUrlUrlNotFoundException() {
        String shortUrl = "non-existent-short-url";

        when(valueOperations.get(shortUrl)).thenReturn(null);

        assertThrows(UrlNotFoundException.class, () -> urlService.findUrlLinkByShortUrl(shortUrl));
    }




    @Test
    void testFindUrlLinkByShortUrlNotFound() {
        String shortUrl = "nonexistentShortUrl";

        when(urlRepository.findUrlLinkByShortUrl(shortUrl)).thenReturn(Optional.empty());

        assertThrows(UrlNotFoundException.class, () -> urlService.findUrlLinkByShortUrl(shortUrl));
    }

    @Test
    void testUpdateByClick() {
        UrlLink urlLink = new UrlLink();
        urlLink.setId(1L);
        urlLink.setClickTimes(0);

        urlService.updateByClick(urlLink);

        verify(urlRepository, times(1)).updateClickTimes(urlLink.getId(), urlLink.getClickTimes() + 1);
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
