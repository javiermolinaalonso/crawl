package com.marfeel.crawler.processor.impl;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.entities.DocumentResult;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.LinkedList;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class CrawlQueue {

    private final LinkedList<URI> pendingUris;
    private final LinkedList<DocumentResult> documentResults;
    private final LinkedList<CrawlResult> crawlResults;

    public CrawlQueue() {
        pendingUris = new LinkedList<>();
        documentResults = new LinkedList<>();
        this.crawlResults = new LinkedList<>();
    }

    public void put(URI uri) {
        pendingUris.addLast(uri);
    }

    public LinkedList<URI> getPendingUris() {
        return pendingUris;
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

    public LinkedList<DocumentResult> getPendingDocuments() {
        return documentResults;
    }

    public void put(CrawlResult result) {
        crawlResults.addLast(result);
    }

    public boolean hasAnyCrawlResult() {
        return !crawlResults.isEmpty();
    }


    public LinkedList<CrawlResult> getPendingCrawlResults() {
        return crawlResults;
    }
}
