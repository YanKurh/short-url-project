package goit.com.shorturlproject.v1.user.controller;

import goit.com.shorturlproject.v1.ITestContainer;
import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.service.UrlService;
import goit.com.shorturlproject.v1.user.dto.UrlLinkRequest;
import goit.com.shorturlproject.v1.user.dto.UrlLinkResponce;
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


    @BeforeEach
    void setUp() {
        userUrlHelper = mock(UserUrlHelper.class);
        userService = mock(UserService.class);
        userController = new UserController(userUrlHelper, userService);
    }

    @Test
    void testGetUserById_UserFound() {
        Long userId = 1L;
        User user = new User();

        when(userService.getUserByID(userId)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetUserById_UserNotFound() {
        Long userId = 2L;

        when(userService.getUserByID(userId)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateShortUrl() {
        UrlLinkRequest urlLinkRequest = new UrlLinkRequest();
        UrlLinkResponce urlLinkResponse = new UrlLinkResponce();

        when(userUrlHelper.createShortUrl(urlLinkRequest)).thenReturn(urlLinkResponse);

        UrlLinkResponce response = userController.createShortUrl(urlLinkRequest);

        assertEquals(urlLinkResponse, response);
    }

    @Test
    void testGetAllActiveLinks() {
        Long userId = 1L;
        Set<UrlLink> urlLinks = Set.of(new UrlLink());

        when(userUrlHelper.getAllActiveLinks(userId)).thenReturn(urlLinks);

        Set<UrlLink> response = userController.getAllActiveLinks(userId);

        assertEquals(urlLinks, response);
    }

    @Test
    void testGetAllLinks() {
        Long userId = 1L;
        Set<UrlLink> urlLinks = Set.of(new UrlLink());

        when(userUrlHelper.getAllLinks(userId)).thenReturn(urlLinks);

        Set<UrlLink> response = userController.getAllLinks(userId);

        assertEquals(urlLinks, response);
    }

    @Test
    void testDeleteLink_Successful() {
        Long userId = 1L;
        Long linkId = 1L;
        String successMessage = "Link deleted successfully";

        when(userUrlHelper.deleteLink(userId, linkId)).thenReturn(ResponseEntity.ok(successMessage));

        ResponseEntity<String> response = userController.deleteLink(userId, linkId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successMessage, response.getBody());
    }

    @Test
    void testDeleteLink_LinkNotExist() {
        Long userId = 1L;
        Long linkId = 1L;

        when(userUrlHelper.deleteLink(userId, linkId)).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<String> response = userController.deleteLink(userId, linkId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
