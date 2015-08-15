package com.marfeel.crawler.processor.document.rules;

/**
 * Created by javier on 15/08/15.
 */
public interface MarfeelizableRule<T> {

    boolean isMarfeelizable(T document);

}
