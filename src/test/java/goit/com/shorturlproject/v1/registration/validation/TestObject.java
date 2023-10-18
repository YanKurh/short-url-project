package goit.com.shorturlproject.v1.registration.validation;

public class TestObject {
    private final String password;
    private final String confirmPassword;

    public TestObject(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
