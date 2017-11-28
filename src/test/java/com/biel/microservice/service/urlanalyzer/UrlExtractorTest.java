package com.biel.microservice.service.urlanalyzer;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UrlExtractorTest {

    @Test
    public void internalLinkTest() throws Exception {
        UrlExtractor urlExtractor = new UrlExtractor();
        List<String> internalLinks = urlExtractor.getInternalLinks("https://www.onet.pl/");
        Assert.assertEquals(internalLinks.size(),427);
    }
}