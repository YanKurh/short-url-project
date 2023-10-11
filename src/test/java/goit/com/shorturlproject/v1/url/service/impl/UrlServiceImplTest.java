package goit.com.shorturlproject.v1.url.service.impl;

import goit.com.shorturlproject.v1.ITestContainer;
import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.exceptions.UrlNotFoundException;
import goit.com.shorturlproject.v1.url.repository.UrlRepository;
import goit.com.shorturlproject.v1.user.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UrlServiceImplTest implements ITestContainer {

    @Mock
    private UrlRepository urlRepository;

    private UrlServiceImpl urlService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        urlService = new UrlServiceImpl(urlRepository);
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
        String longUrl = "https://www.example.com";
        UrlLink expectedUrlLink = new UrlLink();
        expectedUrlLink.setLongUrl(longUrl);

        when(urlRepository.findUrlLinkByLongUrl(longUrl)).thenReturn(Optional.of(expectedUrlLink));

        Optional<UrlLink> result = urlService.findUrlLinkByLongUrl(longUrl);

        assertTrue(result.isPresent());
        assertEquals(expectedUrlLink, result.get());
    }

    @Test
    void testFindUrlLinkByShortUrl() {
        String shortUrl = "abc123";
        UrlLink expectedUrlLink = new UrlLink();
        expectedUrlLink.setShortUrl(shortUrl);

        when(urlRepository.findUrlLinkByShortUrl(shortUrl)).thenReturn(Optional.of(expectedUrlLink));

        UrlLink result = urlService.findUrlLinkByShortUrl(shortUrl);

        assertEquals(expectedUrlLink, result);
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
