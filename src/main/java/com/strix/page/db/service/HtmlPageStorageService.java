package com.strix.page.db.service;

import com.strix.page.core.dto.PageLink;

import java.util.List;

public interface HtmlPageStorageService {

    void saveLinks(String url, List<PageLink> pageLinks);

}
