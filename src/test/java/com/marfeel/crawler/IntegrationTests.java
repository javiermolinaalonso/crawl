package com.marfeel.crawler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class IntegrationTests {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGivenSomeUrlWhenCrawlExpectCorrectValues() throws Exception {
        String urls = readResource("testUrl.json");
        mockMvc.perform(post("/crawl").contentType(MediaType.APPLICATION_JSON).content(urls))
                .andExpect(status().isCreated());

        MvcResult elPaisResult =
                mockMvc
                        .perform(get(new URI("/crawl?uri=http://www.elpais.com")).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
        MvcResult techCrunchResult =
                mockMvc
                        .perform(get(new URI("/crawl?uri=http://techcrunch.com")).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        assertEquals("true", techCrunchResult.getResponse().getContentAsString());
        assertEquals("false", elPaisResult.getResponse().getContentAsString());
    }

    @Test
    public void testPerformance() throws Exception {
        String urls = readResource("heavyTestUrl.json");
        mockMvc.perform(post("/crawl").contentType(MediaType.APPLICATION_JSON).content(urls))
                .andExpect(status().isCreated());
    }
    private String readResource(String resourceName) throws IOException, URISyntaxException {
        InputStream resource = IntegrationTests.class.getClassLoader().getResourceAsStream(resourceName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource));
        StringBuilder sb = new StringBuilder();
        String currentline = bufferedReader.readLine();
        while (currentline != null) {
            sb.append(currentline);
            currentline = bufferedReader.readLine();
        }
        return sb.toString();
    }
}
