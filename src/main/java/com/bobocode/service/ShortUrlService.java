package com.bobocode.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class ShortUrlService {

    private final ConcurrentMap<String, String> shortToInitialUrl = new ConcurrentHashMap<>();

    public String shortUrl(final String initialUrl) {
        if (StringUtils.isEmpty(initialUrl)) {
            throw new IllegalArgumentException("Provided initial url should not be empty!");
        }
        String shortenedUrl = StringUtils.randomAlphanumeric(6); //fwfv4D
        shortToInitialUrl.put(shortenedUrl, initialUrl);
        return shortenedUrl;
    }

    public String getInitialUrl(final String shortenedUrl) {
        if (StringUtils.isEmpty(shortenedUrl)) {
            throw new IllegalArgumentException("Provided shortened url should not be empty!");
        }
        return shortToInitialUrl.get(shortenedUrl);
    }
}
