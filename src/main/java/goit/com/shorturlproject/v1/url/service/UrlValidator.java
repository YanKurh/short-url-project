package goit.com.shorturlproject.v1.url.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Service
public class UrlValidator {

    public boolean isValidURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            log.error("Url not valid {}", url);
            return false;
        }
    }
}
