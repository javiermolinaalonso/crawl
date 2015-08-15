package com.marfeel.crawler.processor;

import com.marfeel.crawler.entities.CrawlResult;
import com.marfeel.crawler.entities.DocumentResult;
import com.marfeel.crawler.persist.CrawlRepository;
import com.marfeel.crawler.processor.document.DocumentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by javier on 15/08/15.
 */
@Component
public class CrawlerProcessorImpl implements CrawlerProcessor {

    //TODO Externalise this to configuration file
    public static final int N_THREADS = 4;

    @Autowired private CrawlRepository crawlRepository;
    @Autowired private DocumentProcessor documentProcessor;

    private ExecutorService executorService;

    public CrawlerProcessorImpl() {
        this.executorService = Executors.newFixedThreadPool(N_THREADS);
    }

    @PreDestroy
    public void tearDown() {
        System.out.println("killing executor service");
        executorService.shutdown();
    }

    @Override
    public void bulkProcess(List<URI> urls) {
//        urls.forEach(x -> executorService.submit(new CrawlRunnable(x, crawlRepository)));
        List<DocumentRetriever> documentRetrieverList = urls.parallelStream().map(DocumentRetriever::new).collect(Collectors.toList());

        List<Future<DocumentResult>> futureDocument = new LinkedList<>();
        documentRetrieverList.forEach(x -> futureDocument.add(executorService.submit(x)));

        for (Future<DocumentResult> doc : futureDocument) {
            CrawlResult result = null;
            try {
                DocumentResult documentResult = doc.get();
                if(documentProcessor.isMarfeelizable(documentResult.getDocument())) {
                    result = CrawlResult.marfeelizable(documentResult.getUri());
                } else {
                    result = CrawlResult.nonMarfeelizable(documentResult.getUri());
                }
            } catch (Exception e) {
                //Error here
                result = CrawlResult.error(null, e);
            } finally {
                if(result != null) {
                    crawlRepository.persist(result);
                }
            }
        }
    }

}
