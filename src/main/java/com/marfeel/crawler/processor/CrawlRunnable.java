package com.marfeel.crawler.processor;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.persist.CrawlRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URI;

/**
 * Created by javier on 15/08/15.
 */
public class CrawlRunnable implements Runnable {

    private final URI uri;
    private final CrawlRepository callback;

    public CrawlRunnable(URI uri, CrawlRepository callback) {
        this.uri = uri;
        this.callback = callback;
    }

    @Override
    public void run() {
        CrawlResult result = null;
        try {
            Connection connect = Jsoup.connect(uri.toString());
            Document document = connect.get();
            Elements title = document.getElementsByTag("TITLE");

            System.out.println(title);
            if(title.contains("news") || title.contains("noticias")) {
                result = CrawlResult.marfeelizable(uri);
            } else {
                result = CrawlResult.nonMarfeelizable(uri);
            }
        } catch (Exception e) {
            result = CrawlResult.error(uri, e);
        } finally {
            callback.persist(result);
        }
    }

}
