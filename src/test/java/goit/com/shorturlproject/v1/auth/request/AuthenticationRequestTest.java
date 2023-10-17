package goit.com.shorturlproject.v1.auth.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationRequestTest {

    @Test
    void testEquals() {
        AuthenticationRequest request1 = AuthenticationRequest.builder()
                .userName("user1")
                .password("password1")
                .build();

        AuthenticationRequest request2 = AuthenticationRequest.builder()
                .userName("user1")
                .password("password1")
                .build();

        AuthenticationRequest request3 = AuthenticationRequest.builder()
                .userName("user2")
                .password("password2")
                .build();

        assertEquals(request1, request2);
        assertEquals(request2, request1);

        assertNotEquals(request1, request3);
        assertNotEquals(request3, request1);
    }

    @Test
    void testHashCode() {
        AuthenticationRequest request1 = AuthenticationRequest.builder()
                .userName("user1")
                .password("password1")
                .build();

        AuthenticationRequest request2 = AuthenticationRequest.builder()
                .userName("user1")
                .password("password1")
                .build();

        assertEquals(request1.hashCode(), request2.hashCode());
    }
}