package goit.com.shorturlproject.v1.user.dto;

import goit.com.shorturlproject.v1.ITestContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UrlLinkRequestTest implements ITestContainer {

    @Test
    public void testEqualsAndHashCode() {
        UrlLinkRequest request1 = new UrlLinkRequest();
        request1.setUserId(1L);
        request1.setLongUrl("http://example.com");

        UrlLinkRequest request2 = new UrlLinkRequest();
        request2.setUserId(1L);
        request2.setLongUrl("http://example.com");

        UrlLinkRequest request3 = new UrlLinkRequest();
        request3.setUserId(2L);
        request3.setLongUrl("http://example.com");

        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());

        assertNotEquals(request1, request3);
        assertNotEquals(request1.hashCode(), request3.hashCode());
    }

    @Test
    public void testToString() {
        UrlLinkRequest request = new UrlLinkRequest();
        request.setUserId(1L);
        request.setLongUrl("http://example.com");

        String expected = "UrlLinkRequest{userId=1, longUrl='http://example.com'}";
        assertEquals(expected, request.toString());
    }
}