package com.strix.page.db.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strix.page.core.dto.PageLink;
import com.strix.page.core.dto.Topic;
import com.strix.page.db.entity.LinkEntity;
import com.strix.page.db.entity.PageEntity;
import com.strix.page.db.entity.TopicEntity;
import com.strix.page.db.repository.LinkRepository;
import com.strix.page.db.repository.PageRepository;
import com.strix.page.db.repository.TopicRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Log4j2
public class HtmlPageStorageServiceImpl implements HtmlPageStorageService {

    private final PageRepository pageRepository;
    private final LinkRepository linkRepository;
    private final TopicRepository topicRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public HtmlPageStorageServiceImpl(PageRepository pageRepository, LinkRepository linkRepository, TopicRepository topicRepository, ObjectMapper objectMapper) {
        this.pageRepository = pageRepository;
        this.linkRepository = linkRepository;
        this.topicRepository = topicRepository;
        this.objectMapper = objectMapper;
    }

    @Async("threadPoolTaskExecutor")
    @Transactional
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

    @Async("threadPoolTaskExecutor")
    @Transactional
    @Override
    public void saveTopics(String url, Map<String, List<String>> topics) {
        final PageEntity pageEntity = savePage(url);
        final List<TopicEntity> topicEntities = new ArrayList<>();
        topics.forEach((key, values) -> {
            values.forEach(value -> {
                final TopicEntity topicEntity = new TopicEntity();
                topicEntity.setId(UUID.randomUUID());
                topicEntity.setPageId(pageEntity.getId());
                try {
                    topicEntity.setValue(objectMapper.writeValueAsString(new Topic(key, value)));
                } catch (JsonProcessingException e) {
                    log.warn(e);
                }
                topicEntities.add(topicEntity);
            });
        });
        topicRepository.saveAll(topicEntities);
    }

    private PageEntity savePage(String url) {
        final PageEntity pageEntity = new PageEntity();
        pageEntity.setId(UUID.randomUUID());
        pageEntity.setUrl(url);
        return pageRepository.save(pageEntity);
    }

}
