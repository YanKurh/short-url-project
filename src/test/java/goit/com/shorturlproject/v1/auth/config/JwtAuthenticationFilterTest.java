package goit.com.shorturlproject.v1.auth.config;
import goit.com.shorturlproject.v1.ITestContainer;
import goit.com.shorturlproject.v1.auth.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.GrantedAuthority;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class JwtAuthenticationFilterTest implements ITestContainer {

    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private JwtService jwtService;
    private UserDetailsService userDetailsService;

    @BeforeEach
    public void setup() {
        jwtService = mock(JwtService.class);
        userDetailsService = mock(UserDetailsService.class);
        jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    @Test
    public void testDoFilterInternal_SuccessfulAuthentication() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();


        UserDetails userDetails = User.builder()
                .username("testUser")
                .password("password")
                .authorities(new GrantedAuthority[]{})
                .build();


        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = "your_mocked_jwt_token";


        when(jwtService.extractUsername(jwt)).thenReturn("testUser");


        when(userDetailsService.loadUserByUsername("testUser")).thenReturn(userDetails);


        when(jwtService.isTokenValid(jwt, userDetails)).thenReturn(true);


        jwtAuthenticationFilter.doFilterInternal(request, response, (req, res) -> {
        });


        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }
}
