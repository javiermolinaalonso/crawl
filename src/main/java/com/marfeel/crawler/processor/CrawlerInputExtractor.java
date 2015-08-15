package com.marfeel.crawler.processor;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by javier on 15/08/15.
 */
public interface CrawlerInputExtractor {

    /**
     * This method will extract all the uri from the input received from the api
     * @param input A map that contain all the input uri to process
     * @return The correct and well formated uri
     */
    List<URI> extractUris(List<Map<String, String>> input);
}
