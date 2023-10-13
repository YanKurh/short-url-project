package goit.com.shorturlproject.v1.auth.TockenGenerator;

import java.util.Map;

// This interface contains a single generateToken() method that accepts a User object.
public interface JwtGeneratorInterface {
    Map<String, String> generateToken(User user);

}
