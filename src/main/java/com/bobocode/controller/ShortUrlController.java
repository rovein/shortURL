package com.bobocode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ShortUrlController {

    // mvc
    @PostMapping
    public String createShortUrl() {
        return "mvc-view";
    }

    @GetMapping
    public void acceptShortUrl() {

    }
}
