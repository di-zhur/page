package com.strix.page.bl;

import com.strix.page.core.dto.PageLink;

import java.util.List;

public interface HtmlPageInfoService {

    List<PageLink> getMainLinks(String url);

}
