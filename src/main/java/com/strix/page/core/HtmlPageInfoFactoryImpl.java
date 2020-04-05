package com.strix.page.core;

import com.strix.page.core.dto.PageLink;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class HtmlPageInfoFactoryImpl implements HtmlPageInfoFactory {

    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;

    @Autowired
    public HtmlPageInfoFactoryImpl(RestTemplate restTemplate, RetryTemplate retryTemplate) {
        this.restTemplate = restTemplate;
        this.retryTemplate = retryTemplate;
    }

    @Override
    public List<PageLink> getMainLinks(String url) {
        final Document document = getHtmlDocumentByUrl(url);
        return HtmlPageInformant.getLinks(document);
    }

    @Override
    public Map<String, List<String>> getTopics(String url) {
        final Document document = getHtmlDocumentByUrl(url);
        return HtmlPageInformant.getTopics(document);
    }

    private Document getHtmlDocumentByUrl(String url) {
        return retryTemplate.execute(context -> {
            final String html = restTemplate.getForObject(url, String.class);
            return HtmlPageParser.parse(html);
        });
    }

}
