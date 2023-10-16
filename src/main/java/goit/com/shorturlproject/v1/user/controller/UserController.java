package goit.com.shorturlproject.v1.user.controller;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.service.UrlService;
import goit.com.shorturlproject.v1.user.dto.UrlLinkRequest;
import goit.com.shorturlproject.v1.user.dto.UrlLinkResponce;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.service.UserUrlHelper;
import goit.com.shorturlproject.v1.user.service.impl.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/auth/user")
public class UserController {
    private final UserUrlHelper userUrlHelper;

    private final UrlService urlService;

    private UserServiceImpl service;

    public UserController(UserUrlHelper userUrlHelper, UrlService urlService) {
        this.userUrlHelper = userUrlHelper;

        this.urlService = urlService;
    }

    @PostMapping("/createShortUrl")
    public @ResponseBody UrlLinkResponce createShortUrl(@RequestBody UrlLinkRequest urlLinkRequest) {
        User user = userUrlHelper.getUserById(urlLinkRequest.getUserId());
        UrlLink urlLink = userUrlHelper.createShortUrlForUser(urlLinkRequest.getLongUrl(), user);
        return new UrlLinkResponce(urlLink.getShortUrl(), urlLink.getLongUrl(), user.getLogin());
    }

    @GetMapping("/{id}/allActiveLinks")
    public Set<UrlLink> getAllActiveLinks(@PathVariable Long id) {
        return urlService.getAllLinksFromRedis().stream()
                .filter(link -> link.getUser().getId().equals(id))
                .map(link -> urlService.findUrlLinkByLongUrl(link.getLongUrl()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }


    @GetMapping(value = "/{id}/allLinks", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<UrlLink> getAllLinks(@PathVariable Long id){
        return urlService.findAllShortLinksByUserID(id);
    }

    @DeleteMapping("/deleteLink/{id}")
    public ResponseEntity<String> deleteLink(@PathVariable Long id) {
        boolean deleted = urlService.deleteUrlById(id);

        if (deleted) {
            return ResponseEntity.ok("Посилання було успішно видалено"); // HTTP статус 200 OK
        } else {
            return ResponseEntity.notFound().build(); // HTTP статус 404 Not Found
        }
    }




}
