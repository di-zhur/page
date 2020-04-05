package com.strix.page.controller;

import com.strix.page.core.dto.PageLink;
import com.strix.page.core.HtmlPageInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<PageLink>> extractLinks(@RequestBody String url) {
        log.info("extractLinks {}", url);
        List<PageLink> links = htmlPageInfoFactory.getMainLinks(url);
        return ResponseEntity.ok(links);
    }

    @PostMapping("/extractTopics")
    public ResponseEntity<Map<String, List<String>>> extractTopics(@RequestBody String url) {
        log.info("extractTopics {}", url);
        Map<String, List<String>> topics = htmlPageInfoFactory.getTopics(url);
        return ResponseEntity.ok(topics);
    }

}
