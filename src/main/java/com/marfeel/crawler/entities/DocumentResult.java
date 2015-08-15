package com.marfeel.crawler.entities;

import org.jsoup.nodes.Document;

import java.net.URI;

/**
 * Created by javier on 15/08/15.
 */
public class DocumentResult {

    private final URI uri;
    private final Document document;

    public DocumentResult(URI uri, Document document) {
        this.uri = uri;
        this.document = document;
    }

    public URI getUri() {
        return uri;
    }

    public Document getDocument() {
        return document;
    }
}
