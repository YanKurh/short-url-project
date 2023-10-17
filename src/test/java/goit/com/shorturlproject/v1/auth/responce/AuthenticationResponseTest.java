package goit.com.shorturlproject.v1.auth.responce;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthenticationResponseTest {

    @Test
    public void testAuthenticationResponseWithToken() {
        AuthenticationResponse response = new AuthenticationResponse("myToken", null);

        assertEquals("myToken", response.getToken());
        assertNull(response.getError());
    }

    @Test
    public void testAuthenticationResponseWithError() {
        AuthenticationResponse response = new AuthenticationResponse(null, "Authentication failed");

        assertNull(response.getToken());
        assertEquals("Authentication failed", response.getError());
    }

    @Test
    public void testAuthenticationResponseWithTokenAndError() {
        AuthenticationResponse response = new AuthenticationResponse("myToken", "Authentication failed");

        assertEquals("myToken", response.getToken());
        assertEquals("Authentication failed", response.getError());
    }
}
