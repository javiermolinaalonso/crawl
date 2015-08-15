package com.marfeel.crawler.entities;

import java.net.URI;

/**
 * Created by javier on 15/08/15.
 */
public class CrawlResult {

    private final URI uri;
    private final boolean marfeelizable;
    private final Exception e;

    public static CrawlResult marfeelizable(URI uri) {
        return new CrawlResult(uri, true, null);
    }

    public static CrawlResult nonMarfeelizable(URI uri) {
        return new CrawlResult(uri, false, null);
    }

    public static CrawlResult error(URI uri, Exception e) {
        return new CrawlResult(uri, false, e);
    }

    public CrawlResult(URI uri, boolean marfeelizable, Exception e) {
        this.uri = uri;
        this.marfeelizable = marfeelizable;
        this.e = e;
    }

    public URI getUri() {
        return uri;
    }

    public boolean isMarfeelizable() {
        return marfeelizable;
    }

    public Exception getE() {
        return e;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CrawlResult{");
        sb.append("uri=").append(uri);
        sb.append(", marfeelizable=").append(marfeelizable);
        sb.append(", e=").append(e);
        sb.append('}');
        return sb.toString();
    }
}
