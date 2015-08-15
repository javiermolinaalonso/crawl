package com.marfeel.crawler.processor.impl;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.entities.DocumentResult;
import com.marfeel.crawler.processor.document.rules.MarfeelizableRule;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by javier on 15/08/15.
 */
public class DocumentProcessor {

    public static final int DELAY = 500;

    @Autowired CrawlQueue queue;

    private final List<MarfeelizableRule<Document>> rules;
    private Timer timer;

    public DocumentProcessor(List<MarfeelizableRule<Document>> rules) {
        this.rules = rules;
        this.timer = new Timer("DocumentProcessorTimer");
    }

    @PostConstruct
    public void init() {
        timer.schedule(new DocumentProcessTask(), DELAY);
    }

    private class DocumentProcessTask extends TimerTask {

        @Override
        public void run() {
            while (queue.hasAnyDocument()) {
                DocumentResult document = queue.getPendingDocuments().pop();
                CrawlResult result;
                if(document == null) {
                    //There is an error
                    result = CrawlResult.error(document.getUri(), document.getE());
                } else {
                    if(isMarfeelizable(document.getDocument())) {
                        result =  CrawlResult.marfeelizable(document.getUri());
                    } else {
                        result = CrawlResult.nonMarfeelizable(document.getUri());
                    }
                }
                queue.put(result);
            }
        }

        private boolean isMarfeelizable(Document document) {
            return rules.stream().anyMatch(x -> x.isMarfeelizable(document));
        }
    }
}
