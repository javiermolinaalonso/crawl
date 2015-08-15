package com.marfeel.crawler.persist;

import com.marfeel.crawler.entities.CrawlResult;
import org.springframework.stereotype.Component;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class CrawlRepository {

    public void persist(CrawlResult result) {
        System.out.println(result);
    }

}
