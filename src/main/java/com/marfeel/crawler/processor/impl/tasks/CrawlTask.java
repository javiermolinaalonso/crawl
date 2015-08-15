package com.marfeel.crawler.processor.impl.tasks;

import com.marfeel.crawler.processor.queue.CrawlQueue;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by javier on 15/08/15.
 */
public abstract class CrawlTask<T> extends TimerTask {

    protected final CrawlQueue queue;

    public CrawlTask(CrawlQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        if(hasSomethingToExecute()) {
            ExecutorService executorService = Executors.newFixedThreadPool(4);
            while(hasSomethingToExecute()) {
                T object = getSomethingToExecute();
                executorService.submit(() -> {
                    doWork(object);
                });
            }
            executorService.shutdown();
        }
    }

    protected abstract void doWork(T object);

    protected abstract T getSomethingToExecute();

    protected abstract boolean hasSomethingToExecute();


}
