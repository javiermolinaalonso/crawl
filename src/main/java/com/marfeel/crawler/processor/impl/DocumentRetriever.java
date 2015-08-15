package com.marfeel.crawler.processor.impl;

import com.marfeel.crawler.entities.DocumentResult;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class DocumentRetriever {

    public static final int PERIOD = 100;

    @Autowired CrawlQueue queue;

    private Timer timer;

    public DocumentRetriever() {
        this.timer = new Timer("DocumentRetrieverTimer");
    }

    @PostConstruct
    public void init() throws Exception {
        timer.schedule(new DocumentRetrieverTask(), 0, PERIOD);
    }

    private class DocumentRetrieverTask extends TimerTask {

        @Override
        public void run() {
            while (queue.hasAnyUrl()) {
                URI uri = queue.getPendingUris().pop();
                Connection connect = Jsoup.connect(uri.toString());
                try {
                    queue.put(new DocumentResult(uri, connect.get()));
                } catch (IOException e) {
                    queue.put(new DocumentResult(uri, e));
                }
            }
        }
    }
}
