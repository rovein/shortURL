package com.bobocode.controller;

import com.bobocode.dto.CreateShortUrlRequest;
import com.bobocode.service.ShortUrlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;

@Controller
@AllArgsConstructor
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("request", new CreateShortUrlRequest(""));
        return "index";
    }

    @PostMapping("/short-url")
    public String createShortUrl(@ModelAttribute("request") CreateShortUrlRequest shortUrlRequest,
                                 HttpServletRequest request,
                                 Model model) {
        String shortUrlId = shortUrlService.shortUrl(shortUrlRequest.initialUrl());
        String requestUrl = request.getRequestURL().toString();
        String url = requestUrl.substring(0, requestUrl.lastIndexOf("/")) + "/" + shortUrlId;
        model.addAttribute("shortUrl", url);
        return "index";
    }

    @GetMapping("/{url}")
    public ResponseEntity<?> acceptShortUrl(@PathVariable("url") String shortenedUrl, HttpServletRequest request) {
        var originalUrl = this.shortUrlService.getInitialUrl(shortenedUrl);
        if (!originalUrl.startsWith("http://")) {
            originalUrl = "https://" + originalUrl;
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(301))
                .location(URI.create(originalUrl))
                .build();
    }
}
