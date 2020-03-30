package com.strix.page.db.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strix.page.core.dto.PageLink;
import com.strix.page.db.entity.LinkEntity;
import com.strix.page.db.entity.PageEntity;
import com.strix.page.db.repository.LinkRepository;
import com.strix.page.db.repository.PageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class HtmlPageStorageServiceImpl implements HtmlPageStorageService {

    private final PageRepository pageRepository;
    private final LinkRepository linkRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public HtmlPageStorageServiceImpl(PageRepository pageRepository, LinkRepository linkRepository, ObjectMapper objectMapper) {
        this.pageRepository = pageRepository;
        this.linkRepository = linkRepository;
        this.objectMapper = objectMapper;
    }

    @Async("threadPoolTaskExecutor")
    @Override
    public void saveLinks(String url, List<PageLink> pageLinks) {
        final PageEntity pageEntity = savePage(url);
        final List<LinkEntity> linkEntities = new ArrayList<>();
        pageLinks.forEach(it -> {
            final LinkEntity linkEntity = new LinkEntity();
            linkEntity.setId(UUID.randomUUID());
            linkEntity.setPageId(pageEntity.getId());
            try {
                linkEntity.setValue(objectMapper.writeValueAsString(it));
            } catch (JsonProcessingException e) {
                log.warn(e);
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
