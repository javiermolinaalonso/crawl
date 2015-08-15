package com.marfeel.crawler.processor;

import com.marfeel.crawler.persist.CrawlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class CrawlerProcessorImpl implements CrawlerProcessor {

    //TODO Externalise this to configuration file
    public static final int N_THREADS = 4;

    @Autowired private CrawlRepository crawlRepository;

    private ExecutorService executorService;

    public CrawlerProcessorImpl() {
        this.executorService = Executors.newFixedThreadPool(N_THREADS);
    }

    @Override
    public void bulkProcess(List<URI> urls) {
        urls.forEach(x -> executorService.submit(new CrawlRunnable(x, crawlRepository)));
    }

}
