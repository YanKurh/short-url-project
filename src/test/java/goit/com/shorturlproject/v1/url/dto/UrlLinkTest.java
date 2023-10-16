package goit.com.shorturlproject.v1.url.dto;

import goit.com.shorturlproject.v1.ITestContainer;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UrlLinkTest implements ITestContainer {

    @Test
    public void testEqualsAndHashCode() {
        UrlLink link1 = new UrlLink();
        link1.setLongUrl("http://example.com");
        link1.setShortUrl("abc123");

        UrlLink link2 = new UrlLink();
        link2.setLongUrl("http://example.com");
        link2.setShortUrl("abc123");

        UrlLink link3 = new UrlLink();
        link3.setLongUrl("http://another-example.com");
        link3.setShortUrl("xyz789");

        assertEquals(link1, link2);
        assertEquals(link1.hashCode(), link2.hashCode());

        assertNotEquals(link1, link3);
        assertNotEquals(link1.hashCode(), link3.hashCode());
    }

    @Test
    public void testConstructor() {
        LocalDateTime now = LocalDateTime.now();
        UrlLink link = new UrlLink(1L, "http://example.com", "abc123", now, 0, now);

        assertEquals("http://example.com", link.getLongUrl());
        assertEquals("abc123", link.getShortUrl());
        assertEquals(now, link.getCreatedAt());
        assertEquals(0, link.getClickTimes());
        assertEquals(now, link.getExpirationDate());
    }

}