package com.marfeel.crawler.processor.impl;

import com.marfeel.crawler.processor.CrawlerProcessor;
import com.marfeel.crawler.processor.queue.CrawlQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.URI;
import java.util.List;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class CrawlerProcessorImpl implements CrawlerProcessor {

    @Autowired private CrawlQueue queue;

    public CrawlerProcessorImpl() {

    }

    @PreDestroy
    public void tearDown() {
    }

    @Override
    public void bulkProcess(List<URI> urls) {
        //The process should be
        //Parse an uri and put into a queue
        //Some object pull that object and retrieve the document
        //Another actor pull the document and retrieve if it is marfeelizable or not
        //Another actor persist the result
        urls.forEach(x -> queue.put(x));
    }

}
