package com.strix.page.service;

import com.strix.page.core.dto.PageLink;

import java.util.List;

public interface HtmlPageInfoBlService {

    List<PageLink> getMainLinks(String url);

}
