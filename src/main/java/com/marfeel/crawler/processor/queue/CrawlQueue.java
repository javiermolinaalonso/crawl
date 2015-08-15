package com.marfeel.crawler.processor.queue;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.entities.DocumentResult;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class CrawlQueue {

    private final ConcurrentLinkedDeque<URI> pendingUris;
    private final ConcurrentLinkedDeque<DocumentResult> documentResults;
    private final ConcurrentLinkedDeque<CrawlResult> crawlResults;

    public CrawlQueue() {
        pendingUris = new ConcurrentLinkedDeque<>();
        documentResults = new ConcurrentLinkedDeque<>();
        this.crawlResults = new ConcurrentLinkedDeque<>();
    }

    public void put(URI uri) {
        pendingUris.addLast(uri);
    }

    public URI popPendingUris() {
        return pendingUris.pop();
    }

    public boolean hasAnyUrl() {
        return !pendingUris.isEmpty();
    }

    public void put(DocumentResult documentResult) {
        documentResults.addLast(documentResult);
    }

    public boolean hasAnyDocument() {
        return !documentResults.isEmpty();
    }

    public void put(CrawlResult result) {
        crawlResults.addLast(result);
    }

    public boolean hasAnyCrawlResult() {
        return !crawlResults.isEmpty();
    }

    public CrawlResult popPendingCrawlResults() {
        return crawlResults.pop();
    }

    public DocumentResult popPendingDocuments() {
        return documentResults.pop();
    }
}
