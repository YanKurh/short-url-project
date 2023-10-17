package goit.com.shorturlproject.v1.user.controller;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.user.dto.UrlLinkRequest;
import goit.com.shorturlproject.v1.user.dto.UrlLinkResponce;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.service.UserService;
import goit.com.shorturlproject.v1.user.service.UserUrlHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("v1/auth/user")
@RequiredArgsConstructor
public class UserController {
    private final UserUrlHelper userUrlHelper;
    private final UserService userService;
    @Operation(summary = "Get a user by id", description = "Returns user by the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully"),
            @ApiResponse(responseCode = "404", description = "The user was not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserByID(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new short link", description = "Returns a short link from a long link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "The link does not exist or was not found")
    })
    @PostMapping("/createShortUrl")
    public @ResponseBody UrlLinkResponce createShortUrl(@RequestBody UrlLinkRequest urlLinkRequest) {
        return userUrlHelper.createShortUrl(urlLinkRequest);
    }

    @Operation(summary = "Get all active links by id", description = "Returns a list of all active links as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "The user with the specified ID was not found")
    })
    @GetMapping("/{id}/allActiveLinks")
    public Set<UrlLink> getAllActiveLinks(@PathVariable Long id) {
        return userUrlHelper.getAllActiveLinks(id);
    }


    @Operation(summary = "Get all links by id", description = "Returns a list of all links as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "The user with the specified ID was not found")
    })
    @GetMapping(value = "/{id}/allLinks", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<UrlLink> getAllLinks(@PathVariable Long id) {
        return userUrlHelper.getAllLinks(id);
    }

    @Operation(summary = "Delete the link by id", description = "Returns the status regarding the removal of the link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "The user with the specified ID was not found")
    })
    @DeleteMapping("{userId}/deleteLink/{linkId}")
    public ResponseEntity<String> deleteLink(@PathVariable Long userId, @PathVariable Long linkId) {
        return userUrlHelper.deleteLink(userId, linkId);
    }
}