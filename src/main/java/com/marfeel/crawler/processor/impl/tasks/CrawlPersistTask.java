package com.marfeel.crawler.processor.impl.tasks;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.persist.CrawlMongoRepository;
import com.marfeel.crawler.processor.queue.CrawlQueue;

import java.util.TimerTask;

/**
 * Created by javier on 15/08/15.
 */
public class CrawlPersistTask extends TimerTask {

    private final CrawlQueue queue;
    private final CrawlMongoRepository crawlMongoRepository;

    public CrawlPersistTask(CrawlQueue queue, CrawlMongoRepository crawlMongoRepository) {
        this.queue = queue;
        this.crawlMongoRepository = crawlMongoRepository;
    }

    @Override
    public void run() {
        while (queue.hasAnyCrawlResult()) {
            CrawlResult crawlResult = queue.getPendingCrawlResults().pop();
            System.out.println("Processing" + crawlResult);
            crawlMongoRepository.save(crawlResult);
        }
    }
}
