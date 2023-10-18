package goit.com.shorturlproject.v1.url.exceptions;

public class UrlNotValidException extends RuntimeException {
    public UrlNotValidException(String url) {
        super(String.format("url don't valid %s", url));
    }
}
