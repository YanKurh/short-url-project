package goit.com.shorturlproject.v1.user.controller;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.service.UrlService;
import goit.com.shorturlproject.v1.url.service.impl.UrlServiceImpl;
import goit.com.shorturlproject.v1.user.dto.UrlLinkRequest;
import goit.com.shorturlproject.v1.user.dto.UrlLinkResponce;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.service.UserService;
import goit.com.shorturlproject.v1.user.service.UserUrlHelper;
import goit.com.shorturlproject.v1.user.service.impl.UserServiceImpl;
import org.hibernate.mapping.Collection;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/auth/user")
public class UserController {
    private final UserUrlHelper userUrlHelper;

    private UrlService urlServise;

    private UserServiceImpl service;

    public UserController(UserUrlHelper userUrlHelper) {
        this.userUrlHelper = userUrlHelper;

    }

    @PostMapping("/createShortUrl")
    public @ResponseBody UrlLinkResponce createShortUrl(@RequestBody UrlLinkRequest urlLinkRequest) {
        User user = userUrlHelper.getUserById(urlLinkRequest.getUserId());
        UrlLink urlLink = userUrlHelper.createShortUrlForUser(urlLinkRequest.getLongUrl(), user);
        return new UrlLinkResponce(urlLink.getShortUrl(), urlLink.getLongUrl(), user.getLogin());
    }

    @GetMapping("/{id}/allActiveLinks")
    public Set<UrlLink> getAllActiveLinks(@PathVariable Long id){
        LocalDateTime dateTime = LocalDateTime.now();
        Set<UrlLink> links = urlServise.findAllShortLinksByUserID(id);
        Set<UrlLink> val = links.stream().filter(x -> dateTime.isBefore(x.getExpirationDate())).collect(Collectors.toSet());
        return val;
    }

    @GetMapping(value = "/{id}/allLinks", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<UrlLink> getAllLinks(@PathVariable Long id){
        return urlServise.findAllShortLinksByUserID(id);
    }

    @DeleteMapping("{userId}/deleteLink/{linkId}")
    public ResponseEntity<String> deleteLink(@PathVariable Long userId, @PathVariable Long linkId) {
        boolean deleted = urlServise.deleteUrlById(userId, linkId);

        if (deleted) {
            return ResponseEntity.ok("Посилання було успішно видалено"); // HTTP статус 200 OK
        } else {
            return ResponseEntity.notFound().build(); // HTTP статус 404 Not Found
        }
    }




}
