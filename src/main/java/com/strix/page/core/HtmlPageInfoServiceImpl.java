package com.strix.page.core;

import com.strix.page.core.dto.PageLink;
import com.strix.page.core.dto.Topics;
import com.strix.page.db.service.HtmlPageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HtmlPageInfoServiceImpl implements HtmlPageInfoService {

    private final HtmlPageInfoFactory htmlPageInfoFactory;
    private final HtmlPageStorageService htmlPageStorageService;

    @Autowired
    public HtmlPageInfoServiceImpl(HtmlPageInfoFactory htmlPageInfoFactory, HtmlPageStorageService htmlPageStorageService) {
        this.htmlPageInfoFactory = htmlPageInfoFactory;
        this.htmlPageStorageService = htmlPageStorageService;
    }

    @Override
    public List<PageLink> getMainLinks(String url) {
        List<PageLink> pageLinks = htmlPageInfoFactory.getMainLinks(url);
        htmlPageStorageService.saveLinks(url, pageLinks);
        return pageLinks;
    }

    @Override
    public List<Topics> getTopics(String url) {
        Map<String, List<String>> topics = htmlPageInfoFactory.getTopics(url);
        htmlPageStorageService.saveTopics(url, topics);
        return topics.entrySet().stream()
                .map(it -> new Topics(it.getKey(), it.getValue()))
                .collect(Collectors.toList());
    }

}
