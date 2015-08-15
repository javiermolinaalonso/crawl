package com.marfeel.config;

import com.marfeel.crawler.processor.impl.DocumentProcessor;
import com.marfeel.crawler.processor.document.rules.JSoupTitleKeywordsMarfeelizableRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by javier on 15/08/15.
 */
@Configuration
public class RulesConfig {

    @Bean
    public JSoupTitleKeywordsMarfeelizableRule jSoupTitleKeywordsMarfeelizableRule() {
        return new JSoupTitleKeywordsMarfeelizableRule("news", "noticias");
    }

    @Bean
    public DocumentProcessor documentProcessor() {
        return new DocumentProcessor(Arrays.asList(jSoupTitleKeywordsMarfeelizableRule()));
    }
}
