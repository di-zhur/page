package com.strix.page.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strix.page.core.HtmlPageInfoFactory;
import com.strix.page.core.dto.PageLink;
import com.strix.page.db.entity.LinkEntity;
import com.strix.page.db.entity.PageEntity;
import com.strix.page.db.repository.LinkRepository;
import com.strix.page.db.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HtmlPageInfoBlServiceImpl implements HtmlPageInfoBlService {

    private final HtmlPageInfoFactory htmlPageInfoFactory;
    private final PageRepository pageRepository;
    private final LinkRepository linkRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public HtmlPageInfoBlServiceImpl(HtmlPageInfoFactory htmlPageInfoFactory, PageRepository pageRepository, LinkRepository linkRepository, ObjectMapper objectMapper) {
        this.htmlPageInfoFactory = htmlPageInfoFactory;
        this.pageRepository = pageRepository;
        this.linkRepository = linkRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<PageLink> getMainLinks(String url) {
        List<PageLink> pageLinks = htmlPageInfoFactory.getMainLinks(url);
        saveLinks(url, pageLinks);
        return pageLinks;
    }

    private void saveLinks(String url, List<PageLink> pageLinks) {
        final PageEntity pageEntity = savePage(url);
        final List<LinkEntity> linkEntities = new ArrayList<>();
        pageLinks.forEach(it -> {
            final LinkEntity linkEntity = new LinkEntity();
            linkEntity.setId(UUID.randomUUID());
            linkEntity.setPageId(pageEntity.getId());
            try {
                linkEntity.setValue(objectMapper.writeValueAsString(it));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            linkEntities.add(linkEntity);
        });
        linkRepository.saveAll(linkEntities);
    }

    private PageEntity savePage(String url) {
        final PageEntity pageEntity = new PageEntity();
        pageEntity.setId(UUID.randomUUID());
        pageEntity.setUrl(url);
        return pageRepository.save(pageEntity);
    }

}
