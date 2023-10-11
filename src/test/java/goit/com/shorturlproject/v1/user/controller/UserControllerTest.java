package goit.com.shorturlproject.v1.user.controller;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.user.dto.UrlLinkRequest;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.service.UserUrlHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    @Mock
    private UserUrlHelper userUrlHelper;

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
}
