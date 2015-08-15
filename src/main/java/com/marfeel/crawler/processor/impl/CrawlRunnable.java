package com.marfeel.crawler.processor.impl;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.persist.CrawlMongoRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URI;

/**
 * Created by javier on 15/08/15.
 */
public class CrawlRunnable implements Runnable {

    private final URI uri;
    private final CrawlMongoRepository callback;

    public CrawlRunnable(URI uri, CrawlMongoRepository callback) {
        this.uri = uri;
        this.callback = callback;
    }

    @Override
    public void run() {
        CrawlResult result = null;
        try {
            Connection connect = Jsoup.connect(uri.toString());
            Document document = connect.get();

        } catch (Exception e) {
            result = CrawlResult.error(uri, e);
        } finally {
            callback.save(result);
        }
    }

}
