package com.marfeel.crawler.processor;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.processor.post.CrawlerCallback;

import java.net.URI;
import java.util.concurrent.Future;

/**
 * Created by javier on 15/08/15.
 */
public interface SingleCrawlerProcessor {

    /**
     * This method process one URI and check if it is valid for marfeel site
     * @param uri
     * @param crawlerProcessor
     * @return The result of the uri containing if it is marfeelable or not
     */
    Future<CrawlResult> processUri(URI uri, CrawlerCallback crawlerProcessor);
}
