package com.marfeel.crawler.processor.document.rules;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by javier on 15/08/15.
 */
public class JSoupTitleKeywordsMarfeelizableRule implements MarfeelizableRule<Document> {

    private final String[] keywords;

    public JSoupTitleKeywordsMarfeelizableRule(String... keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean isMarfeelizable(Document document) {
        try {
            Elements title = document.getElementsByTag("TITLE");
            String[] text = title.text().replaceAll("[^a-zA-Z ]", "").split(" ");
            for(String word : text) {
                for(String keyword : keywords) {
                    if(keyword.equalsIgnoreCase(word.trim())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

//    try {
//        Connection connect = Jsoup.connect(uri.toString());
//        Document document = connect.get();
//        Elements title = document.getElementsByTag("TITLE");
//
//        System.out.println(title);
//        if(title.contains("news") || title.contains("noticias")) {
//            result = CrawlResult.marfeelizable(uri);
//        } else {
//            result = CrawlResult.nonMarfeelizable(uri);
//        }
//    } catch (Exception e) {
//        result = CrawlResult.error(uri, e);
//    } finally {
//        callback.persist(result);
//    }
}
