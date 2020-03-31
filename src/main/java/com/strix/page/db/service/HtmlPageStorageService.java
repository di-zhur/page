package com.strix.page.db.service;

import com.strix.page.core.dto.PageLink;

import java.util.List;
import java.util.Map;

public interface HtmlPageStorageService {

    void saveLinks(String url, List<PageLink> pageLinks);

    void saveTopics(String url, Map<String, List<String>> topics);

}
