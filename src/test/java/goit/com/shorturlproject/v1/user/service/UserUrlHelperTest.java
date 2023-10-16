package goit.com.shorturlproject.v1.user.service;

import goit.com.shorturlproject.v1.url.service.UrlShortener;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class UserUrlHelperTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserUrlHelper userUrlHelper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById_UserFound() {

        long userId = 1L;
        User expectedUser = new User();
        expectedUser.setId(userId);
        expectedUser.setUserName("TestUser");

        when(userService.findById(anyLong())).thenReturn(Optional.of(expectedUser));


        User actualUser = userUrlHelper.getUserById(userId);


        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testGetUserById_UserNotFound() {

        Long userId = 1L;

        when(userService.findById(anyLong())).thenReturn(Optional.empty());


        try {
            userUrlHelper.getUserById(userId);
        } catch (UserNotFoundException e) {
            assertEquals("User not found with id 1", e.getMessage());
        }
    }
}
