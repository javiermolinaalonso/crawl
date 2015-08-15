package com.marfeel.crawler.processor.impl.tasks;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.persist.CrawlMongoRepository;
import com.marfeel.crawler.processor.queue.CrawlQueue;

/**
 * Created by javier on 15/08/15.
 */
public class CrawlPersistTask extends CrawlTask<CrawlResult> {

    private final CrawlMongoRepository crawlMongoRepository;

    public CrawlPersistTask(CrawlQueue queue, CrawlMongoRepository crawlMongoRepository) {
        super(queue);
        this.crawlMongoRepository = crawlMongoRepository;
    }

    @Override
    protected boolean hasSomethingToExecute() {
        return queue.hasAnyCrawlResult();
    }

    @Override
    protected CrawlResult getSomethingToExecute() {
        return queue.popPendingCrawlResults();
    }

    @Override
    protected void doWork(CrawlResult crawlResult) {
        crawlMongoRepository.save(crawlResult);
    }
}
