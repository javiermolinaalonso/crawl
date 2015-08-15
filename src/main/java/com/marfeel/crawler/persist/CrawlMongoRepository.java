package com.marfeel.crawler.persist;

import com.marfeel.crawler.entities.CrawlResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class CrawlMongoRepository {

    @Autowired private MongoOperations operations;

    public void save(CrawlResult result) {
        operations.save(result);
    }

    public CrawlResult findByUri(URI uri) {
        return operations.findById(uri, CrawlResult.class);
    }
}
