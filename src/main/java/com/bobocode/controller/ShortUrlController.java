package com.bobocode.controller;

import com.bobocode.dto.CreateShortUrlRequest;
import com.bobocode.service.ShortUrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/short-url")
    public ResponseEntity<String> createShortUrl(@ModelAttribute("request") CreateShortUrlRequest request) {
        return ResponseEntity.ok(shortUrlService.shortUrl(request.initialUrl()));
    }

    @GetMapping("/{url}") //localhost:8080/fwfv4D
    public void acceptShortUrl(@PathVariable("url") String shortenedUrl, HttpServletResponse response) throws IOException {
        var originalUrl = this.shortUrlService.getInitialUrl(shortenedUrl);
        response.sendRedirect(originalUrl);
    }
}
