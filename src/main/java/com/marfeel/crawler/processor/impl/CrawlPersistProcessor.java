package com.marfeel.crawler.processor.impl;

import com.marfeel.crawler.persist.CrawlMongoRepository;
import com.marfeel.crawler.processor.impl.tasks.CrawlPersistTask;
import com.marfeel.crawler.processor.queue.CrawlQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Timer;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class CrawlPersistProcessor extends BaseProcessor<CrawlPersistTask> {

    private static final int DELAY = 10;

    @Autowired private CrawlMongoRepository crawlMongoRepository;

    @Autowired private CrawlQueue queue;

    public CrawlPersistProcessor() {
        super(new Timer("CrawlPersistProcessorTimer"));
    }

    @PostConstruct
    public void init() {
        init(new CrawlPersistTask(queue, crawlMongoRepository), DELAY);
    }

}
