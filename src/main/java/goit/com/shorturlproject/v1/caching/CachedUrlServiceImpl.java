package goit.com.shorturlproject.v1.caching;


import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.service.impl.UrlServiceImpl;
import goit.com.shorturlproject.v1.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CachedUrlServiceImpl {

    private final UrlServiceImpl urlService;

    @Cacheable(value="urlCache", key = "#shortUrl")
    public UrlLink saveAndFlush(String shortUrl, String longUrl, User user){
        return urlService.saveAndFlush(shortUrl, longUrl, user);
    }

    @Cacheable(value="urlCache", key = "#shortUrl")
    public UrlLink findUrlLinkByShortUrl(String shortUrl) {
        return urlService.findUrlLinkByShortUrl(shortUrl);
    }



}
