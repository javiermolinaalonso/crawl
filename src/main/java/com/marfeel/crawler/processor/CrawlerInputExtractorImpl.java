package com.marfeel.crawler.processor;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class CrawlerInputExtractorImpl implements CrawlerInputExtractor {

    public static final String ACCEPTED_VALUES = "url";

    @Override
    public List<URI> extractUris(List<Map<String, String>> input) {
        return input.parallelStream()
                .map(x -> x.get(ACCEPTED_VALUES))
                .map(x -> {
                    URI uri = null;
                    try {
                        if (x != null) {
                            uri = new URI(x);
                        }
                    } catch (URISyntaxException e) {
                        //TODO Use logger
                        System.out.println(String.format("The uri " + x + " is not well formatted"));
                    }
                    return uri;
                })
                .filter(x -> x != null)
                .collect(Collectors.toList());
    }
}
