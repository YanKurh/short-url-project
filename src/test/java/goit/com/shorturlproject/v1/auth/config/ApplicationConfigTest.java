package goit.com.shorturlproject.v1.auth.config;

import goit.com.shorturlproject.v1.ITestContainer;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class ApplicationConfigTest implements ITestContainer {

    private ApplicationConfig applicationConfig;
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        applicationConfig = new ApplicationConfig(userRepository);
    }

    @Test
    public void testUserDetailsService() {
        String username = "testUser";
        User user = new User();
        user.setUserName(username);

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));

        UserDetailsService userDetailsService = applicationConfig.userDetailsService();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    }

    @Test
    public void testAuthenticationProvider() {
        AuthenticationProvider authenticationProvider = applicationConfig.authenticationProvider();

        // Define a sample user
        String username = "testUser";
        String password = "testPassword";
        User user = new User();
        user.setUserName(username);
        user.setPassword("encoded_password");

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = applicationConfig.passwordEncoder();

        String rawPassword = "testPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);
    }
}