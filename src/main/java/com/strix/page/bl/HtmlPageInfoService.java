package com.strix.page.bl;

import com.strix.page.core.dto.PageLink;

import java.util.List;
import java.util.Map;

public interface HtmlPageInfoService {

    List<PageLink> getMainLinks(String url);

    Map<String, List<String>> getTopics(String url);

}
