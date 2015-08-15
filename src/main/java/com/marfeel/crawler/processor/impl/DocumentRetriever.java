package com.marfeel.crawler.processor.impl;

import com.marfeel.crawler.processor.impl.tasks.DocumentRetrieverTask;
import com.marfeel.crawler.processor.queue.CrawlQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Timer;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class DocumentRetriever extends BaseProcessor<DocumentRetrieverTask> {

    public static final int PERIOD = 1000;

    @Autowired
    CrawlQueue queue;

    public DocumentRetriever() {
        super(new Timer("DocumentRetrieverTimer"));
    }

    @PostConstruct
    public void init() throws Exception {
        init(new DocumentRetrieverTask(queue), PERIOD);
    }

}
