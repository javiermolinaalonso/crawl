package com.marfeel.crawler.processor.document;

import com.marfeel.crawler.exceptions.MarfeelizableException;
import com.marfeel.crawler.processor.document.rules.MarfeelizableRule;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by javier on 15/08/15.
 */
public class DocumentProcessor {

    private final List<MarfeelizableRule<Document>> rules;

    public DocumentProcessor(List<MarfeelizableRule<Document>> rules) {
        this.rules = rules;
    }

    public boolean isMarfeelizable(Document document) throws MarfeelizableException {
        return rules.stream().anyMatch(x -> x.isMarfeelizable(document));
    }

}
