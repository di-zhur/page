package com.strix.page.service;

import com.strix.page.core.HtmlPageInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HtmlPageInfoBlServiceImpl implements HtmlPageInfoBlService {

    private final HtmlPageInfoFactory htmlPageInfoFactory;

    @Autowired
    public HtmlPageInfoBlServiceImpl(HtmlPageInfoFactory htmlPageInfoFactory) {
        this.htmlPageInfoFactory = htmlPageInfoFactory;
    }

}
