package com.marfeel.crawler;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.persist.CrawlMongoRepository;
import com.marfeel.crawler.processor.CrawlerInputExtractor;
import com.marfeel.crawler.processor.CrawlerProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/crawl", produces = "application/json; charset=utf8", consumes = "application/json")
public class CrawlerController {

    @Autowired private CrawlerProcessor crawlerProcessor;
    @Autowired private CrawlerInputExtractor crawlerInputExtractor;

    //THIS shouldn't be in this layer, it is here to simplify the program!
    @Autowired private CrawlMongoRepository repository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> executeCrawls(@RequestBody ArrayList<Map<String, String>> request) {
        //This is done blocking because it should be fast..
        List<URI> uris = crawlerInputExtractor.extractUris(request);
        crawlerProcessor.bulkProcess(uris);
        return new ResponseEntity<>(HttpStatus.CREATED);
	}


    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> crawl(@RequestParam("uri") String uri) throws URISyntaxException {
        CrawlResult result = repository.findByUri(new URI(uri));
        return new ResponseEntity<String>(String.valueOf(result.isMarfeelizable()), HttpStatus.OK);
    }
}