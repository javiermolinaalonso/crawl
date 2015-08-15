package com.marfeel.crawler.processor.impl;

import com.marfeel.crawler.processor.document.rules.MarfeelizableRule;
import com.marfeel.crawler.processor.impl.tasks.DocumentProcessTask;
import com.marfeel.crawler.processor.queue.CrawlQueue;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Timer;

/**
 * Created by javier on 15/08/15.
 */
public class DocumentProcessor extends BaseProcessor<DocumentProcessTask> {

    public static final int DELAY = 10;

    @Autowired
    CrawlQueue queue;

    private final List<MarfeelizableRule<Document>> rules;

    public DocumentProcessor(List<MarfeelizableRule<Document>> rules) {
        super(new Timer("DocumentProcessorTimer"));
        this.rules = rules;
    }

    @PostConstruct
    public void init() {
        init(new DocumentProcessTask(queue, rules), DELAY);
    }

}
