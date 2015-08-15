package com.marfeel.crawler.processor;

import java.net.URI;
import java.util.List;

/**
 * Created by javier on 15/08/15.
 */
public interface CrawlerProcessor {

    /**
     * Process a list of url to check if they are "Crawleable" or not
     * @param urls A list of url to check crawls
     * @return The amount of correct url and sent to process
     */
    void bulkProcess(List<URI> urls);

}
