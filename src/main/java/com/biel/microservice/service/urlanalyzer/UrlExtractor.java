package com.biel.microservice.service.urlanalyzer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UrlExtractor {

    private static final Logger log = LoggerFactory.getLogger(UrlExtractor.class);

    public List<String> getInternalLinks(String url) {
        log.info("Starting getting links from " + url);
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a");
            return links.stream().map((link) -> link.attr("abs:href")).collect(Collectors.toList());
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        return null;
    }
}
