package com.strix.page.rest;

import com.strix.page.db.entity.LinkEntity;
import com.strix.page.db.repository.LinkRepository;
import com.strix.page.rest.dto.PageInfoInput;
import com.strix.page.core.HtmlInfoService;
import com.strix.page.core.dto.PageLink;
import com.strix.page.core.dto.Topics;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/page/info")
@Log4j2
public class PageInfoController {

    private final LinkRepository linkRepository;
    private final HtmlInfoService htmlPageInfoFactory;

    @Autowired
    public PageInfoController(LinkRepository linkRepository, HtmlInfoService htmlPageInfoFactory) {
        this.linkRepository = linkRepository;
        this.htmlPageInfoFactory = htmlPageInfoFactory;
    }

    @PostMapping("/extractLinks")
    public ResponseEntity<List<PageLink>> extractLinks(@RequestBody PageInfoInput pageInfoInput) {
        log.info("extractLinks {}", pageInfoInput.getUrl());
        List<PageLink> links = htmlPageInfoFactory.getMainLinks(pageInfoInput.getUrl());
        return ResponseEntity.ok(links);
    }

    @PostMapping("/extractTopics")
    public ResponseEntity<List<Topics>> extractTopics(@RequestBody PageInfoInput pageInfoInput) {
        log.info("extractTopics {}", pageInfoInput.getUrl());
        List<Topics> topics = htmlPageInfoFactory.getTopics(pageInfoInput.getUrl());
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/getLinks")
    public ResponseEntity<List<LinkEntity>> getLinks() {
        return ResponseEntity.ok(linkRepository.findAll());
    }

}
