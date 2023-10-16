package goit.com.shorturlproject.v1.url.controller;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("v1/")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping(value = "/{shortUrl}")
    @Operation(summary = "Redirect from short URL",
               description = "Redirects to the long URL associated with the provided short URL")
    public ModelAndView redirectFromShortUrl(@PathVariable String shortUrl) {
        UrlLink urlLinkByShortUrl = urlService.findUrlLinkByShortUrl(shortUrl);
        urlService.updateByClick(urlLinkByShortUrl);
        return new ModelAndView("redirect:" + urlLinkByShortUrl.getLongUrl());
    }
}

