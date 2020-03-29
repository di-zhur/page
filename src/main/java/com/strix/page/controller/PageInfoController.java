package com.strix.page.controller;

import com.strix.page.core.HtmlPageInfoFactory;
import com.strix.page.core.dto.PageLink;
import com.strix.page.service.HtmlPageInfoBlService;
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

    private final HtmlPageInfoBlService htmlPageInfoFactory;

    @Autowired
    public PageInfoController(HtmlPageInfoBlService htmlPageInfoFactory) {
        this.htmlPageInfoFactory = htmlPageInfoFactory;
    }

    @PostMapping("/getLinks")
    public ResponseEntity<?> getLinks(@RequestBody String url) {
        log.info("getLinks {}", url);
        List<PageLink> links = htmlPageInfoFactory.getMainLinks(url);
        return ResponseEntity.ok(links);
    }

}
