package com.marfeel.crawler.processor.impl.tasks;

import com.marfeel.crawler.entities.DocumentResult;
import com.marfeel.crawler.processor.queue.CrawlQueue;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URI;

/**
 * Created by javier on 15/08/15.
 */
public class DocumentRetrieverTask extends CrawlTask<URI> {

    public DocumentRetrieverTask(CrawlQueue queue) {
        super(queue);
    }

    @Override
    protected void doWork(URI uri) {
        Connection connect = Jsoup.connect(uri.toString());
        try {
            queue.put(new DocumentResult(uri, connect.get()));
        } catch (IOException e) {
            queue.put(new DocumentResult(uri, null, e));
        }
    }

    @Override
    protected URI getSomethingToExecute() {
        return queue.popPendingUris();
    }

    @Override
    protected boolean hasSomethingToExecute() {
        return queue.hasAnyUrl();
    }
}
