package goit.com.shorturlproject.v1.TockenGenerator;

import goit.com.shorturlproject.v1.user.User;

import java.util.Map;

// This interface contains a single generateToken() method that accepts a User object.
public interface JwtGeneratorInterface {
    Map<String, String> generateToken(User user);

}
