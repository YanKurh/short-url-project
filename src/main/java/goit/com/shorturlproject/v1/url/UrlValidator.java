package goit.com.shorturlproject.v1.url;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlValidator {

    public static boolean isValidURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
