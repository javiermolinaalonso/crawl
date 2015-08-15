package com.marfeel.crawler.processor.impl;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.persist.CrawlMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class CrawlPersistProcessor {

    private static final long DELAY = 500;

    @Autowired
    CrawlMongoRepository crawlMongoRepository;

    @Autowired private CrawlQueue queue;
    private Timer timer;

    public CrawlPersistProcessor() {
        this.timer = new Timer("CrawlPersistProcessorTimer");
    }

    @PostConstruct
    public void init() {
        timer.schedule(new CrawlPersistTask(), DELAY);
    }


    private class CrawlPersistTask extends TimerTask {

        @Override
        public void run() {
            while (queue.hasAnyCrawlResult()) {
                CrawlResult crawlResult = queue.getPendingCrawlResults().pop();
                crawlMongoRepository.save(crawlResult);
            }
        }
    }
}
