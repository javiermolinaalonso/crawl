package com.marfeel.crawler;

import com.marfeel.crawler.processor.CrawlerInputExtractor;
import com.marfeel.crawler.processor.CrawlerProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/crawl", produces = "application/json; charset=utf8", consumes = "application/json")
public class CrawlerController {

    @Autowired private CrawlerProcessor crawlerProcessor;
    @Autowired private CrawlerInputExtractor crawlerInputExtractor;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> executeCrawls(@RequestBody ArrayList<Map<String, String>> request) {
        List<URI> uris = crawlerInputExtractor.extractUris(request);
        crawlerProcessor.bulkProcess(uris);
        return new ResponseEntity<String>("Hello", HttpStatus.CREATED);
	}


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> crawl() {
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }
}