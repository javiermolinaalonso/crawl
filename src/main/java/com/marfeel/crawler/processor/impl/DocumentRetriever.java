package com.marfeel.crawler.processor.impl;

import com.marfeel.crawler.entities.DocumentResult;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.net.URI;
import java.util.concurrent.Callable;

/**
 * Created by javier on 15/08/15.
 */
public class DocumentRetriever implements Callable<DocumentResult> {

    private final URI uri;

    public DocumentRetriever(URI uri) {
        this.uri = uri;
    }

    @Override
    public DocumentResult call() throws Exception {
        Connection connect = Jsoup.connect(uri.toString());
        return new DocumentResult(uri, connect.get());
    }

}
