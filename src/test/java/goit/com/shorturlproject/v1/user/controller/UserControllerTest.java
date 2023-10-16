package goit.com.shorturlproject.v1.user.controller;

import goit.com.shorturlproject.v1.ITestContainer;
import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.service.UrlService;
import goit.com.shorturlproject.v1.user.dto.UrlLinkRequest;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.service.UserService;
import goit.com.shorturlproject.v1.user.service.UserUrlHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest implements ITestContainer {

    @Mock
    private UserUrlHelper userUrlHelper;

    @Mock
    private UserService userService;

    @Mock
    private UrlService urlService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testCreateShortUrl() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setLogin("TestUser");

        when(userUrlHelper.getUserById(anyLong())).thenReturn(user);

        UrlLink urlLink = new UrlLink();
        urlLink.setUser(user);
        urlLink.setShortUrl("shortUrl");
        urlLink.setLongUrl("longUrl");

        when(userUrlHelper.createShortUrlForUser(anyString(), any(User.class))).thenReturn(urlLink);

        UrlLinkRequest urlLinkRequest = new UrlLinkRequest();
        urlLinkRequest.setUserId(1L);
        urlLinkRequest.setLongUrl("http://example.com");

        mockMvc.perform(post("/v1/auth/user/createShortUrl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": 1, \"longUrl\": \"http://example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.shortUrl").value("shortUrl"))
                .andExpect(jsonPath("$.longUrl").value("longUrl"))
                .andExpect(jsonPath("$.username").value("TestUser"));
    }

    @Test
    void getUserById_UserExists() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userService.getUserByID(userId)).thenReturn(Optional.of(user));
        ResponseEntity<User> response = userController.getUserById(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());

        verify(userService, times(1)).getUserByID(userId);
    }

    @Test
    void getUserById_UserNotExists() {
        Long userId = 1L;

        when(userService.getUserByID(userId)).thenReturn(Optional.empty());
        ResponseEntity<User> response = userController.getUserById(userId);

        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(userService, times(1)).getUserByID(userId);
    }

//    @Test
//    void getAllActiveLinks_ReturnsActiveSetUrlLinks() {
//        Long userId = 1L;
//        User user = new User();
//        user.setId(userId);
//
//        LocalDateTime dateTime = LocalDateTime.now();
//        Set<UrlLink> links = Set.of(
//                new UrlLink(1L, "http://example.com/1", "short1", dateTime, 0, dateTime.plusDays(1), user),
//                new UrlLink(2L, "http://example.com/2", "short2", dateTime, 0, dateTime.minusDays(1), user),
//                new UrlLink(3L, "http://example.com/3", "short3", dateTime, 0, dateTime.plusDays(2), user)
//        );
//
//        when(urlService.getAllLinksFromRedis()).thenReturn(links);
//
//        Set<UrlLink> result = userController.getAllActiveLinks(userId);
//
//        Set<UrlLink> activeLinks = links.stream()
//                .filter(link -> dateTime.isBefore(link.getExpirationDate()))
//                .collect(Collectors.toSet());
//
//        assertEquals(activeLinks, result);
//    }


    @Test
    void getAllLinks() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        LocalDateTime dateTime = LocalDateTime.now();
        Set<UrlLink> links = Set.of(
                new UrlLink(1L, "http://example.com/1", "short1",
                        dateTime, 0, dateTime.plusDays(1), user),
                new UrlLink(2L, "http://example.com/2", "short2",
                        dateTime, 0, dateTime.minusDays(1), user),
                new UrlLink(3L, "http://example.com/3", "short3",
                        dateTime, 0, dateTime.plusDays(2), user)
        );

        when(urlService.findAllShortLinksByUserID(userId)).thenReturn(links);

        Set<UrlLink> allLinks = userController.getAllLinks(userId);

        assertEquals(allLinks, links);
    }
}
