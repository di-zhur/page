package com.strix.page.core.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Component
public class HtmlInformant {

    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;

    @Autowired
    public HtmlInformant(RestTemplate restTemplate, RetryTemplate retryTemplate) {
        this.restTemplate = restTemplate;
        this.retryTemplate = retryTemplate;
    }

    public <T> T receive(String url, Function<Document,T> informationFunc) {
        final Document document = getHtmlDocumentByUrl(url);
        return informationFunc.apply(document);
    }

    private Document getHtmlDocumentByUrl(String url) {
        return retryTemplate.execute(context -> {
            final String html = restTemplate.getForObject(url, String.class);
            return parse(html);
        });
    }

    private static Document parse(String html) {
        Assert.isTrue(!StringUtils.isEmpty(html), "html can't be empty");
        return Jsoup.parse(html);
    }

}
