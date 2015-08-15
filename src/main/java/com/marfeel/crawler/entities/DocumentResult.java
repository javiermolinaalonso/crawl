package com.marfeel.crawler.entities;

import org.jsoup.nodes.Document;

import java.net.URI;

/**
 * Created by javier on 15/08/15.
 */
public class DocumentResult {

    private final URI uri;
    private final Document document;
    private final Exception e;

    public DocumentResult(URI uri, Document document) {
        this.uri = uri;
        this.document = document;
        this.e = null;
    }

    public DocumentResult(URI uri, Exception e) {
        this.uri = uri;
        this.e = e;
        this.document = null;
    }

    public URI getUri() {
        return uri;
    }

    public Document getDocument() {
        return document;
    }

    public Exception getE() {
        return e;
    }
}
