package com.marfeel.crawler.processor;

import com.marfeel.crawler.processor.impl.CrawlerInputExtractorImpl;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by javier on 15/08/15.
 */
public class CrawlerInputExtractorImplTest {

    private CrawlerInputExtractor crawlerInputExtractor;

    @Before
    public void setUp() throws Exception {
        crawlerInputExtractor = new CrawlerInputExtractorImpl();
    }

    @Test
    public void testGivenCorrectUriWhenExtractExpectConvertedUri() throws Exception {
        //Given
        List<Map<String, String>> input = new ArrayList<Map<String, String>>();
        input.add(getUriMap("google.es"));

        //When
        List<URI> uris = crawlerInputExtractor.extractUris(input);

        //Then
        URI expectedUri = new URI("google.es");
        assertEquals(uris.get(0), expectedUri);
    }

    @Test
    public void testMalformedUriWhenExtractExpectNoConversion() throws Exception {
        //Given
        List<Map<String, String>> input = new ArrayList<>();
        input.add(getUriMap("^invaliduricom"));

        //When
        List<URI> uris = crawlerInputExtractor.extractUris(input);

        //Then
        assertEquals(0, uris.size());
    }

    @Test
    public void testGivenTwoValidUrisAndOneInvalidWhenExtractExpectTwoFound() throws Exception {
        //Given
        List<Map<String, String>> input = new ArrayList<>();
        input.add(getUriMap("^invaliduricom"));
        input.add(getUriMap("google.com"));
        input.add(getUriMap("google.es"));

        //When
        List<URI> uris = crawlerInputExtractor.extractUris(input);

        //Then
        assertEquals(2, uris.size());
    }
    private Map<String, String> getUriMap(String value) {
        Map<String, String> correctUri = new HashMap<>();
        correctUri.put("url", value);
        return correctUri;
    }
}