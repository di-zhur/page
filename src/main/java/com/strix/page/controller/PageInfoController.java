package com.strix.page.controller;

import com.strix.page.controller.dto.PageInfoInput;
import com.strix.page.core.HtmlPageInfoService;
import com.strix.page.core.dto.PageLink;
import com.strix.page.core.dto.Topics;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/page/info")
@Log4j2
public class PageInfoController {

    private final HtmlPageInfoService htmlPageInfoFactory;

    @Autowired
    public PageInfoController(HtmlPageInfoService htmlPageInfoFactory) {
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

}
