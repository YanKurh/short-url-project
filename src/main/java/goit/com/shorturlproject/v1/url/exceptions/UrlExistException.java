package goit.com.shorturlproject.v1.url.exceptions;

public class UrlExistException extends RuntimeException {
    public UrlExistException(String longURL) {
        super(String.format("url exist %s", longURL));
    }
}