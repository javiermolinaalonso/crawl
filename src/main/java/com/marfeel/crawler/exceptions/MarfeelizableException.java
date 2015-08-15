package com.marfeel.crawler.exceptions;

import java.net.URI;

/**
 * Created by javier on 15/08/15.
 */
public class MarfeelizableException extends Exception {

    private final URI uri;

    public MarfeelizableException(URI uri, Exception e) {
        super(e);
        this.uri = uri;
    }

    public URI getUri() {
        return uri;
    }
}
