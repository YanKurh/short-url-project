package goit.com.shorturlproject.v1.url.service;

import goit.com.shorturlproject.v1.ITestContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ShortUrlGeneratorTest implements ITestContainer {

    @Mock
    private UrlService urlService;
    private ShortUrlGenerator shortUrlGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        shortUrlGenerator = new ShortUrlGenerator(urlService);
    }

    @Test
    void testUniqueKeyGeneration() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int numberOfKeysToGenerate = 100;
        Set<String> generatedKeys = new HashSet<>();
        for (int i = 0; i < numberOfKeysToGenerate; i++) {
            Method generateKeyMethod = ShortUrlGenerator.class.getDeclaredMethod("generateKey");
            generateKeyMethod.setAccessible(true);
            String generatedKey = (String) generateKeyMethod.invoke(shortUrlGenerator);

            assertFalse(generatedKeys.contains(generatedKey));

            generatedKeys.add(generatedKey);
        }
        assertEquals(numberOfKeysToGenerate, generatedKeys.size());
    }

    @Test
    void testLengthOfGeneratedKey() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method generateKeyMethod = ShortUrlGenerator.class.getDeclaredMethod("generateKey");
        generateKeyMethod.setAccessible(true);
        String generatedKey = (String) generateKeyMethod.invoke(shortUrlGenerator);
        assertEquals(8, generatedKey.length());
    }


}
