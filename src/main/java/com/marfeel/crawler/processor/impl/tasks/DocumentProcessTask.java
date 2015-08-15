package com.marfeel.crawler.processor.impl.tasks;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.entities.DocumentResult;
import com.marfeel.crawler.processor.document.rules.MarfeelizableRule;
import com.marfeel.crawler.processor.queue.CrawlQueue;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by javier on 15/08/15.
 */
public class DocumentProcessTask extends CrawlTask<DocumentResult> {

    private final List<MarfeelizableRule<Document>> rules;

    public DocumentProcessTask(CrawlQueue queue, List<MarfeelizableRule<Document>> rules) {
        super(queue);
        this.rules = rules;
    }

    @Override
    protected DocumentResult getSomethingToExecute() {
        return queue.popPendingDocuments();
    }

    @Override
    protected boolean hasSomethingToExecute() {
        return queue.hasAnyDocument();
    }

    @Override
    protected void doWork(DocumentResult document) {
        CrawlResult result;
        if (document.getDocument() == null) {
            //There is an error
            result = CrawlResult.error(document.getUri(), document.getE());
        } else {
            if (isMarfeelizable(document.getDocument())) {
                result = CrawlResult.marfeelizable(document.getUri());
            } else {
                result = CrawlResult.nonMarfeelizable(document.getUri());
            }
        }
        queue.put(result);
    }

    private boolean isMarfeelizable(Document document) {
        return rules.stream().anyMatch(x -> x.isMarfeelizable(document));
    }
}
