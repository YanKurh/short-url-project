package goit.com.shorturlproject.v1.user.dto;

import goit.com.shorturlproject.v1.ITestContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlLinkResponceTest implements ITestContainer {

    @Test
    void testGettersAndSetters() {
        String shortUrl = "shortUrlValue";
        String longUrl = "longUrlValue";
        String username = "usernameValue";

        UrlLinkResponce responce = new UrlLinkResponce();
        responce.setShortUrl(shortUrl);
        responce.setLongUrl(longUrl);
        responce.setUsername(username);

        assertEquals(shortUrl, responce.getShortUrl());
        assertEquals(longUrl, responce.getLongUrl());
        assertEquals(username, responce.getUsername());
    }

}