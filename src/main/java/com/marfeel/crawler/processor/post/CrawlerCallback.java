package com.marfeel.crawler.processor.post;

import com.marfeel.crawler.entities.CrawlResult;

import java.net.URI;

/**
 * Created by javier on 15/08/15.
 */
public class CrawlerCallback {

    public CrawlerCallback() {
    }

    public void call(CrawlResult r) {
        System.out.println(r);
    }

    public void error(URI uri, Exception e) {

    }
}
