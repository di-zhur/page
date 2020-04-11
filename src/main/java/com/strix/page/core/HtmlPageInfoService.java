package com.strix.page.core;

import com.strix.page.core.dto.PageLink;
import com.strix.page.core.dto.Topics;

import java.util.List;

public interface HtmlPageInfoService {

    List<PageLink> getMainLinks(String url);

    List<Topics> getTopics(String url);

}
