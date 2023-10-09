package goit.com.shorturlproject.v1.url.service;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;

@Service
@Slf4j
public class ShortUrlGenerator {
    private final char[] myChars;
    private final Random myRand;
    private final int keyLength;
    private final UrlService urlService;

    public ShortUrlGenerator(UrlService urlService) {
        this.urlService = urlService;
        myRand = new Random();
        keyLength = 8;
        myChars = new char[62];
        //ініціалізуємо масив буквами
        for (int i = 0; i < 62; i++) {
            int j = 0;
            if (i < 10) {
                j = i + 48;
            } else if (i <= 35) {
                j = i + 55;
            } else {
                j = i + 61;
            }
            myChars[i] = (char) j;
        }
    }

    public void getKey(String longURL) {
        String key = generateKey();
        log.debug("short url {}", key);
        UrlLink urlLink = new UrlLink();
        urlLink.setLongUrl(longURL);
        urlLink.setShortUrl(key);
        urlService.save(urlLink);
    }

    private String generateKey() {
        StringBuilder key = new StringBuilder();
        boolean flag = true;
        Set<String> allShortLinks = urlService.findAllShortLinks();
        while (flag) {
            for (int i = 0; i <= keyLength; i++) {
                key.append(myChars[myRand.nextInt(62)]);
            }
            if (!allShortLinks.contains(String.valueOf(key))) {
                flag = false;
            }
        }
        return String.valueOf(key);
    }
}
