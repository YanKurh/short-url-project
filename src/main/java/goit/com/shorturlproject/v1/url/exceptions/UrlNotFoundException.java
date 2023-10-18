package goit.com.shorturlproject.v1.url.exceptions;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String url) {
        super(String.format("url not found %s", url));
    }
}
