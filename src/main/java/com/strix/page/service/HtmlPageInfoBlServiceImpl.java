package com.strix.page.service;

import com.strix.page.core.HtmlPageInfoFactory;
import com.strix.page.core.dto.PageLink;
import com.strix.page.db.service.HtmlPageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HtmlPageInfoBlServiceImpl implements HtmlPageInfoBlService {

    private final HtmlPageInfoFactory htmlPageInfoFactory;
    private final HtmlPageStorageService htmlPageStorageService;

    @Autowired
    public HtmlPageInfoBlServiceImpl(HtmlPageInfoFactory htmlPageInfoFactory, HtmlPageStorageService htmlPageStorageService) {
        this.htmlPageInfoFactory = htmlPageInfoFactory;
        this.htmlPageStorageService = htmlPageStorageService;
    }

    @Override
    public List<PageLink> getMainLinks(String url) {
        List<PageLink> pageLinks = htmlPageInfoFactory.getMainLinks(url);
        htmlPageStorageService.saveLinks(url, pageLinks);
        return pageLinks;
    }



}
