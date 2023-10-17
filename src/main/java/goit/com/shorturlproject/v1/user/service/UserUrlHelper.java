package goit.com.shorturlproject.v1.user.service;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.service.UrlService;
import goit.com.shorturlproject.v1.url.service.UrlShortener;
import goit.com.shorturlproject.v1.user.dto.UrlLinkRequest;
import goit.com.shorturlproject.v1.user.dto.UrlLinkResponce;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserUrlHelper {
    private final UserService userService;
    private final UrlShortener urlShortener;
    private final UrlService urlService;
    @Value("${domain}")
    private String domain;

    public UserUrlHelper(UserService userService, UrlShortener urlShortener, UrlService urlService) {
        this.userService = userService;
        this.urlShortener = urlShortener;
        this.urlService = urlService;
    }

    public User getUserById(Long userId) {
        return userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UrlLink createShortUrlForUser(String longUrl, User user) {
        return urlShortener.shortenURL(longUrl, user);
    }

    public UrlLinkResponce createShortUrl(UrlLinkRequest urlLinkRequest) {
        User user = getUserById(urlLinkRequest.getUserId());
        UrlLink urlLink = createShortUrlForUser(urlLinkRequest.getLongUrl(), user);
        return new UrlLinkResponce(domain + urlLink.getShortUrl() + "/short", urlLink.getLongUrl(), user.getUsername());
    }

    public Set<UrlLink> getAllActiveLinks(Long userId) {
        return urlService.getAllLinksFromRedis().stream()
                .filter(link -> {
                    User user = link.getUser();
                    return user != null && user.getId() != null && user.getId().equals(userId);
                })
                .collect(Collectors.toSet());
    }

    public Set<UrlLink> getAllLinks(Long userId) {
        return urlService.findAllShortLinksByUserID(userId);
    }

    public ResponseEntity<String> deleteLink(Long userId, Long linkId) {
        if (urlService.deleteUrlById(userId, linkId) != 0) {
            return ResponseEntity.ok("Link was successfully deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}