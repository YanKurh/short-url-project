package goit.com.shorturlproject.v1.url.controller;

import goit.com.shorturlproject.v1.url.dto.UrlLink;
import goit.com.shorturlproject.v1.url.service.UrlService;
import goit.com.shorturlproject.v1.url.service.UrlShortener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping("/{shortUrl}")
    public ModelAndView redirectFromShortUrl(@PathVariable String shortUrl) {
        UrlLink urlLinkByShortUrl = urlService.findUrlLinkByShortUrl(shortUrl);
        urlService.updateByClick(urlLinkByShortUrl);
        return new ModelAndView("redirect:" + urlLinkByShortUrl.getLongUrl());
    }
}

