package com.marfeel.crawler.processor.impl.tasks;

import com.marfeel.crawler.entities.DocumentResult;
import com.marfeel.crawler.processor.queue.CrawlQueue;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URI;
import java.util.TimerTask;

/**
 * Created by javier on 15/08/15.
 */
public class DocumentRetrieverTask extends TimerTask {

    private final CrawlQueue queue;

    public DocumentRetrieverTask(CrawlQueue queue) {
        this.queue = queue;
    }

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
