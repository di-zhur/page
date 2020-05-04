package com.strix.page.db;

import com.strix.page.core.dto.Link;

import java.util.List;
import java.util.Map;

public interface HtmlPageStorage {

    void saveLinks(String url, List<Link> links);

    void saveTopics(String url, Map<String, List<String>> topics);

}
