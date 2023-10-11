package goit.com.shorturlproject.v1.url.controller;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.service.UrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UrlControllerTest {

    private UrlService urlService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        urlService = mock(UrlService.class);
        UrlController urlController = new UrlController(urlService);
        mockMvc = MockMvcBuilders.standaloneSetup(urlController)
                .setViewResolvers(new InternalResourceViewResolver())
                .build();
    }

    @Test
    void testRedirectFromShortUrl() throws Exception {
        String shortUrl = "abc123";
        String longUrl = "https://www.example.com";

        UrlLink urlLink = new UrlLink();
        urlLink.setShortUrl(shortUrl);
        urlLink.setLongUrl(longUrl);

        when(urlService.findUrlLinkByShortUrl(shortUrl)).thenReturn(urlLink);

        mockMvc.perform(get("/v1/" + shortUrl))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(longUrl));

        verify(urlService, times(1)).updateByClick(urlLink);
    }
}
