package goit.com.shorturlproject.v1.url.handlers;

import goit.com.shorturlproject.v1.url.exceptions.UrlNotFoundException;
import goit.com.shorturlproject.v1.url.exceptions.UrlNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UrlExceptionHandler {
    @ExceptionHandler({UrlNotValidException.class, UrlNotFoundException.class})
    public ResponseEntity<String> handleUrlExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
