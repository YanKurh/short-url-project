package goit.com.shorturlproject.v1.user.service;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.service.UrlShortener;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserUrlHelper {
    private final UserService userService;
    private final UrlShortener urlShortener;

    public UserUrlHelper(UserService userService, UrlShortener urlShortener) {
        this.userService = userService;
        this.urlShortener = urlShortener;
    }

    public User getUserById(Long userId) {
        return userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UrlLink createShortUrlForUser(String longUrl, User user) {
        return urlShortener.shortenURL(longUrl, user);
    }
}

