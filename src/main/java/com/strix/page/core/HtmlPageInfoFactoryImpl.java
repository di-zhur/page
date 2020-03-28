package com.strix.page.core;

import com.strix.page.core.dto.PageLink;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class HtmlPageInfoFactoryImpl implements HtmlPageInfoFactory {

    private final RestTemplate restTemplate;

    @Autowired
    public HtmlPageInfoFactoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
        final String html = restTemplate.getForObject(url, String.class);
        return HtmlPageParser.parse(html);
    }

}
