package goit.com.shorturlproject.v1.registration.exception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ValidationExceptionHandlerTest {

    private ValidationExceptionHandler validationExceptionHandler;

    @BeforeEach
    void setUp() {
        validationExceptionHandler = new ValidationExceptionHandler();
    }

    @Test
    void testNotValid() throws MethodArgumentNotValidException {

        Object controller = new Object();
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(controller, "controller");

        ObjectError objectError = new ObjectError("objectName", "Global error message");
        bindingResult.addError(objectError);

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException((MethodParameter) null, bindingResult );

        ResponseEntity<?> response = validationExceptionHandler.notValid(ex, null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Map<String, List<String>> responseBody = (Map<String, List<String>>) response.getBody();
        List<String> errors = responseBody.get("errors");

        assertEquals(1, errors.size());
        assertEquals("Global error message", errors.get(0));
    }
}
