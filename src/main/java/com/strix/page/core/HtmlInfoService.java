package com.strix.page.core;

import com.strix.page.core.dto.Link;
import com.strix.page.core.dto.Topics;

import java.util.List;

public interface HtmlInfoService {

    List<Link> getMainLinks(String url);

    List<Topics> getTopics(String url);

}
